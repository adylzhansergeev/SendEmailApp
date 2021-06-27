package com.example.SendEmailApp.models.entities;

import com.example.SendEmailApp.models.basics.BasicModel;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role extends BasicModel{
    public final static Long ROLE_ADMIN_ID = 1L;
    public final static Long ROLE_USER_ID = 2L;

    public final static String ROLE_ADMIN_NAME = "ROLE_ADMIN";
    public final static String ROLE_USER_NAME = "ROLE_USER";

    @Column(name = "name", unique = true, nullable = false)
    private String name;

}
