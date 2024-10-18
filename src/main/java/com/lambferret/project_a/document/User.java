package com.lambferret.project_a.document;

import com.lambferret.project_a.security.core.CustomAuthority;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Builder
public class User implements UserDetails {

    @Id
    private ObjectId id;
    private String companyName;
    private String password;
    @CreatedDate
    private LocalDateTime createdDate;
    private LocalDateTime expirationDate;

    private boolean doesTestCICD;
    private boolean doesTestMalfunction;
    private final List<CustomAuthority.Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new CustomAuthority(role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return companyName;
    }
}
