package com.dyrits.surveys.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@JsonIgnoreProperties({"question"})
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer score;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}