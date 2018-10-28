package com.academic.as.demo.security;

import com.academic.as.demo.models.User;
import com.academic.as.demo.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AcademicDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AcademicDetailsService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user " + username + " is not found");
        }
        return new AcademicUserPrincipal(user);
    }
}
