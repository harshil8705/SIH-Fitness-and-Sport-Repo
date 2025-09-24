package com.ai_llm.valetude.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChatbotController {

    private final ChatClient chatClient;

    public ChatbotController(ChatClient chatClient) {

        this.chatClient = chatClient;

    }

    @GetMapping("/askAI")
    public String askAi(@RequestParam("query") String query) {

        var systemPrompt = """
            You are a fitness, diet, and sports assistant.
            ONLY answer questions related to fitness, exercise, diet, nutrition, or sports.
            Respond only with plain text.
            If the question is outside these domains, say:
            "Sorry, I can only help with fitness, diet, and sports-related questions."
        """;

        Prompt prompt = new Prompt(List.of(
                new SystemMessage(systemPrompt),
                new UserMessage(query)
        ));

        return chatClient.prompt(prompt).call().content();

    }
}
