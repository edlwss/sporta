package ru.itche.backend.controller.instructor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itche.backend.dto.instructor.GetInstructorPayload;
import ru.itche.backend.dto.instructor.UpdateInstructorPhotoPayload;
import ru.itche.backend.dto.instructor.UpdateInstructorSportsPayload;
import ru.itche.backend.entity.Instructor;
import ru.itche.backend.service.instructor.InstructorService;
import ru.itche.backend.dto.instructor.UpdateInstructorPersonalPayload;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sporta/api/instructor/{id:\\d+}")
public class InstructorRestController {

    private final InstructorService instructorService;

    @ModelAttribute("instructor")
    public Instructor getInstructor(@PathVariable("id") Long userId) {
        return instructorService.findById(userId)
                .orElseThrow(() -> new InstructorRestController.InstructorNotFoundException(userId));
    }

    @GetMapping
    public  GetInstructorPayload get(@ModelAttribute("instructor") Instructor instructor) {
        return GetInstructorPayload.from(instructor);
    }

    @PatchMapping("/edit/addsports")
    public ResponseEntity<?> addSports(@PathVariable("id") Long id,
                                    @RequestBody UpdateInstructorSportsPayload payload) {
        instructorService.addSports(id, payload);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/edit/personal")
    public ResponseEntity<?> updatePersonal(@PathVariable("id") Long id,
                                    @RequestBody UpdateInstructorPersonalPayload payload) {
        instructorService.updatePersonal(id, payload);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/edit/photo")
    public ResponseEntity<?> updatePhoto(@PathVariable("id") Long id,
                                         @RequestBody UpdateInstructorPhotoPayload payload) {
        instructorService.updatePhoto(id, payload);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        instructorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private static class InstructorNotFoundException extends RuntimeException {
        public InstructorNotFoundException(Long id) {
            super("Instructor with id " + id + " not found");
        }
    }
}
