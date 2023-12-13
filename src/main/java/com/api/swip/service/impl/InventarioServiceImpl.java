package com.api.swip.service.impl;

import com.api.swip.dao.IInventarioRepo;
import com.api.swip.entity.Inventario;
import com.api.swip.exception.ModelNotFoundException;
import com.api.swip.service.IInventarioService;
import com.api.swip.validators.InventarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioServiceImpl implements IInventarioService
{
    private final IInventarioRepo repo;

    @Transactional
    @Override
    public Inventario save(Inventario inventario) throws Exception {
        InventarioValidator.validate(inventario);
        return repo.save(inventario);
    }

    @Transactional
    @Override
    public Inventario update(Inventario inventario, Integer id) throws Exception {
        InventarioValidator.validate(inventario);
        repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NO ENCONTRADO: " + id));
        return repo.save(inventario);
    }

    @Override
    public Inventario readById(Integer id) throws Exception {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NO ENCONTRADO: " + id));
    }

    @Override
    public List<Inventario> readAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NO ENCONTRADO: " + id));
        repo.deleteById(id);
    }
}
