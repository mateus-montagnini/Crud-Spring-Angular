package br.com.mrocha.controller;

import br.com.mrocha.model.Course;
import br.com.mrocha.repository.ICourseRepository;
import br.com.mrocha.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private final CourseService service;

    public CourseController(ICourseRepository courseRepository, CourseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Course> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable("id") @NotNull @Positive Long id) {
        return service.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Course create(@RequestBody @Valid Course course) {
        return service.create(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable("id") @NotNull @Positive Long id,
                                         @RequestBody @Valid Course course) {
        return service.update(id, course)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @NotNull @Positive Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().<Void>build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
