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
    //@Email
    //@Size(max = 50)
    private String email;
    @Column(name = "password", unique = true, nullable = false, length = 60)
    //@Size(min = 6, max = 60)
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    //@Size(min = 3, max = 30)
    private String surname;
    @Column(name = "phoneNumber", unique = true)
   // @Size(min = 3, max = 30)
    private String phoneNumber;
    @Column(name = "active")
    private Boolean active;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(name = "createdAt", columnDefinition = "DATETIME")
    private LocalDateTime createdAt;
    @Column(name = "role")
    private String roles;

    public User(){
        createdAt = LocalDateTime.now();
    }



}
