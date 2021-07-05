package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto updateUser(Long id, User updatedUser) {
        User user = userRepository.getOne(id);
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        userRepository.save(user);
        return UserMapper.toDto(user);
    }

    public UserDto findById(Long id) {
        return UserMapper.toDto(userRepository.getOne(id));
    }

    public int getAmountOfNotifications(Long id) {
        User user = userRepository.getOne(id);
        return user.getNotification();
    }

    public void clearNotifications(Long id) {
        User user = userRepository.getOne(id);
        user.setNotification(0);
        userRepository.save(user);
    }
}
