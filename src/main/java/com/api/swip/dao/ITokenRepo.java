package com.api.swip.dao;

import com.api.swip.entity.Token;
import com.api.swip.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ITokenRepo extends JpaRepository<Token, Integer> {
    List<Token> findByUserIdAndIsActive(Integer userId, Boolean isActive);

    Optional<Token> findByUser(User user);
}
