package org.dreamteam.onlineshop.model.DTOs;

import lombok.Data;
import org.dreamteam.onlineshop.model.enums.UserRole;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    private String userName;
    private String userPassword;
    private String userAddress;
    private String userEmail;
    private List<UserRole> userRoles = new ArrayList<>();

    public UserDTO() {
        this.userRoles.add(UserRole.USER);
    }
}
