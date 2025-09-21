package com.ai_llm.valetude.repository;

import com.ai_llm.valetude.model.FoodConsumptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodConsumptionRepo extends JpaRepository<FoodConsumptions, Long> {
}
