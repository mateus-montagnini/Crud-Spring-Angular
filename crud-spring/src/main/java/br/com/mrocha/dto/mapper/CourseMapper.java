package br.com.mrocha.dto.mapper;

import br.com.mrocha.dto.CourseDTO;
import br.com.mrocha.enums.Category;
import br.com.mrocha.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDTO toDto(Course course) {
        if(course == null) {
            return null;
        }

        return new CourseDTO(course.getId(), course.getName(), "Front-end");
    }

    public Course toEntity(CourseDTO courseDTO) {
        if(courseDTO == null) {
            return null;
        }

        Course course = new Course();
        if(courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(Category.FRONTEND);
        course.setStatus("Ativo");
        return course;
    }
}
