package org.example.nestcomm.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@RequiredArgsConstructor


public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long ID;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", unique = true, nullable = false, length = 60)
    private String password;
    @Column(name = "phoneNumber", unique = true)
    private String phoneNumber;
    @Column(name = "active")
    private Boolean active ;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;


//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
//    private List<Role> roles = new ArrayList<>();

    @Column(name = "createdAt", columnDefinition = "DATETIME")
    private LocalDateTime createdAt;

    @Column(name = "role")
    private String roles;



}
