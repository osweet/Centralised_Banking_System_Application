package com.example.bma.service.security;

import com.example.bma.entity.RoleEntity;
import com.example.bma.entity.UserEntity;
import com.example.bma.repository.RoleRepository;
import com.example.bma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String userIdentifier) throws UsernameNotFoundException {
        if (isTextEmail(userIdentifier)) {
            UserEntity user = userRepository.findUserEntityByUserEmail(userIdentifier);
            if (user == null) {
                throw new BadCredentialsException("Invalid Email");
            } else {
                return new User(user.getUserId(),
                                user.getUserPassword(),
                                user.getUserEnabled(),
                                true, true, true,
                                getAuthorities(user.getUserRoles()));
            }
        }
        else {
            UserEntity user = userRepository.findUserEntityByUserId(userIdentifier);
            if (user == null) {
                throw new BadCredentialsException("Invalid Credentials");
            } else {
                return new User(user.getUserId(),
                                user.getUserPassword(),
                        user.getUserEnabled(), true, true, true,
                        getAuthorities(user.getUserRoles()));
            }
        }
    }

    private Set<SimpleGrantedAuthority> getAuthorities(Set<RoleEntity> roles) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(roleEntity -> authorities.add(
                new SimpleGrantedAuthority("ROLE_"+roleEntity.getRoleName())));
        return authorities;
    }

    private boolean isTextEmail(String text) {
        return text.contains("@");
    }
}
