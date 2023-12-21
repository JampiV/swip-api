package com.api.swip.service;

import com.api.swip.entity.Inventario;
import com.api.swip.entity.UnidadOrganica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUnidadOrganicaService
{
    UnidadOrganica save(UnidadOrganica unidadOrganica) throws Exception;

    UnidadOrganica update(UnidadOrganica unidadOrganica, Integer id) throws Exception;

    UnidadOrganica readById(Integer id) throws Exception;

    //Page<UnidadOrganica> readAll(Pageable pageable) throws Exception;

    void delete(Integer id) throws Exception;

    void deleteByIdWithoutBienes(Integer id) throws Exception;

    public String getNameUnidad(Integer id) throws Exception;

    public Page<UnidadOrganica> readAll(String filter, Pageable pageable) throws Exception;

    String getNameForIdInventario(Integer id)throws Exception;

    Inventario getInventarioDeUnidadOrganicaForName(String name) throws Exception;
}
