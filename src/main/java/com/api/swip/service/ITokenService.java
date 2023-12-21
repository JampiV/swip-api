package com.api.swip.service;

import com.api.swip.entity.Token;
import com.api.swip.entity.User;

import java.util.Date;

public interface ITokenService {
    void invalidateOldToken(User user);
    Token createNewToken(User user, String tokenString, Date expiryDate);
    boolean isTokenActive(String token, User user);
    Token createOrUpdateToken(User user, String tokenString, Date expiryDate);
    Token findByUserId(Integer userId);
    void delete(Integer id) throws Exception;
}
