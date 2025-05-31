package com.mytasks.api.repositories;

import com.mytasks.api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    UserDetails findByUsername(String username);
}
