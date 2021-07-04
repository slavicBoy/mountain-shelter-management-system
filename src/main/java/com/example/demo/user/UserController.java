package com.example.demo.user;

import com.example.demo.security.role.ERole;
import com.example.demo.security.role.Role;
import com.example.demo.security.role.RoleRepository;
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
@RequestMapping("/api/panel/workers")
public class UserController {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;
    private UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, UserService userService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.userService = userService;
    }

    @PostMapping
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


        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getNotification());

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

    @GetMapping
    @PreAuthorize("hasRole('OWNER')")
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public UserDto getReservationById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/notification/{id}")
    @PreAuthorize("hasRole('OWNER') or hasRole('WORKER')")
    public int getNotification(@PathVariable Long id) {
        return userService.getAmountOfNotifications(id);
    }

    @GetMapping("/notification/clear/{id}")
    @PreAuthorize("hasRole('OWNER') or hasRole('WORKER')")
    public void clearNotification(@PathVariable Long id) {
        userService.clearNotifications(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public UserDto updateReservation(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public void deleteReservation(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
