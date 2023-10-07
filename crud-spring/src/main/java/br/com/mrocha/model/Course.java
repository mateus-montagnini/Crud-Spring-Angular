package br.com.mrocha.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_curso")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Column(name = "nome", length = 150, nullable = false)
    private String name;

    @Column(name = "categoria", length = 100, nullable = false)
    private String category;
}
