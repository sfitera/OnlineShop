package org.dreamteam.onlineshop.model.DTOs;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;

}
