package com.api.swip.service;

import com.api.swip.entity.Bien;

import java.util.List;

public interface IBienService
{
    Bien save(Bien bienes) throws Exception;
    Bien update(Bien bienes, Integer id) throws Exception;
    Bien readById(Integer id) throws Exception;
    List<Bien> readAll() throws Exception;
    void delete(Integer id) throws Exception;
    List<Bien> findByInventarioId(Integer id);
    /*
    List<Bien> findByUserCode(String username);*/
}
