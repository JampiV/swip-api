package com.api.swip.service.impl;

import com.api.swip.dao.IUserLocalRepo;
import com.api.swip.dao.IUserRepo;
import com.api.swip.entity.UserLocal;
import com.api.swip.exception.ModelNotFoundException;
import com.api.swip.service.IUserLocalService;
import com.api.swip.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserLocalServiceImpl implements IUserLocalService
{
    private final IUserLocalRepo repo;
    private final IUserRepo userRepo;
    private final IUserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserLocal save(UserLocal userLocal) throws Exception {
        String encryptedPassword = passwordEncoder.encode(userLocal.getContrasenia());
        userLocal.setContrasenia(encryptedPassword);
        return repo.save(userLocal);
    }

    @Override
    public UserLocal update(UserLocal userLocal, Integer id) throws Exception {
        repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return repo.save(userLocal);
    }

    @Override
    public UserLocal readById(Integer id) throws Exception {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public List<UserLocal> readAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        repo.deleteById(id);
    }

    @Override
    public Integer obtenerInventarioId(String usercode) throws Exception {
        Integer id_usuario = userService.findIdUserByUsercode(usercode);
        Optional<UserLocal> userLocal = repo.findById(id_usuario);
        Integer inventarioId = userLocal.get().getUnidadOrganica().getInventario().getId();
        return inventarioId;
    }

}
