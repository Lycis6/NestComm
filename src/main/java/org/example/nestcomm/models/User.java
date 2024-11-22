package org.example.nestcomm.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.example.nestcomm.dto.UserDto;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.processing.Pattern;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data

public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long ID;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", unique = true, nullable = false, length = 60)
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "phoneNumber", unique = true)
    private String phoneNumber;
    @Column(name = "active")
    private Boolean active;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(name = "createdAt", columnDefinition = "DATETIME")
    private LocalDateTime createdAt;
    @Column(name = "lastLogin", columnDefinition = "DATETIME")
    private LocalDateTime lastLogin;
    @Column(name = "role")
    private String roles;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Product> products = new ArrayList<>();

    public User(){
        createdAt = LocalDateTime.now();
    }

    public boolean isAdmin(){
        return roles.contains("ADMIN");
    }

    public boolean isAuthor(){
        return roles.contains("AUTHOR");
    }

    public boolean isRegistered(){
        return email != null && password != null;
    }

    // возвращает true, если заполнены данные пользователя
    public boolean isApproved(){
        return name != null && surname != null && phoneNumber != null;
    }

    public String isActive(){
        return active?"active":"inactive";
    }

    public String getRegistrationTime(){
        if(createdAt != null)
            return createdAt.toString();
        return "none";
    }

    public void transferDtoToModel(UserDto dto){
        if(dto.getEmail() != null) this.email = dto.getEmail();
        if(dto.getPassword() != null) this.password = dto.getPassword();
        if(dto.getName() != null) this.name = dto.getName();
        if(dto.getSurname() != null) this.surname = dto.getSurname();
        if(dto.getPhoneNumber() != null) this.phoneNumber = dto.getPhoneNumber();
    }

}
