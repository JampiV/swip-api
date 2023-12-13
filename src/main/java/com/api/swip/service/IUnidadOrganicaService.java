package com.api.swip.service;

import com.api.swip.entity.UnidadOrganica;

import java.util.List;

public interface IUnidadOrganicaService
{
    UnidadOrganica save(UnidadOrganica unidadOrganica) throws Exception;

    UnidadOrganica update(UnidadOrganica unidadOrganica, Integer id) throws Exception;

    UnidadOrganica readById(Integer id) throws Exception;

    List<UnidadOrganica> readAll() throws Exception;

    void delete(Integer id) throws Exception;

    void deleteByIdWithoutBienes(Integer id) throws Exception;

    /*Integer findIdOrganicaByIdUser(Integer id) throws Exception;*/
}
