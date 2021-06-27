package com.example.SendEmailApp.models.entities;

import com.example.SendEmailApp.models.basics.BasicModel;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends BasicModel {


    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;


    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column(name = "locked")
    private Boolean locked = false;

    @Column(name = "enabled")
    private Boolean enabled = false;
}
