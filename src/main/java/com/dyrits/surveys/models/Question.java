package com.dyrits.surveys.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    // Avoid infinite recursion when serializing.
    @JsonBackReference
    private Survey survey;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private List<Rating> ratings;

    @Transient
    @JsonProperty("averageRating")
    public Double getAverageRating() {
        if (ratings == null || ratings.isEmpty()) {
            return null;
        }
        return ratings.stream()
                .mapToDouble(Rating::getScore)
                .average()
                .orElse(0.0);
    }
}