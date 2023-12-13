package com.api.swip.dao;

import com.api.swip.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByCodigo(String codigo);
}
