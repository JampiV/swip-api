package com.api.swip.service;


import com.api.swip.entity.UserAdmin;

import java.util.List;

public interface IUserAdminService
{
    UserAdmin save(UserAdmin userAdmin) throws Exception;

    UserAdmin update(UserAdmin userAdmin, Integer id) throws Exception;

    UserAdmin readById(Integer id) throws Exception;

    List<UserAdmin> readAll() throws Exception;

    void delete(Integer id) throws Exception;
}
