package com.piyush.projectManagmentSystem.service;

import com.piyush.projectManagmentSystem.entity.Chat;
import com.piyush.projectManagmentSystem.repo.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    private ChatRepository chatRepository;
    @Override
    public Chat createChat(Chat chat) {

        return chatRepository.save(chat);
    }
}
