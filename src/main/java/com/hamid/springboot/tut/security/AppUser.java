package com.hamid.springboot.tut.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Date;

/**
 * @author Hamid Ait Brahim
 * @Created 17/01/2020
 */
@Document(collection = "users")
@Data
@NoArgsConstructor
public class AppUser implements UserDetails {

    @Id
    private String id;
    @NotEmpty
    private String email;
    @NotEmpty
    @JsonIgnore
    private String password;

    @NotEmpty
    private String name;

    private Date created;

    public AppUser(@NotEmpty String email, @NotEmpty String password, @NotEmpty String name)
    {
        this.email = email;
        this.password = password;
        this.name = name;
        this.created = new Date();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
