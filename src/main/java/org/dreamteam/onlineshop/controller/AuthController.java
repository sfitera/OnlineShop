package org.dreamteam.onlineshop.controller;

import org.dreamteam.onlineshop.configuration.JwtUtils;
import org.dreamteam.onlineshop.model.DTOs.UserDTO;
import org.dreamteam.onlineshop.model.User;
import org.dreamteam.onlineshop.payload.LoginRequest;
import org.dreamteam.onlineshop.payload.SignupRequest;
import org.dreamteam.onlineshop.payload.JwtResponse;
import org.dreamteam.onlineshop.payload.MessageResponse;
import org.dreamteam.onlineshop.repository.UserRepository;
import org.dreamteam.onlineshop.service.UserServiceBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173/")
public class AuthController {

    private final UserServiceBean userServiceBean;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public AuthController(UserServiceBean userServiceBean, UserRepository userRepository, JwtUtils jwtUtils) {
        this.userServiceBean = userServiceBean;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody SignupRequest request) {
        if (userRepository.findUserByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(request.getUsername());
        userDTO.setUserEmail(request.getEmail());
        userDTO.setUserPassword(request.getPassword());
        userDTO.setUserAddress(request.getAddress());
        userServiceBean.addUser(userDTO);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest request) {
        Optional<User> userOptional = userRepository.findUserByUsername(request.getUsername());

        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new JwtResponse("Invalid username or password", null, null));
        }

        User user = userOptional.get();
        String jwt = jwtUtils.generateJwtToken(user);

        // Získame role ako String zoznam
        List<String> roles = user.getUserRoles().stream()
                .map(Enum::name)
                .toList();

        return ResponseEntity.ok(new JwtResponse(jwt, user.getUsername(), roles));
    }

}
