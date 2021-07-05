package com.example.demo.user;

import com.example.demo.chat.message.MessageDto;
import com.example.demo.security.payload.request.SignupRequest;
import com.example.demo.security.payload.response.MessageResponse;
import com.example.demo.security.role.ERole;
import com.example.demo.security.role.Role;
import com.example.demo.security.role.RoleNotFoundException;
import com.example.demo.security.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public UserDto updateUser(Long id, UserDto updatedUserDto) {
        User updatedUser = UserMapper.toEntity(updatedUserDto);
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

    public ResponseEntity<MessageResponse> registerUser(SignupRequest signUpRequest, PasswordEncoder encoder, RoleRepository roleRepository){
        if (userRepository.existsByUsername(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }


        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getNotification());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role workerRole = roleRepository.findByName(ERole.ROLE_WORKER)
                    .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found."));
            roles.add(workerRole);
        } else {
            strRoles.forEach(role -> {
                if ("owner".equals(role)) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_OWNER)
                            .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_WORKER)
                            .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Pracownik dodany do bazy!"));

    }

}
