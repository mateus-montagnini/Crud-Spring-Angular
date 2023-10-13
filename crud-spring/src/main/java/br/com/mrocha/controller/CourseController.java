package br.com.mrocha.controller;

import br.com.mrocha.dto.CourseDTO;
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
    public List<CourseDTO> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public CourseDTO findById(@PathVariable("id") @NotNull @Positive Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseDTO create(@RequestBody @Valid CourseDTO course) {
        return service.create(course);
    }

    @PutMapping("/{id}")
    public CourseDTO update(@PathVariable("id") @NotNull @Positive Long id,
                                         @RequestBody @Valid @NotNull CourseDTO course) {
        return service.update(id, course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") @NotNull @Positive Long id) {
        service.delete(id);
    }
}
