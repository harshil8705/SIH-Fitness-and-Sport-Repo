package com.ai_llm.valetude.repository;

import com.ai_llm.valetude.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Long> {
}
