package com.api.swip.service;

import com.api.swip.entity.Inventario;

import java.util.List;

public interface IInventarioService
{
    Inventario save(Inventario inventariador) throws Exception;

    Inventario update(Inventario inventariador, Integer id) throws Exception;

    Inventario readById(Integer id) throws Exception;

    List<Inventario> readAll() throws Exception;
    void delete(Integer id) throws Exception;
}
