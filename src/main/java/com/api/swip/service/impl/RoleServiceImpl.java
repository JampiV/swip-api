package com.api.swip.service.impl;

import com.api.swip.dao.IRoleRepo;
import com.api.swip.entity.Role;
import com.api.swip.exception.ModelNotFoundException;
import com.api.swip.service.IRoleService;
import com.api.swip.validators.RoleValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService
{
    private final IRoleRepo repo;


    @Override
    public Role save(Role role) throws Exception {
        RoleValidator.validate(role);
        return repo.save(role);
    }

    @Override
    public Role update(Role role, Integer id) throws Exception {
        RoleValidator.validate(role);
        repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NO ENCONTRADO: " + id));
        return repo.save(role);
    }

    @Override
    public Role readById(Integer id) throws Exception {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NO ENCONTRADO: " + id));
    }

    @Override
    public List<Role> readAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NO ENCONTRADO: " + id));
        repo.deleteById(id);
    }
}
