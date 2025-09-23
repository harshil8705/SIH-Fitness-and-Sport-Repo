package com.ai_llm.valetude.service;

import com.ai_llm.valetude.dto.ExerciseDTO;
import com.ai_llm.valetude.model.Exercises;
import com.ai_llm.valetude.repository.ExerciseRepo;
import com.ai_llm.valetude.response.ExerciseResponse;
import com.ai_llm.valetude.response.ExerciseResponse2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService{

    private final ExerciseRepo exerciseRepo;

    public ExerciseServiceImpl (ExerciseRepo exerciseRepo) {
        this.exerciseRepo = exerciseRepo;
    }

    @Override
    public ExerciseResponse addNewExercise(ExerciseDTO exercises) {

        Exercises newExercise = Exercises.builder()
                .exerciseBenefits(exercises.getExerciseBenefits())
                .exerciseDescription(exercises.getExerciseDescription())
                .exerciseSteps(exercises.getExerciseSteps())
                .exerciseName(exercises.getExerciseName())
                .build();

        Exercises savedExercise = exerciseRepo.save(newExercise);

        return ExerciseResponse.builder()
                .exerciseBenefits(savedExercise.getExerciseBenefits())
                .exerciseDescription(savedExercise.getExerciseDescription())
                .exerciseId(savedExercise.getExerciseId())
                .exerciseName(savedExercise.getExerciseName())
                .exerciseSteps(savedExercise.getExerciseSteps())
                .build();

    }

    @Override
    public ExerciseResponse2 getAllExercises(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Exercises> exercisesPage = exerciseRepo.findAll(pageDetails);

        List<Exercises> exercises = exercisesPage.getContent();

        List<ExerciseResponse> exerciseResponseList = exercises.stream()
                .map(e -> ExerciseResponse.builder()
                        .exerciseName(e.getExerciseName())
                        .exerciseId(e.getExerciseId())
                        .exerciseBenefits(e.getExerciseBenefits())
                        .exerciseDescription(e.getExerciseDescription())
                        .exerciseSteps(e.getExerciseSteps())
                        .build()
                ).toList();

        if(exercises.isEmpty()) {

            System.out.println("No Exercises found in the DataBase.");

            throw new RuntimeException("No Exercises found. Please try again later.");

        }

        ExerciseResponse2 exerciseResponse = new ExerciseResponse2();
        exerciseResponse.setExerciseResponseList(exerciseResponseList);
        exerciseResponse.setPageNumber(exercisesPage.getNumber());
        exerciseResponse.setPageSize(exercisesPage.getSize());
        exerciseResponse.setTotalPages(exercisesPage.getTotalPages());
        exerciseResponse.setTotalElements(exercisesPage.getTotalElements());
        exerciseResponse.setLastPage(exercisesPage.isLast());

        return exerciseResponse;

    }

    public String formatExercisesForEmbedding(Exercises exercise) {
        return String.format(
                "Exercise Name: %s\nDescription: %s\nSteps: %s\nBenefits: %s",
                exercise.getExerciseName(),
                exercise.getExerciseDescription(),
                exercise.getExerciseSteps(),
                exercise.getExerciseBenefits()
        );
    }

}
