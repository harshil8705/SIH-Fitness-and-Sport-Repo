package com.ai_llm.valetude.service;

import com.ai_llm.valetude.model.FoodConsumptions;

public interface AiService {

    FoodConsumptions foodConsumptionMethod(String mealDescription);

}
