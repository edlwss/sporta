package ru.itche.backend.controller.instructor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.itche.backend.dto.instructor.GetInstructorPayload;
import ru.itche.backend.entity.Instructor;
import ru.itche.backend.dto.instructor.NewInstructorPayload;
import ru.itche.backend.service.instructor.InstructorService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sporta/api/instructors")
public class InstructorsRestController {

    private final InstructorService instructorService;

    @GetMapping
    public List<GetInstructorPayload> getAll() {
        Iterable<Instructor> instructors = instructorService.getAll();
        return GetInstructorPayload.fromList(instructors);
    }

    @PostMapping("/registration")
    public ResponseEntity<Instructor> create(@RequestBody NewInstructorPayload payload,
                                             UriComponentsBuilder uriBuilder) {

        Instructor created = instructorService.create(payload);

        return ResponseEntity.created(uriBuilder
                        .replacePath("/sporta/api/instructors/{id}")
                        .build(Map.of("id", created.getId())))
                .body(created);
    }
}
