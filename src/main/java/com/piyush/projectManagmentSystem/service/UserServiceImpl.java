package com.piyush.projectManagmentSystem.service;

import com.piyush.projectManagmentSystem.config.JwtProvider;
import com.piyush.projectManagmentSystem.entity.User;
import com.piyush.projectManagmentSystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
        String email = JwtProvider.getEmailFromToken(jwt);
        return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {

        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new Exception("User not Found");
        }
        return user;
    }

    @Override
    public Optional<User> findUserById(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new Exception("User not found");
        }
        return Optional.of(user.get());
    }

    @Override
    public User updateUsersProjectSize(User user, int number) throws Exception {
        user.setProjectSize(user.getProjectSize() + number);

        return userRepository.save(user);
    }
}
