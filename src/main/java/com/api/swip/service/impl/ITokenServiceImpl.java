package com.api.swip.service.impl;

import com.api.swip.dao.ITokenRepo;
import com.api.swip.entity.Token;
import com.api.swip.entity.User;
import com.api.swip.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ITokenServiceImpl implements ITokenService {
    @Autowired
    private ITokenRepo tokenRepo;

    @Override
    public void invalidateOldToken(User user) {
        List<Token> activeToken = tokenRepo.findByUserIdAndIsActive(user.getId(), true);
        for(Token token: activeToken){
            token.setIsActive(false);
            tokenRepo.save(token);
        }
    }

    @Override
    public Token createNewToken(User user, String tokenString, Date expiryDate) {
        Token newToken = new Token();
        newToken.setUser(user);
        newToken.setToken(tokenString);
        newToken.setCreationDate(new Date());
        newToken.setExpiryDate(expiryDate);
        newToken.setIsActive(true);
        return tokenRepo.save(newToken);
    }

    @Override
    public boolean isTokenActive(String tokenString, User user) {
        return tokenRepo.findByUserIdAndIsActive(user.getId(), true)
                .stream()
                .anyMatch(token -> token.getToken().equals(tokenString) && token.getIsActive());
    }

    @Override
    public Token createOrUpdateToken(User user, String tokenString, Date expiryDate) {
        Token token = tokenRepo.findByUser(user).orElse(new Token());
        token.setUser(user);
        token.setToken(tokenString);
        token.setCreationDate(new Date());
        token.setExpiryDate(expiryDate);
        token.setIsActive(true);
        return tokenRepo.save(token);
    }

    @Override
    public Token findByUserId(Integer userId) {
        return tokenRepo.findByUserId(userId);
    }

    @Override
    public void delete(Integer id) throws Exception {
        tokenRepo.findById(id).orElseThrow(() -> new Exception("ID NOT FOUND: " + id));
        tokenRepo.deleteById(id);
    }

}
