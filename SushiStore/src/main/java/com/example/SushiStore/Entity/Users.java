package com.example.SushiStore.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Email")
    private String email;

    @Column(name = "Fullname")
    private String fullname;

    @Column(name = "Password")
    private String password;

    @Column(name = "phoneNumber")
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Roles> roles;
}
