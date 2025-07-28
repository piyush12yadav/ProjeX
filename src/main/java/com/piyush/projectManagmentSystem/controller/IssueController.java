package com.piyush.projectManagmentSystem.controller;

import com.piyush.projectManagmentSystem.dto.IssueDto;
import com.piyush.projectManagmentSystem.entity.Issue;
import com.piyush.projectManagmentSystem.entity.User;
import com.piyush.projectManagmentSystem.request.IssueRequest;
import com.piyush.projectManagmentSystem.responce.MessageResponse;
import com.piyush.projectManagmentSystem.service.IssueService;
import com.piyush.projectManagmentSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;


    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception{
        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) throws Exception{
        return ResponseEntity.ok(issueService.getIssueByProjectId(projectId));
    }


    @PostMapping
    public ResponseEntity<IssueDto> createIssue(@RequestBody IssueRequest issue,
                                                @RequestHeader("Authorization") String token) throws Exception{
        User tokenUser = userService.findUserProfileByJwt(token);

        User user = userService.findUserById(tokenUser.getId()).orElseThrow(() -> new RuntimeException("User not found"));


            Issue createIssue = issueService.createIssue(issue,tokenUser);
            IssueDto issueDto = new IssueDto();
            issueDto.setDescription(createIssue.getDescription());
            issueDto.setDueDate(createIssue.getDueDate());
            issueDto.setId(createIssue.getId());
            issueDto.setPriority(createIssue.getPriority());
            issueDto.setProject(createIssue.getProject());
            issueDto.setProjectID(createIssue.getProjectID());
            issueDto.setStatus(createIssue.getStatus());
            issueDto.setTitle(createIssue.getTitle());
            issueDto.setTags(createIssue.getTags());
            issueDto.setAssignee(createIssue.getAssignee());

            return ResponseEntity.ok(issueDto);
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId,
                                                       @RequestHeader("Authorization") String token)
        throws Exception{
        User user = userService.findUserProfileByJwt(token);
        issueService.deleteIssue(issueId , user.getId());

        MessageResponse res = new MessageResponse("Issue id deleted");
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId,
                                                @PathVariable Long userId)
        throws Exception{
        Issue issue = issueService.addUserToIssue(issueId , userId);
        return ResponseEntity.ok(issue);
    }

    @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issue> updateIssueStatus(
            @PathVariable String status,
            @PathVariable Long issueId) throws  Exception{

        Issue issue = issueService.updateStatus(issueId, status);
        return ResponseEntity.ok(issue);
    }
}
