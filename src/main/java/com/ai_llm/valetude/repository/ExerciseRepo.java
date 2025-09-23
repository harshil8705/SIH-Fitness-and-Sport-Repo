package com.ai_llm.valetude.repository;

import com.ai_llm.valetude.model.Exercises;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepo extends JpaRepository<Exercises, Long> {
}
