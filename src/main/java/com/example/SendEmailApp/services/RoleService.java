package com.example.SendEmailApp.services;

import com.example.SendEmailApp.exceptions.ServiceException;
import com.example.SendEmailApp.models.entities.Role;
import java.util.List;
public interface RoleService {
    Role findById(Long id) throws ServiceException;
    List<Role> findAll();
    List<Role> findAllWithDeleted();
    Role update(Role role) throws ServiceException ;
    Role save(Role role) throws ServiceException ;
    void delete(Role role) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;
}
