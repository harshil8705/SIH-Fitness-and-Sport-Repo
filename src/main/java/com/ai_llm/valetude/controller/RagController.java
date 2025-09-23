package com.ai_llm.valetude.controller;

import com.ai_llm.valetude.response.ExerciseResponse;
import com.ai_llm.valetude.service.ExerciseServiceImpl;
import com.ai_llm.valetude.service.RagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RagController {

    @Autowired
    private RagServiceImpl ragService;

    @GetMapping("/ask")
    public ResponseEntity<String> ask(@RequestParam(value = "query") String query) {
        return new ResponseEntity<>(ragService.ask(query), HttpStatus.OK);
    }

    @GetMapping("/exercise/recommendations")
    public ResponseEntity<List<ExerciseResponse>> getRecommendation(@RequestParam(value = "userGoal") String userGoal) {

        return new ResponseEntity<>(ragService.getExerciseRecommendation(userGoal), HttpStatus.OK);

    }

}
