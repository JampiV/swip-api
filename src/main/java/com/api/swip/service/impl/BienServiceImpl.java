package com.api.swip.service.impl;


import com.api.swip.dao.IBienRepo;
import com.api.swip.dao.IUserLocalRepo;
import com.api.swip.entity.Bien;
import com.api.swip.entity.UnidadOrganica;
import com.api.swip.entity.UserLocal;
import com.api.swip.exception.ModelNotFoundException;
import com.api.swip.service.IBienService;
import com.api.swip.validators.BienValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BienServiceImpl implements IBienService
{
    private final IBienRepo repo;
    private final IUserLocalRepo userLocalRepo;

    @Transactional
    @Override
    public Bien save(Bien bienes) throws Exception {
        BienValidator.validate(bienes);
        return repo.save(bienes);
    }

    @Transactional
    @Override
    public Bien update(Bien bienes, Integer id) throws Exception {
        BienValidator.validate(bienes);
        repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NO ENCONTRADO: " + id));
        return repo.save(bienes);
    }

    @Override
    public Bien readById(Integer id) throws Exception {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NO ENCONTRADO: " + id));
    }

    @Override
    public List<Bien> readAll() throws Exception {
        return repo.findAll();
    }

    @Transactional
    @Override
    public void delete(Integer id) throws Exception {
        repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NO ENCONTRADO: " + id));
        repo.deleteById(id);
    }

    @Override
    public List<Bien> findByInventarioId(Integer id) {
        return repo.findByInventarioId(id);
    }
    
}
