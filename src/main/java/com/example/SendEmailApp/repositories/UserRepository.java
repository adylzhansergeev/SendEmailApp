package com.example.SendEmailApp.repositories;

import com.example.SendEmailApp.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndDeletedAtIsNotNull(String email);
    List<User> findAllByDeletedAtIsNull();
    User findByIdAndDeletedAtIsNull(Long id);
}
