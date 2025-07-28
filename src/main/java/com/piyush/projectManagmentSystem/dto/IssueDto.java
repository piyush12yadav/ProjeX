package com.piyush.projectManagmentSystem.dto;

import com.piyush.projectManagmentSystem.entity.Project;
import com.piyush.projectManagmentSystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueDto {

    private Long id;

    private String title;
    private String description;
    private String status;

    private Long projectID;

    private String priority;

    private LocalDate dueDate;
    private List<String> tags = new ArrayList<>();

    private Project project;

    private User assignee;

}
