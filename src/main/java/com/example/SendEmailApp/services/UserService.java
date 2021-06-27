package com.example.SendEmailApp.services;

import com.example.SendEmailApp.exceptions.ServiceException;
import com.example.SendEmailApp.models.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;
public interface UserService extends UserDetailsService {
    List<User> findAll();
    User findById(Long id) throws ServiceException;
    User findByEmail(String email) throws ServiceException;
    User update(User user) throws ServiceException ;
    User save(User user) throws ServiceException ;
    void delete(User user) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;
}
