package com.dyrits.surveys.services;

import com.dyrits.surveys.models.Question;
import com.dyrits.surveys.models.Rating;
import com.dyrits.surveys.models.Survey;
import com.dyrits.surveys.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public Optional<Survey> getSurvey(Long id) {
        return surveyRepository.findById(id);
    }

    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    public void submitRatings(Long surveyId, List<Rating> ratings) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new RuntimeException("Survey not found"));

        if (ratings.size() != 5) {
            throw new IllegalArgumentException("Must rate all 5 questions");
        }

        for (Rating rating : ratings) {
            Question question = survey.getQuestions().get(ratings.indexOf(rating));
            rating.setQuestion(question);
            question.getRatings().add(rating);
        }

        surveyRepository.save(survey);
    }
}