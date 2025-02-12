package org.dreamteam.onlineshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.dreamteam.onlineshop.model.DTOs.LoginRequestDTO;
import org.dreamteam.onlineshop.model.DTOs.PasswordUpdateDTO;
import org.dreamteam.onlineshop.model.DTOs.UserDTO;
import org.dreamteam.onlineshop.model.DTOs.UserResponseDTO;
import org.dreamteam.onlineshop.model.User;
import org.dreamteam.onlineshop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "API na správu uzivatelov")
@CrossOrigin(origins = "http://localhost:5173/")
public class UserRestController {

    private final UserService userService;
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    @Operation(summary = "Pridaj uzivatela")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO){
        userService.addUser(userDTO);
        return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    @Operation(summary = "Aktualizuj uzivatela")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        userService.updateUser(id, userDTO);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Zmaz uzivatela")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Získaj uzivatela podla ID")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id){
        UserResponseDTO user = userService.getUser(id);
        return user != null ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    @Operation(summary = "Získaj zoznam všetkých uzivatelov")
    public ResponseEntity<List<UserResponseDTO>> getUsers(){
        List<UserResponseDTO> users = userService.getUsers();
        return users != null ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user-name/{username}")
    @Operation(summary = "Získaj uzivatela podla ID")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username){
        UserResponseDTO user = userService.getUserByUsername(username);
        return user != null ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login")
    @Operation(summary = "Prihlásenie užívateľa")
    public ResponseEntity<UserResponseDTO> loginUser(@RequestBody LoginRequestDTO loginRequest) {
        UserResponseDTO userResponseDTO = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        return userResponseDTO != null ? ResponseEntity.ok(userResponseDTO) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PatchMapping("/update-password")
    @Operation(summary = "Zmena hesla používateľa")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateDTO passwordUpdateDTO) {
        if (passwordUpdateDTO.getUserId() == null) {
            return ResponseEntity.badRequest().body("User ID nesmie byť null");
        }
        userService.updatePassword(passwordUpdateDTO.getUserId(), passwordUpdateDTO.getCurrentPassword(), passwordUpdateDTO.getNewPassword());
        return ResponseEntity.ok("Heslo bolo úspešne zmenené.");
    }



}
