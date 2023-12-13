package com.api.swip.service.impl;

import com.api.swip.dao.IUserRepo;
import com.api.swip.entity.User;
import com.api.swip.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User registrarUsuario(User usuario) throws Exception {
        String encryptedPassword = passwordEncoder.encode(usuario.getContrasenia());
        usuario.setContrasenia(encryptedPassword);
        return userRepo.save(usuario);
    }

    @Override
        public Integer findIdUserByUsercode(String usercode) throws Exception {
        Integer id_User;
        Optional<User> user = userRepo.findByCodigo(usercode);
        id_User = user.get().getId();
        return id_User;
    }

}