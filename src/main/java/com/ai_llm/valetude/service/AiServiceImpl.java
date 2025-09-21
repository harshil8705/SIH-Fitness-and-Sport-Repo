package com.ai_llm.valetude.service;

import com.ai_llm.valetude.model.FoodConsumptions;
import com.ai_llm.valetude.repository.FoodConsumptionRepo;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AiServiceImpl implements AiService{

    @Autowired
    private ChatClient chatClient;

    private final FoodConsumptionRepo foodConsumptionRepo;
    private final CaloriePredictionClientImpl caloriePredictionClient;
    private final DietRestrictionPredictClientImpl dietRestrictionPredictClient;

    public AiServiceImpl(FoodConsumptionRepo foodConsumptionRepo,
                         CaloriePredictionClientImpl caloriePredictionClient,
                         DietRestrictionPredictClientImpl dietRestrictionPredictClient) {
        this.foodConsumptionRepo = foodConsumptionRepo;
        this.caloriePredictionClient = caloriePredictionClient;
        this.dietRestrictionPredictClient = dietRestrictionPredictClient;
    }

    @Override
    public FoodConsumptions foodConsumptionMethod(String mealDescription) {

        System.out.println("User entered the Meal: " + mealDescription);

        Integer predictedCalorie = caloriePredictionClient.predictCalories(mealDescription);

        if (predictedCalorie == null) {
            System.out.println("Could not determine calories. Aborting meal log for: " + mealDescription);
            return null;
        }

        String predictedDietRestriction = dietRestrictionPredictClient.predictDietRestriction(mealDescription);

        if (predictedDietRestriction == null) {
            System.out.println("Could not determine diet restriction. Aborting meal log for: " + mealDescription);
            return null;
        }

        FoodConsumptions foodToSave = FoodConsumptions.builder()
                .mealDescription(mealDescription)
                .caloriesIntake(predictedCalorie)
                .dietRestriction(predictedDietRestriction)
                .build();

        try{
            System.out.println("Saving to your details: " + foodToSave);
            return foodConsumptionRepo.save(foodToSave);
        } catch (Exception e) {
            System.out.println("Error saving food consumption: " + e.getMessage());
            return null;
        }
    }
}
