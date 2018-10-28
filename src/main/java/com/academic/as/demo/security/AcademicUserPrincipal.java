package com.academic.as.demo.security;

import com.academic.as.demo.models.AuthGroup;
import com.academic.as.demo.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class AcademicUserPrincipal implements UserDetails {

    private User user;
    private List<AuthGroup> authGroups;

    public AcademicUserPrincipal(User user, List<AuthGroup> authGroups) {
        super();
        this.user = user;
        this.authGroups = authGroups;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authGroups == null) {
            return Collections.emptySet();
        }
        Set<SimpleGrantedAuthority> grantedAuthoritySet = new HashSet<>();
        authGroups.forEach(authGroup -> {
            grantedAuthoritySet.add(new SimpleGrantedAuthority(authGroup.getAuthGroup()));
        });
        return grantedAuthoritySet;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
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
