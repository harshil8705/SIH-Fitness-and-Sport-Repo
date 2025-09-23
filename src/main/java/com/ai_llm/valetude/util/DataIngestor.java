package com.ai_llm.valetude.util;

import com.ai_llm.valetude.model.Exercises;
import com.ai_llm.valetude.repository.ExerciseRepo;
import com.ai_llm.valetude.service.ExerciseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DataIngestor implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataIngestor.class);

    private final VectorStore vectorStore;
    private final ExerciseRepo exerciseRepo;
    private final ExerciseServiceImpl exerciseService;

    public DataIngestor(VectorStore vectorStore, ExerciseRepo exerciseRepo, ExerciseServiceImpl exerciseService) {
        this.vectorStore = vectorStore;
        this.exerciseRepo = exerciseRepo;
        this.exerciseService = exerciseService;
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("Ingesting sample fitness data into the ChromaDB...");
        List<Document> allDocuments = new ArrayList<>(List.of(
                new Document("Cardiovascular exercise, like running or cycling, is vital for heart health and improves blood circulation."),
                new Document("Strength training, such as lifting weights, builds muscle mass, which boosts metabolism and strengthens bones."),
                new Document("A balanced diet should include proteins for muscle repair, carbohydrates for energy, and fats for hormone production."),
                new Document("Proper hydration is essential. Drinking enough water daily helps regulate body temperature and lubricate joints.")
        ));
        logger.info("Fetching specific exercises from PostgreSQL.");
        List<Exercises> allExercises = exerciseRepo.findAll();
        if(!allExercises.isEmpty()) {
            List<Document> exerciseDocuments = allExercises.stream()
                    .map(exercise -> {
                        String content = exerciseService.formatExercisesForEmbedding(exercise);
                        Map<String, Object> metadata = Map.of("exerciseId", exercise.getExerciseId());
                        return new Document(content, metadata);
                    })
                    .toList();
            allDocuments.addAll(exerciseDocuments);
            logger.info("Added all Exercises from the database.");
        } else {
            logger.info("No Exercises found in the database to add.");
        }
        if (!allDocuments.isEmpty()) {
            vectorStore.add(allDocuments);
            logger.info("Successfully Ingested the data into the vector database.");
        }
    }
}
