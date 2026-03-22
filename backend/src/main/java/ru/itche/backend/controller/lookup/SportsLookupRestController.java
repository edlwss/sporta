package ru.itche.backend.controller.lookup;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itche.backend.dto.lookup.ImportResult;
import ru.itche.backend.entity.reference.SportTypes;
import ru.itche.backend.service.lookup.SportService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sporta/api/lookup/sports")
public class SportsLookupRestController {

    private final SportService sportService;

    @GetMapping
    public Iterable<SportTypes> getSports() {
        return sportService.getSports();
    }

    @PostMapping("/import")
    public ResponseEntity<ImportResult> importFromCsv(
            @RequestParam(defaultValue = "types_sport.csv") String file
    ) {
        return ResponseEntity.ok(sportService.importFromCsv(file));
    }
}
