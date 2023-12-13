package com.api.swip.service.impl;

import com.api.swip.dao.IUserAdminRepo;
import com.api.swip.entity.UserAdmin;
import com.api.swip.exception.ModelNotFoundException;
import com.api.swip.service.IUserAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAdminServiceImpl implements IUserAdminService
{
    private final IUserAdminRepo repo;


    @Override
    public UserAdmin save(UserAdmin userAdmin) throws Exception {
        return repo.save(userAdmin);
    }

    @Override
    public UserAdmin update(UserAdmin userAdmin, Integer id) throws Exception {
        repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return repo.save(userAdmin);
    }

    @Override
    public UserAdmin readById(Integer id) throws Exception {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public List<UserAdmin> readAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        repo.deleteById(id);
    }
}
