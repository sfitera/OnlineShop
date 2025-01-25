package org.dreamteam.onlineshop.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.dreamteam.onlineshop.model.enums.UserRole;

@Builder
@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    private Long id;
    private User user;
    private String userName;
    private String userPassword;
    private String userAddress;
    private String userEmail;
    private UserRole userRole;


}
