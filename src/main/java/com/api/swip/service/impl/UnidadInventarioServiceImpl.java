package com.api.swip.service.impl;

import com.api.swip.dao.IUnidadInventarioRepo;
import com.api.swip.entity.Bien;
import com.api.swip.entity.Inventario;
import com.api.swip.entity.UnidadInventario;
import com.api.swip.exception.ModelNotFoundException;
import com.api.swip.service.IBienService;
import com.api.swip.service.IInventarioService;
import com.api.swip.service.IUnidadInventarioService;
import com.api.swip.validators.UnidadInventarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnidadInventarioServiceImpl implements IUnidadInventarioService
{
    private final IUnidadInventarioRepo repo;
    private final IInventarioService inventarioService;
    private final IBienService bienService;

    @Override
    public UnidadInventario save(UnidadInventario unidadInventario) throws Exception {
        UnidadInventarioValidator.validate(unidadInventario);
        return repo.save(unidadInventario);
    }

    @Override
    public UnidadInventario update(UnidadInventario unidadInventario, Integer id) throws Exception {
        UnidadInventarioValidator.validate(unidadInventario);
        repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return repo.save(unidadInventario);
    }

    @Override
    public UnidadInventario readById(Integer id) throws Exception {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public List<UnidadInventario> readAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        repo.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteByIdWithoutBienes(Integer id) throws Exception {
        Inventario inv = inventarioService.readById(id);
        List<Bien> bienes = bienService.findByInventarioId(id);
        if (bienes.size() == 0) {
            bienes.forEach(bien -> {
                bien.setInventario(null);
                try {
                    bienService.save(bien);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

        repo.deleteById(id);
    }
}
