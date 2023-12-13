package com.api.swip.service;

import com.api.swip.entity.UnidadInventario;

import java.util.List;

public interface IUnidadInventarioService
{
    UnidadInventario save(UnidadInventario unidadInventario) throws Exception;

    UnidadInventario update(UnidadInventario unidadInventario, Integer id) throws Exception;

    UnidadInventario readById(Integer id) throws Exception;

    List<UnidadInventario> readAll() throws Exception;

    void delete(Integer id) throws Exception;

    void deleteByIdWithoutBienes(Integer id) throws Exception;
}
