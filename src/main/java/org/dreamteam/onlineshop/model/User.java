package org.dreamteam.onlineshop.model;


import jakarta.persistence.*;
import lombok.*;
import org.dreamteam.onlineshop.model.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String userPassword;
    private String userAddress;
    private String userEmail;
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private List<UserRole> userRoles = new ArrayList<>();

    public User(String userName, String userPassword, String userAddress, String userEmail) {
        this.username = userName;
        this.userPassword = userPassword;
        this.userAddress = userAddress;
        this.userEmail = userEmail;
        this.userRoles.add(UserRole.USER);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(UserRole userRole : userRoles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.name()));
        }
        return authorities;
    }

    public void setUserRole(UserRole userRole) {
        this.userRoles.add(userRole);
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
