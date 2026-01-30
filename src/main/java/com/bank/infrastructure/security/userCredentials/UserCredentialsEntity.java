package com.bank.infrastructure.security.userCredentials;

import com.bank.infrastructure.persistence.user.UserEntity;
import com.bank.infrastructure.security.UserDetails.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_credentials")
public class UserCredentialsEntity implements UserDetails {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    protected UserCredentialsEntity() {
        // JPA
    }

    public UserCredentialsEntity(UserEntity user,
                                 String passwordHash,
                                 Role role) {
        this.user = user;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    /* =========================
       UserDetails implementation
       ========================= */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + role.name())
        );
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
        return enabled;
    }

    /* =========================
       Getters métier / utiles
       ========================= */

    public UUID getUserId() {
        return userId;
    }

    public UserEntity getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }
}
