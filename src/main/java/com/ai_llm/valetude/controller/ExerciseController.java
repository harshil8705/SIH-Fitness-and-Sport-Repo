package com.ai_llm.valetude.controller;

import com.ai_llm.valetude.configuration.AppConstants;
import com.ai_llm.valetude.dto.ExerciseDTO;
import com.ai_llm.valetude.model.Exercises;
import com.ai_llm.valetude.response.ExerciseResponse;
import com.ai_llm.valetude.response.ExerciseResponse2;
import com.ai_llm.valetude.service.ExerciseServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExerciseController {

    private final ExerciseServiceImpl exerciseService;

    public ExerciseController(ExerciseServiceImpl exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping("/add/exercise")
    public ResponseEntity<ExerciseResponse> addNewExercise(@RequestBody ExerciseDTO exercises) {

        return new ResponseEntity<>(exerciseService.addNewExercise(exercises), HttpStatus.OK);

    }

    @GetMapping("/get/exercises")
    public ResponseEntity<ExerciseResponse2> getAllExercises(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_EXERCISES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortOrder
    ) {

        return new ResponseEntity<>(exerciseService.getAllExercises(pageNumber, pageSize, sortBy, sortOrder), HttpStatus.FOUND);

    }

}
