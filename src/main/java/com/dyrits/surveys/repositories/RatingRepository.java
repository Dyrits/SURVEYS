package com.dyrits.surveys.repositories;

import com.dyrits.surveys.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}