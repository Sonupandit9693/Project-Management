package com.sonu.repository;

import com.sonu.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat , Long> {
}
