package br.com.mrocha.controller;

import br.com.mrocha.model.Course;
import br.com.mrocha.repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
