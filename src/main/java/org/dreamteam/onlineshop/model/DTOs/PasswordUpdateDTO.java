package org.dreamteam.onlineshop.model.DTOs;

import lombok.Data;

@Data
public class PasswordUpdateDTO {
    private Long userId;
    private String currentPassword;
    private String newPassword;
}
