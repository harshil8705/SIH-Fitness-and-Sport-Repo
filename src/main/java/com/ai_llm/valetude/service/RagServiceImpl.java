package com.ai_llm.valetude.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RagServiceImpl implements RagService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public RagServiceImpl(ChatClient chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    @Override
    public String ask(String query) {

        SearchRequest request = SearchRequest.builder()
                .query(query)
                .topK(4)
                .build();

        List<Document> relevantDocuments = vectorStore.similaritySearch(request);
        String context = relevantDocuments.stream()
                .map(Document::getFormattedContent)
                .collect(Collectors.joining("\n"));

        String template = """
    You are a helpful fitness assistant. Answer the user's question based ONLY on the context provided.
    - Do not mention any internal processes, scores, or distances.
    - If the context does not contain the answer, state ONLY that you do not have enough information.
    - Do not make assumptions or add information not present in the context.

    Context:
    {context}

    Question:
    {question}
    """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Prompt prompt = promptTemplate.create(Map.of("context", context, "question", query));

        return chatClient.prompt(prompt).call().content();
    }
}
