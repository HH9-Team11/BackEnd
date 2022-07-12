package com.hh99team11.backend.repository;


import com.hh99team11.backend.model.Message;
import com.hh99team11.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message>findMessageBySender(User sender);
}
