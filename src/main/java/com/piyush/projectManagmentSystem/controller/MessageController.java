package com.piyush.projectManagmentSystem.controller;


import com.piyush.projectManagmentSystem.entity.Chat;
import com.piyush.projectManagmentSystem.entity.Message;
import com.piyush.projectManagmentSystem.entity.User;
import com.piyush.projectManagmentSystem.request.CreateMessageRequest;
import com.piyush.projectManagmentSystem.service.MessageService;
import com.piyush.projectManagmentSystem.service.ProjectService;
import com.piyush.projectManagmentSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest request) throws Exception{
//        User user = userService.findUserById(request.getSenderId());

        Chat chat = projectService.getProjectById(request.getProjectId()).getChat();

        if(chat == null){
            throw new Exception("Chats not found");
        }

        Message message = messageService.sendMessage(request.getSenderId(),
                request.getProjectId(), request.getContent());

        return ResponseEntity.ok(message);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessageByChatId(@PathVariable Long projectId) throws Exception{
        List<Message> messages = messageService.getMessageByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }
}
