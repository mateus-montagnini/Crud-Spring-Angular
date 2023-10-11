package br.com.mrocha.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "tb_curso")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @NotNull
    @Column(name = "nome", length = 150, nullable = false)
    private String name;

    @NotBlank
    @NotNull
    @Pattern(regexp = "Back-end | Front-end")
    @Column(name = "categoria", length = 100, nullable = false)
    private String category;
}
