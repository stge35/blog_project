package com.blog_project.server.global.auth.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomAuthorityUtils {

    @Value("${mail.address.admin}")
    private String adminMailAddress;

    private final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
    private final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");
    private final List<String> ADMIN_ROLE_STRINGS = List.of("ADMIN", "USER");
    private final List<String> USER_ROLE_STRINGS = List.of("USER");

    public List<GrantedAuthority> createAuthorities(String email) {

        if(email.equals(adminMailAddress)) {
            return ADMIN_ROLES;
        }

        return USER_ROLES;
    }

    public List<GrantedAuthority> createAuthorities(List<String> roles) {

        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("Role_" + role))
                .collect(Collectors.toList());

        return authorities;
    }

    public List<String> createRoles(String email) {

        if(email.equals(adminMailAddress)){
            return ADMIN_ROLE_STRINGS;
        }

        return USER_ROLE_STRINGS;
    }

    public boolean isAdmin(UserDetails userDetails) {

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        return authorities.stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }
}
