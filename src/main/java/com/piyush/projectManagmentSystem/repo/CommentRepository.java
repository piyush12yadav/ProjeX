package com.piyush.projectManagmentSystem.repo;

import com.piyush.projectManagmentSystem.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment , Long> {


    List<Comment> findByIssueId(Long issueId);
}
