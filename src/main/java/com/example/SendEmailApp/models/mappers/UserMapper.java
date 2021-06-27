package com.example.SendEmailApp.models.mappers;

import com.example.SendEmailApp.models.basics.BasicModel;
import com.example.SendEmailApp.models.dtos.RoleDto;
import com.example.SendEmailApp.models.dtos.UserDto;
import com.example.SendEmailApp.models.entities.Role;
import com.example.SendEmailApp.models.entities.User;
import com.example.SendEmailApp.shared.utils.mappers.SuperMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Component
public class UserMapper implements SuperMapper<User, UserDto> {

    private ModelMapper modelMapper;
    private RoleMapper roleMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper, RoleMapper roleMapper){
        this.modelMapper = modelMapper;
        this.roleMapper = roleMapper;
    }
    @Override
    public UserDto toDto(User user) {
        UserDto userDto = modelMapper.map(user,UserDto.class);
        if (user.getRoles() != null){
            Set<RoleDto> roleDtoSet = new HashSet<>();
            for (Role role: user.getRoles()) {
                roleDtoSet.add(roleMapper.toDto(role));
            }
            userDto.setRoleDtos(roleDtoSet);
        }
        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        if (userDto.getRoleDtos() != null){
            Set<Role> roles = new HashSet<>();
            for (RoleDto roleDto: userDto.getRoleDtos()) {
                roles.add(roleMapper.toEntity(roleDto));
            }
            user.setRoles(roles);
        }
        return user;
    }

    @Override
    public List<UserDto> toDtoList(List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            userDtoList.add(toDto(user));
        }
        return userDtoList;
    }

    @Override
    public List<User> toEntityList(List<UserDto> eList) {
        List<User> userList = new ArrayList<>();
        for (UserDto userDto : eList) {
            userList.add(toEntity(userDto));
        }
        return userList;
    }
}
