package com.piyush.projectManagmentSystem.repo;

import com.piyush.projectManagmentSystem.entity.Project;
import com.piyush.projectManagmentSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

//    List<Project> findByOwner(User user);
    List<Project> findByNameContainingAndTeamContaining(String partialName, User user);

//    @Query("SELECT p From Project p join p.team t where t =:user")
//    List<Project> findProjectByTeam(@Param("user") User user);

    List<Project> findByTeamContainingOrOwner(User user , User owner);
}
