package com.api.swip.service.impl;

import com.api.swip.dao.IUserInventariadorRepo;
import com.api.swip.entity.Token;
import com.api.swip.entity.UserInventariador;
import com.api.swip.exception.ModelNotFoundException;
import com.api.swip.service.ITokenService;
import com.api.swip.service.IUserInventariadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInventariadorServiceImpl implements IUserInventariadorService
{
    private final IUserInventariadorRepo repo;
    private final PasswordEncoder encoder;
    private final ITokenService tokenService;

    @Override
    public UserInventariador save(UserInventariador userInventariador) throws Exception {
        userInventariador.setContrasenia(encoder.encode(userInventariador.getContrasenia()));
        return repo.save(userInventariador);
    }

    @Override
    public UserInventariador update(UserInventariador userInventariador, Integer id) throws Exception {
        repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return repo.save(userInventariador);
    }

    @Override
    public UserInventariador readById(Integer id) throws Exception {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public List<UserInventariador> readAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public void delete(Integer id) throws Exception {
        Token token = tokenService.findByUserId(id);

        if(token != null) {
            tokenService.delete(token.getId());
        }

        repo.deleteById(id);
    }
}