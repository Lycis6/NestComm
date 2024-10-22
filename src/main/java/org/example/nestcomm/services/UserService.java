package org.example.nestcomm.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.nestcomm.models.Role;
import org.example.nestcomm.models.User;
import org.example.nestcomm.models.enums.RoleEnum;
import org.example.nestcomm.repositories.UserRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepositoryInt userRepository;

    @Autowired
    UserService(UserRepositoryInt userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(User user) {
        String email = user.getEmail();
        if(userRepository.findByEmail(email) != null) {
            return false;
        }
        user.setActive(true);
        Role role = new Role();
        role.setRole(RoleEnum.USER);
        user.getRoles().add(role);
        log.info("User created: " + email);
        return true;
    }

}
