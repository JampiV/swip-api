package com.api.swip.service.impl;

import com.api.swip.dao.IUnidadOrganicaRepo;
import com.api.swip.entity.Bien;
import com.api.swip.entity.Inventario;
import com.api.swip.entity.UnidadOrganica;
import com.api.swip.exception.ModelNotFoundException;
import com.api.swip.service.IBienService;
import com.api.swip.service.IInventarioService;
import com.api.swip.service.IUnidadOrganicaService;
import com.api.swip.validators.UnidadOrganicaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnidadOrganicaServiceImpl implements IUnidadOrganicaService
{
    private final IUnidadOrganicaRepo repo;
    private final IInventarioService inventarioService;
    private final IBienService bienService;

    @Transactional
    @Override
    public UnidadOrganica save(UnidadOrganica unidadOrganica) throws Exception {
        UnidadOrganicaValidator.validate(unidadOrganica);
        return repo.save(unidadOrganica);
    }

    @Override
    public UnidadOrganica update(UnidadOrganica unidadOrganica, Integer id) throws Exception {
        UnidadOrganicaValidator.validate(unidadOrganica);
        repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NO ENCONTRADO: " + id));
        return repo.save(unidadOrganica);
    }

    @Override
    public UnidadOrganica readById(Integer id) throws Exception {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NO ENCONTRADO: " + id));
    }

    @Override
    public Page<UnidadOrganica> readAll(Pageable pageable) throws Exception {
        return repo.findAll(pageable);
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NO ENCONTRADO: " + id));
        repo.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteByIdWithoutBienes(Integer id) throws Exception {
        Inventario inv = inventarioService.readById(id);
        Page<Bien> pageBienes = bienService.findByInventarioId(id, Pageable.unpaged());
        List<Bien> bienes = pageBienes.getContent();
        if (!bienes.isEmpty()) {
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
