package com.example.demo.user;

import com.example.demo.security.role.ERole;
import com.example.demo.security.role.Role;
import com.example.demo.security.role.RoleNotFoundException;
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

    private RoleRepository roleRepository;
    private PasswordEncoder encoder;
    private UserService userService;

    @Autowired
    public UserController(RoleRepository roleRepository, PasswordEncoder encoder, UserService userService) {
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        try
        {
            return userService.registerUser(signUpRequest, encoder, roleRepository);
        } catch (RoleNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
    public UserDto updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
