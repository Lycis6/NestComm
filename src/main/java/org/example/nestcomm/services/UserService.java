package org.example.nestcomm.services;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.example.nestcomm.models.Role;
import org.example.nestcomm.models.Image;
import org.example.nestcomm.models.User;
//import org.example.nestcomm.models.enums.RoleEnum;
import org.example.nestcomm.repositories.ImageRepositoryInt;
import org.example.nestcomm.repositories.UserRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public void createUser(User user) {
        String email = user.getEmail();
        if(userRepository.findByEmail(email).isPresent()) {
            log.info("User with email {} already exists", email);
            return;
        }
        user.setActive(true);
        user.setRoles("USER");
        log.info("User created: {}", email);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void updateUser(User userCurrent,User userUpdated, MultipartFile file) throws IOException {
        log.info("Updating user: {}", userCurrent.getEmail());
        if(!file.isEmpty()){
            Image image;
            image = ToImageEntity(file);
            userCurrent.setImage(image);
        }
        userCurrent.setName(userUpdated.getName());
        userCurrent.setSurname(userUpdated.getSurname());
        userCurrent.setPhoneNumber(userUpdated.getPhoneNumber());
        User user = userRepository.save(userCurrent);
        userCurrent.setImage(user.getImage());
        userRepository.save(userCurrent);

        log.info("User updated: {}", userCurrent.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        return user.map(org.example.nestcomm.configurations.UserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + "not found"));

    }

    Image ToImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setData(file.getBytes());
        return image;
    }
}
