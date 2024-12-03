package com.dyrits.surveys.repositories;

import com.dyrits.surveys.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
