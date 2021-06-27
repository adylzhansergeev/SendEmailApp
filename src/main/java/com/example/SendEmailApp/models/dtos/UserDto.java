package com.example.SendEmailApp.models.dtos;

import com.example.SendEmailApp.models.entities.Role;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String name;

    private String surname;

    private String username;

    private String password;

    private String email;

    private Set<RoleDto> roleDtos = new HashSet<>();
}
