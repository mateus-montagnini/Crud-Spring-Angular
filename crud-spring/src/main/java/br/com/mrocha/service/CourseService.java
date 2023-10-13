package br.com.mrocha.service;

import br.com.mrocha.dto.CourseDTO;
import br.com.mrocha.dto.mapper.CourseMapper;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@Service
public class CourseService {

    private final ICourseRepository courseRepository;
    private final CourseMapper mapper;

    public CourseService(ICourseRepository courseRepository, CourseMapper mapper) {
        this.courseRepository = courseRepository;
        this.mapper = mapper;
    }

    public List<CourseDTO> list() {
        return courseRepository.findAll()
                .stream()
                .map(mapper :: toDto)
                .collect(Collectors.toList());
    }

    public CourseDTO findById(@PathVariable("id") @NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(mapper :: toDto)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return mapper.toDto(courseRepository.save(mapper.toEntity(course)));
    }

    public CourseDTO update(@NotNull @Positive Long id,
                                   @Valid @NotNull CourseDTO course) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.name());
                    recordFound.setCategory(course.category());
                    return mapper.toDto(courseRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }


    public void delete(@PathVariable("id") @NotNull @Positive Long id) {
        courseRepository.delete(courseRepository
                .findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
