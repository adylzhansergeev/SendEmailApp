package com.example.SendEmailApp.controllers;

import com.example.SendEmailApp.exceptions.ServiceException;
import com.example.SendEmailApp.models.UserDetailsImpl;
import com.example.SendEmailApp.models.dtos.UserDto;
import com.example.SendEmailApp.models.entities.Role;
import com.example.SendEmailApp.models.entities.User;
import com.example.SendEmailApp.models.mappers.UserMapper;
import com.example.SendEmailApp.services.EmailService;
import com.example.SendEmailApp.services.UserService;
import com.example.SendEmailApp.shared.security.jwt.JwtUtils;
import com.example.SendEmailApp.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private UserMapper userMapper;
    private EmailService emailService;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserMapper userMapper, EmailService emailService){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userMapper = userMapper;
        this.emailService = emailService;
    }

    @PostMapping("sign-in")
    public ResponseEntity<?> login(@RequestBody UserDto userDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDto.getEmail(),
                userDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new ResponseEntity<>( jwt , HttpStatus.OK);
    }
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) throws ServiceException, MessagingException {
        User user = userMapper.toEntity(userDto);
        if (userService.findByEmail(user.getEmail()) != null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("email already exists")
                    .build();
        }
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setId(Role.ROLE_USER_ID);
        roles.add(role);
        user.setRoles(roles);

        emailService.send(user.getEmail());
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }
}
