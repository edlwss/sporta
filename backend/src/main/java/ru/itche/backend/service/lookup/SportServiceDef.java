package ru.itche.backend.service.lookup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itche.backend.entity.reference.SportDisciplines;
import ru.itche.backend.entity.reference.SportTypes;
import ru.itche.backend.repository.lookup.SportDisciplinesRepository;
import ru.itche.backend.repository.lookup.SportTypesRepository;
import ru.itche.backend.dto.lookup.ImportResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SportServiceDef implements SportService {

    private final SportTypesRepository sportTypesRepository;
    private final SportDisciplinesRepository sportDisciplinesRepository;

    @Override
    public Iterable<SportTypes> getSports() {
        return sportTypesRepository.findAll();
    }

    /**
     * Импорт из CSV в ресурсах.
     * @param resourcePath путь в classpath, например "types_sport.csv" (положи файл в src/main/resources/)
     * @return статистика импорта строки: [savedTypes, savedDisciplines, skipped]
     */
    @Transactional
    public ImportResult importFromCsv(String resourcePath) {
        int newTypes = 0;
        int newDisciplines = 0;
        int skipped = 0;

        ClassPathResource resource = new ClassPathResource(resourcePath);
        SportTypes currentType = null;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                // пропускаем пустые строки
                if (line == null) continue;
                line = line.trim();
                if (line.isEmpty()) continue;

                // убираем BOM, если есть
                if (line.startsWith("\uFEFF")) {
                    line = line.substring(1);
                }

                // split по ';', сохраняем пустые ячейки
                String[] cols = line.split(";", -1);

                // нужна минимум 5 колонок по формату примера
                if (cols.length < 5) continue;

                String first = cols[0].trim();            // номер строки с видом спорта или пусто (для продолжений)
                String sportName = cols[1].trim();        // наименование вида спорта
                String sportCode = cols[2].trim();        // код вида спорта
                String disciplineName = cols[3].trim();   // наименование дисциплины
                String disciplineCode = cols[4].trim();   // код дисциплины

                // строка, начинающаяся с номера => новый вид спорта (главная запись)
                if (!first.isEmpty() && first.matches("\\d+")) {
                    // если есть код и имя — создаём/получаем SportTypes
                    if (!sportCode.isEmpty() && !sportName.isEmpty()) {
                        Optional<SportTypes> opt = sportTypesRepository.findById(sportCode);
                        if (opt.isPresent()) {
                            currentType = opt.get();
                        } else {
                            SportTypes t = new SportTypes();
                            t.setId(sportCode);
                            t.setNameTypesSport(sportName);
                            sportTypesRepository.save(t);
                            currentType = t;
                            newTypes++;
                            log.debug("Saved sport type: {} / {}", sportCode, sportName);
                        }
                    } else {
                        // нет кода/имени — пропускаем
                        log.debug("Skipping malformed type line: {}", line);
                        skipped++;
                        currentType = null;
                    }

                    // в этой же строке может быть первая дисциплина
                    if (!disciplineCode.isEmpty() && !disciplineName.isEmpty() && currentType != null) {
                        if (!sportDisciplinesRepository.existsById(disciplineCode)) {
                            SportDisciplines d = new SportDisciplines();
                            d.setId(disciplineCode);
                            d.setNameSportDisciplines(disciplineName);
                            d.setSportType(currentType);
                            sportDisciplinesRepository.save(d);
                            newDisciplines++;
                            log.debug("Saved discipline (inline): {} / {}", disciplineCode, disciplineName);
                        } else {
                            log.debug("Discipline already exists: {}", disciplineCode);
                            skipped++;
                        }
                    }
                } else {
                    // продолжение: строка дисциплины, связываем с последним текущим видом спорта
                    if (currentType == null) {
                        log.debug("No current sport type for discipline line, skipping: {}", line);
                        skipped++;
                        continue;
                    }
                    if (!disciplineCode.isEmpty() && !disciplineName.isEmpty()) {
                        if (!sportDisciplinesRepository.existsById(disciplineCode)) {
                            SportDisciplines d = new SportDisciplines();
                            d.setId(disciplineCode);
                            d.setNameSportDisciplines(disciplineName);
                            d.setSportType(currentType);
                            sportDisciplinesRepository.save(d);
                            newDisciplines++;
                            log.debug("Saved discipline: {} / {}", disciplineCode, disciplineName);
                        } else {
                            log.debug("Discipline already exists: {}", disciplineCode);
                            skipped++;
                        }
                    } else {
                        log.debug("Malformed discipline line, skipping: {}", line);
                        skipped++;
                    }
                }
            }
        } catch (IOException ex) {
            log.error("Error reading CSV resource '{}'", resourcePath, ex);
            throw new RuntimeException(ex);
        }

        log.info("Import finished. typesSaved={}, disciplinesSaved={}, skipped={}", newTypes, newDisciplines, skipped);
        return new ImportResult(newTypes, newDisciplines, skipped);
    }
}

