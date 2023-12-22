package com.api.swip.service;

import com.api.swip.entity.Bien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBienService
{
    Bien save(Bien bienes) throws Exception;
    Bien update(Bien bienes, Integer id) throws Exception;
    Bien readById(Integer id) throws Exception;
    Page<Bien> readAll(Pageable pageable) throws Exception;
    void delete(Integer id) throws Exception;
    Page<Bien> findByInventarioId(Integer id, Pageable pageable);
    Page<Bien> findByInventarioId(Integer id, Pageable pageable, String filter);
    public Page<?> findAllBienesWithUnidad(String filter, Pageable pageable);
    public Page<?> findAllBienesWithUnidad(Pageable pageable);
    Bien updateEstadoBien(String estado, Integer id) throws Exception;

}
