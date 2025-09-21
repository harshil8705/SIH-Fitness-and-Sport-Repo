package com.ai_llm.valetude.controller;

import com.ai_llm.valetude.model.FoodConsumptions;
import com.ai_llm.valetude.service.AiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AiController {

    @Autowired
    private AiServiceImpl aiService;

    @PostMapping("/users/add/foodConsumptions")
    public ResponseEntity<FoodConsumptions> foodConsumptionMethod(
            @RequestParam(value = "mealDescription") String mealDescription
    ) {

        return new ResponseEntity<>(aiService.foodConsumptionMethod(mealDescription), HttpStatus.OK);

    }

}
