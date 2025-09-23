package com.ai_llm.valetude.service;

import com.ai_llm.valetude.response.ExerciseResponse;

import java.util.List;

public interface RagService {

    String ask(String query);

    List<ExerciseResponse> getExerciseRecommendation(String userGoal);
}
