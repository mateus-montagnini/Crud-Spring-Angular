package br.com.mrocha.service;

import br.com.mrocha.exception.RecordNotFoundException;
import br.com.mrocha.model.Course;
import br.com.mrocha.repository.ICourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

@Validated
@Service
public class CourseService {

    private final ICourseRepository courseRepository;

    public CourseService(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> list() {
        return courseRepository.findAll();
    }

    public Course findById(@PathVariable("id") @NotNull @Positive Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Course create( @Valid Course course) {
        return courseRepository.save(course);
    }

    public Course update(@NotNull @Positive Long id,
                                   @Valid Course course) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    return courseRepository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }


    public void delete(@PathVariable("id") @NotNull @Positive Long id) {
        courseRepository.delete(courseRepository
                .findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}