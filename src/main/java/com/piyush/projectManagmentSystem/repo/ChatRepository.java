package com.piyush.projectManagmentSystem.repo;

import com.piyush.projectManagmentSystem.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat , Long> {


}
