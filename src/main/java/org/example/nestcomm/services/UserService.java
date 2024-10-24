package org.example.nestcomm.services;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.example.nestcomm.models.Role;
import org.example.nestcomm.models.User;
//import org.example.nestcomm.models.enums.RoleEnum;
import org.example.nestcomm.repositories.UserRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@NoArgsConstructor

public class UserService implements UserDetailsService {


    private UserRepositoryInt userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserService(UserRepositoryInt userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public boolean createUser(User user) {
        String email = user.getEmail();
        if(userRepository.findByEmail(email).isPresent()) {
            log.info("User with email {} already exists", email);
            return false;
        }
        user.setActive(true);
        user.setRoles("USER");
        log.info("User created: " + email);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean validateUserDetails(String login, String password) {
        if(!userRepository.findByEmail(login).isPresent()){
            log.info("User with email {} does not exist", login);
            return false;
        }
        User user = userRepository.findByEmail(login).get();
        log.info("Validating user: {}", login);

        if(!passwordEncoder.matches(password, user.getPassword())) {
            log.info("Password does not match: {}", login);
            return false;
        }
        log.info("User validated: {}", login);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        return user.map(org.example.nestcomm.configurations.UserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + "not found"));

    }
}
