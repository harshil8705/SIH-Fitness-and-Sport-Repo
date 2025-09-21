package com.ai_llm.valetude.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CaloriePredictionClientImpl implements CaloriePredictionClient{

    private final ChatClient chatClient;

    private final PromptTemplate promptTemplate;

    private static final String CALORIE_PROMPT_TEMPLATE = """
            You are an expert calorie predictor AI.
            Your single task is to estimate the calories in a meal description and respond with ONLY the integer number.
            Do not provide any text, explanations, or units.

            ### EXAMPLES ###
            Input: A grilled chicken salad with lettuce, tomatoes, cucumbers, and a light vinaigrette dressing.
            Output: 350

            Input: Two scrambled eggs with a slice of whole wheat toast and a glass of orange juice.
            Output: 320
            ### END EXAMPLES ###

            Now, analyze the meal below and provide only the total calorie count as a number.

            ### MEAL TO ANALYZE ###
            {mealDescription}
            """;



    public CaloriePredictionClientImpl(ChatClient chatClient) {

        this.chatClient = chatClient;
        this.promptTemplate = new PromptTemplate(CALORIE_PROMPT_TEMPLATE);

    }

    @Override
    public Integer predictCalories(String mealDescription) {

        Prompt prompt = this.promptTemplate.create(Map.of("mealDescription", mealDescription));

        String predictedCalorie = chatClient
                .prompt(prompt)
                .call()
                .content();

        try{

            return Integer.parseInt(predictedCalorie.trim());

        } catch (NumberFormatException e) {
            System.err.println("Error: AI did not return a valid number. Response was: " + predictedCalorie);
            return null;
        }

    }

}
