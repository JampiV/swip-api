package com.api.swip.service;

import com.api.swip.entity.UserInventariador;

import java.util.List;

public interface IUserInventariadorService
{
    UserInventariador save(UserInventariador userInventariador) throws Exception;

    UserInventariador update(UserInventariador userInventariador, Integer id) throws Exception;

    UserInventariador readById(Integer id) throws Exception;

    List<UserInventariador> readAll() throws Exception;

    void delete(Integer id) throws Exception;
}
