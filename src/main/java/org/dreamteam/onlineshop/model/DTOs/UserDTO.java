package org.dreamteam.onlineshop.model.DTOs;

import lombok.Data;
import org.dreamteam.onlineshop.model.enums.UserRole;

@Data
public class UserDTO {
    private Long id;
    private String userName;
    private String userAddress;
    private String userEmail;
    private UserRole userRole;
}
