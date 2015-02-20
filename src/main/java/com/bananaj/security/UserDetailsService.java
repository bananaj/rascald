package com.bananaj.security;

import com.bananaj.user.domain.User;
import com.bananaj.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            return null;
        }
        org.springframework.security.core.userdetails.UserDetails returnValue = null;
        List<User> users = userRepository.findByUsername(username);
        if (users != null && users.size() == 1) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
            grantedAuthorities.add(new GrantedAuthorityImpl("ROLE_USER"));
            returnValue = new org.springframework.security.core.userdetails.User(username, "password", true, true, true, true, grantedAuthorities);
        }
        return returnValue;
    }
}
