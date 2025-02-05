package org.dreamteam.onlineshop.model.DTOs;

import lombok.Data;
import org.dreamteam.onlineshop.model.enums.UserRole;

@Data
public class UserDTO {
    private String userName;
    private String userPassword;
    private String userAddress;
    private String userEmail;
    private UserRole userRole;
}
