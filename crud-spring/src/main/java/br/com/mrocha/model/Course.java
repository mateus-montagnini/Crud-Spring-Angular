package br.com.mrocha.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.Length;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
@SQLDelete(sql = "UPDATE tb_curso SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
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

    @Pattern(regexp = "Ativo | Inativo")
    @Column(name = "status", length = 10)
    private String status;

}
