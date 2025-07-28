package com.piyush.projectManagmentSystem.service;

import com.piyush.projectManagmentSystem.entity.Issue;
import com.piyush.projectManagmentSystem.entity.User;
import com.piyush.projectManagmentSystem.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    Issue getIssueById(Long issueId) throws  Exception;
    List<Issue> getIssueByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issue , User user) throws Exception;

    void deleteIssue(Long issueId, Long userId) throws Exception;

    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issueId, String status) throws Exception;

}
