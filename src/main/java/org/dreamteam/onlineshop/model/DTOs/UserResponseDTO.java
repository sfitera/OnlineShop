package org.dreamteam.onlineshop.model.DTOs;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String userAddress;
    private String userEmail;
}
