package com.dyrits.surveys.repositories;

import com.dyrits.surveys.models.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
