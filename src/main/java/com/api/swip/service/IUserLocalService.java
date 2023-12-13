package com.api.swip.service;

import com.api.swip.entity.UserLocal;

import java.util.List;

public interface IUserLocalService 
{
    UserLocal save(UserLocal userLocal) throws Exception;

    UserLocal update(UserLocal userLocal, Integer id) throws Exception;

    UserLocal readById(Integer id) throws Exception;

    List<UserLocal> readAll() throws Exception;

    void delete(Integer id) throws Exception;
    Integer obtenerInventarioId(String usercode) throws Exception;

}
