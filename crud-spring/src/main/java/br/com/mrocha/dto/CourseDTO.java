package br.com.mrocha.dto;

import br.com.mrocha.model.Lesson;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public record CourseDTO(
        @JsonProperty("_id") Long id,
        @NotBlank @NotNull String name,
        @NotNull @Pattern(regexp = "Back-end | Front-end") String category,
        @NotNull @NotEmpty @Valid List<LessonDTO> lessons) {
}
