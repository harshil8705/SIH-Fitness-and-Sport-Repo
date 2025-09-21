package com.ai_llm.valetude.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class DietRestrictionPredictClientImpl implements DietRestrictionPredictClient {

    private ChatClient chatClient;

    private final PromptTemplate promptTemplate;

    private static final Set<String> VALID_RESTRICTIONS = Set.of("VEGETARIAN", "NON_VEGETARIAN", "VEGAN", "GLUTEN_FREE", "OTHER");

    private static final String DIET_PROMPT_TEMPLATE = """
        You are an expert dietary analyst AI assistant. Your task is to analyze a meal description and classify it into one of the following specific categories:
        - VEGETARIAN
        - NON_VEGETARIAN
        - VEGAN
        - GLUTEN_FREE
        - OTHER

        You must respond with only a single category name from the list above.

        Here are some examples:
        User Meal: "A grilled chicken sandwich on whole wheat bread with a side of fries."
        Your Response: NON_VEGETARIAN

        User Meal: "A quinoa bowl with black beans, corn, avocado, and a lime-cilantro dressing."
        Your Response: VEGAN
        
        // --- THIS IS THE NEW, CRITICAL EXAMPLE ---
        User Meal: "A spicy paneer butter masala curry with naan bread."
        Your Response: VEGETARIAN
        // ------------------------------------------

        Now, analyze the following meal. Do not include any text other than the category name.

        ---
        User Meal: {mealDescription}
        Your Response:
        """;

    public DietRestrictionPredictClientImpl(ChatClient chatClient) {

        this.chatClient = chatClient;
        this.promptTemplate = new PromptTemplate(DIET_PROMPT_TEMPLATE);

    }

    @Override
    public String predictDietRestriction(String mealDescription) {

        try{

            Prompt prompt = this.promptTemplate.create(Map.of("mealDescription", mealDescription));

            String predictedDietRestriction = chatClient
                    .prompt(prompt)
                    .call()
                    .content()
                    .trim()
                    .toUpperCase();

            if(VALID_RESTRICTIONS.contains(predictedDietRestriction)) {
                return predictedDietRestriction;
            } else {
                System.err.println("Warning: AI returned an unexpected dietary restriction: '" + predictedDietRestriction + "'. Defaulting to OTHER.");
                return "OTHER";
            }

        } catch (Exception e) {
            System.err.println("Error calling AI for diet restriction prediction: " + e.getMessage());
            return "OTHER";
        }
    }
}
