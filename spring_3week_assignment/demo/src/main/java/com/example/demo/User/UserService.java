package com.example.demo.User;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setAge(user.getAge());
        newUser.setGender(user.getGender());
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        Optional<User> userOpt = userRepository.findById(id);

        if(userOpt.isPresent()){
            return userOpt.get();
        }
        else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public User updateUser(User user) {
        Optional<User> userOpt = userRepository.findById(user.getId());

        if(userOpt.isEmpty()){
            throw new IllegalArgumentException("User not found");
        }

        User updateUser = userOpt.get();
        updateUser.setName(user.getName());
        updateUser.setAge(user.getAge());
        updateUser.setGender(user.getGender());

        return userRepository.save(updateUser);
    }

    public void deleteUser(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isPresent()){
            userRepository.delete(userOpt.get());
        }
        else {
            throw new IllegalArgumentException("User not found");
        }
    }

}
