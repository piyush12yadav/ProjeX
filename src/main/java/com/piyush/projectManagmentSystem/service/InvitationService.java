package com.piyush.projectManagmentSystem.service;

import com.piyush.projectManagmentSystem.entity.Invitation;
import jakarta.mail.MessagingException;

public interface InvitationService {

    public void sendInvitation(String email , Long projectId) throws MessagingException;

    public Invitation acceptInvitation(String token , Long userId) throws Exception;

    public String getTokenByUserMail(String userEmail);

    void deleteToken(String token);
}
