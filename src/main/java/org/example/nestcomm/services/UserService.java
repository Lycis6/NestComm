package org.example.nestcomm.services;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.example.nestcomm.models.Role;
import org.example.nestcomm.dto.UserDto;
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
import java.time.LocalDateTime;
import java.util.List;
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

    public List<User> getAllUsers(){
        if(userRepository.findAll().isEmpty()) {
            log.info("No Users found");
            // TODO написать exception
            return null;
        }
        return userRepository.findAll();
    }

    public boolean createUser(UserDto userDto) {
        User user = new User();
        user.transferDtoToModel(userDto);
        String email = user.getEmail();
        if(userRepository.findByEmail(email).isPresent()) {
            log.info("User with email {} already exists", email);
            // TODO написать exception
            return false;
        }
        user.setActive(true);
        user.setRoles("USER");
        log.info("User created: {}", email);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public void updateUser(User userCurrent,UserDto userDto, MultipartFile file) throws IOException {
        log.info("Updating user: {}", userCurrent.getEmail());
        User userUpdated = new User();
        userUpdated.transferDtoToModel(userDto);
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

    public boolean banUserWithEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()) {
            optionalUser.get().setActive(false);
            userRepository.save(optionalUser.get());
            log.info("User with email {} has been banned", email);
            return true;
        }
        // TODO написать exception
        return false;
    }

    public boolean unbanUserWithEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()) {
            optionalUser.get().setActive(true);
            userRepository.save(optionalUser.get());
            log.info("User with email {} has been unbanned", email);
            return true;
        }
        // TODO написать exception
        return false;
    }

    public void becameAuthor(User user) {
        user.setRoles(user.getRoles() + ", AUTHOR");
        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void changePassword(User user, UserDto userDto) {
        log.info("Changing password for user: {}", user.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    public void saveLoginDate(String email, LocalDateTime loginDate) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setLastLogin(loginDate);
            userRepository.save(user);
        }
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
