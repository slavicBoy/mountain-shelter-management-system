package com.example.demo.controller;

import com.example.demo.model.role.ERole;
import com.example.demo.model.role.Role;
import com.example.demo.model.user.User;
import com.example.demo.model.user.UserDto;
import com.example.demo.model.user.UserService;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.payload.request.SignupRequest;
import com.example.demo.security.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/panel")
public class UsersController {

    UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;
    private UserService userService;

    @Autowired
    public UsersController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, UserService userService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.userService = userService;
    }

    @PostMapping("/workers")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
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


        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getFirstName(), signUpRequest.getLastName());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role workerRole = roleRepository.findByName(ERole.ROLE_WORKER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(workerRole);
        } else {
            strRoles.forEach(role -> {
                if ("owner".equals(role)) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_OWNER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_WORKER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Pracownik dodany do bazy!"));
    }

    @GetMapping("/workers")
    @PreAuthorize("hasRole('OWNER')")
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/workers/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public UserDto getReservationById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/workers/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public UserDto updateReservation(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);


    }

    @DeleteMapping("/workers/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public void deleteReservation(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}