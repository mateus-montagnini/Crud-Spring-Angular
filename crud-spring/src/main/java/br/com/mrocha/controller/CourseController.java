package br.com.mrocha.controller;

import br.com.mrocha.model.Course;
import br.com.mrocha.repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private ICourseRepository courseRepository;

    @GetMapping
    public List<Course> list() {
        return courseRepository.findAll();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseRepository.save(course));
    }
}
