package com.academic.as.demo.security;

import com.academic.as.demo.models.AuthGroup;
import com.academic.as.demo.models.User;
import com.academic.as.demo.repositories.AuthGroupRepository;
import com.academic.as.demo.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthGroupRepository authGroupRepository;

    public AcademicDetailsService(UserRepository userRepository, AuthGroupRepository authGroupRepository) {
        super();
        this.userRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user " + username + " is not found");
        }
        List<AuthGroup> authGroups = this.authGroupRepository.findByUsername(username);
        return new AcademicUserPrincipal(user, authGroups);
    }
}
