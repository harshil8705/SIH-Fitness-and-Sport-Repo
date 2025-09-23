package com.ai_llm.valetude.service;

import com.ai_llm.valetude.dto.ExerciseDTO;
import com.ai_llm.valetude.response.ExerciseResponse;
import com.ai_llm.valetude.response.ExerciseResponse2;

import java.util.List;

public interface ExerciseService {

    ExerciseResponse addNewExercise(ExerciseDTO exercises);

    ExerciseResponse2 getAllExercises(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

}
