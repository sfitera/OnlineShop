package org.dreamteam.onlineshop.model.DTOs;

import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String userAddress;
    private String userEmail;
    @Setter
    private List<String> roles;

}
