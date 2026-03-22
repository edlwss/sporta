package ru.itche.backend.controller.student;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itche.backend.dto.student.GetStudentPayload;
import ru.itche.backend.entity.Student;
import ru.itche.backend.exceptions.StudentNotFoundException;
import ru.itche.backend.service.student.StudentService;
import ru.itche.backend.dto.student.UpdateStudentPayload;
import ru.itche.backend.service.user.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sporta/api/student/{id:\\d+}")
public class StudentRestController {

    private final StudentService studentService;
    private final UserService userService;

    @GetMapping
    public GetStudentPayload findStudent(@PathVariable("id") Long id) {
        Student student = studentService.findStudent(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        return GetStudentPayload.from(student);
    }

    @PatchMapping("/edit")
    public ResponseEntity<Void> updateStudent(@PathVariable("id") Long id,
                                              @Valid @RequestBody UpdateStudentPayload payload) {

        studentService.updateStudent(id, payload);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long userId) {
        studentService.deleteStudent(userId);
        return ResponseEntity.noContent().build();
    }

}

