package com.ai_llm.valetude.util;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataIngestor implements CommandLineRunner {

    private final VectorStore vectorStore;

    public DataIngestor(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Override
    public void run(String... args) throws Exception {
        if(vectorStore.similaritySearch("").isEmpty()) {
            System.out.println("Ingesting sample fitness data into the ChromaDB...");
            vectorStore.add(List.of(
                    new Document("Cardiovascular exercise, like running or cycling, is vital for heart health and improves blood circulation."),
                    new Document("Strength training, such as lifting weights, builds muscle mass, which boosts metabolism and strengthens bones."),
                    new Document("A balanced diet should include proteins for muscle repair, carbohydrates for energy, and fats for hormone production."),
                    new Document("Proper hydration is essential. Drinking enough water daily helps regulate body temperature and lubricate joints.")
            ));
            System.out.println("Data Ingestion Complete.");
        }
    }
}
