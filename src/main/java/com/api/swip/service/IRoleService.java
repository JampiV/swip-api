package com.api.swip.service;

import com.api.swip.entity.Role;

import java.util.List;

public interface IRoleService
{
    Role save(Role role) throws Exception;

    Role update(Role role, Integer id) throws Exception;

    Role readById(Integer id) throws Exception;

    List<Role> readAll() throws Exception;

    void delete(Integer id) throws Exception;
}
