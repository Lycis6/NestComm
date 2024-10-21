//package org.example.nestcomm.models;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import org.example.nestcomm.models.enums.Role;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.time.LocalDateTime;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
//@Data
//@AllArgsConstructor
//public class User implements UserDetails {
//    private Long ID;
//    private String email;
//    private String password;
//    private String phoneNumber;
//    private Boolean active ;
//    private Set<Role> roles = new HashSet<>();
//    final private LocalDateTime createdAt;
//
//    User(){
//        createdAt = LocalDateTime.now();
//    }
//
//    // security
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return active;
//    }
//}
