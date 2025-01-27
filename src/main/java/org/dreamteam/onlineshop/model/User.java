package org.dreamteam.onlineshop.model;


import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String userPassword;
    private String userAddress;
    private String userEmail;
    private UserRole userRole;


}
