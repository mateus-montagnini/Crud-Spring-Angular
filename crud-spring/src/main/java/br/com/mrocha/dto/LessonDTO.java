package br.com.mrocha.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record LessonDTO(
        Long id,
        @NotNull @NotBlank String name,
        @NotNull @NotBlank String youtubeUrl
) {
}
