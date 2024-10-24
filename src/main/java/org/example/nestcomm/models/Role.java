//package org.example.nestcomm.models;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//import org.example.nestcomm.models.enums.RoleEnum;
////import org.springframework.security.core.GrantedAuthority;
//
//@Entity
//@Table(name = "user_roles")
//@Data
//public class Role{
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private Long id;
//
//
//    @Enumerated(EnumType.ORDINAL)
//    private RoleEnum role;
//
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//}
