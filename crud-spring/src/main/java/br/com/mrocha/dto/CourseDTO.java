package br.com.mrocha.dto;

import br.com.mrocha.model.Lesson;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public record CourseDTO(
        @JsonProperty("_id") Long id,
        @NotBlank @NotNull String name,
        @NotNull @Pattern(regexp = "Back-end | Front-end") String category,
        List<LessonDTO> lessons) {
}
