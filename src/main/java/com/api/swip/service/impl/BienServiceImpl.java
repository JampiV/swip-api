package com.api.swip.service.impl;


import com.api.swip.dao.IBienRepo;
import com.api.swip.dao.IUserLocalRepo;
import com.api.swip.dto.BienCentralDto;
import com.api.swip.dto.especification.BienEspecification;
import com.api.swip.entity.Bien;
import com.api.swip.exception.ModelNotFoundException;
import com.api.swip.service.IBienService;
import com.api.swip.validators.BienValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

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

        bienes.setFecActualizacion(LocalDateTime.now().toString());
        return repo.save(bienes);
    }

    @Override
    public Bien readById(Integer id) throws Exception {
        return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NO ENCONTRADO: " + id));
    }

    @Override
    public Page<Bien> readAll(Pageable pageable) throws Exception {
        return repo.findAll(pageable);
    }

    @Transactional
    @Override
    public void delete(Integer id) throws Exception {
        repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NO ENCONTRADO: " + id));
        repo.deleteById(id);
    }

    // Método original sin cambios
    @Override
    public Page<Bien> findByInventarioId(Integer id, Pageable pageable) {
        return repo.findByInventarioId(id, pageable);
    }

    // Método sobrecargado con filtro
    @Override
    public Page<Bien> findByInventarioId(Integer id, Pageable pageable, String filter) {
        if (filter == null || filter.isEmpty()) {
            return repo.findByInventarioId(id, pageable);
        } else {
            Specification<Bien> spec = BienEspecification.withFilterAndInventarioId(filter, id);
            return repo.findAll(spec, pageable);
        }
    }

    @Override
    public Page<BienCentralDto> findAllBienesWithUnidad(String filter, Pageable pageable) {
        if (filter == null || filter.isEmpty()) {
            throw new  ModelNotFoundException("BÚSQUEDA VACÍA");
        } else
        {
            return repo.findBienByInventarioU(filter, pageable);
        }
    }

    @Override
    public Page<BienCentralDto> findAllBienesWithUnidad(Pageable pageable) {
        return repo.findBienByInventarioU(pageable);
    }

    @Override
    public Bien updateEstadoBien(String estado, Integer id) throws Exception {
        Optional<Bien> bienOptional = repo.findById(id);
        Bien bien = bienOptional.get(); // Extract the Bien object
        bien.setEstado(estado);
        return repo.save(bien); // Now passing a Bien object
    }

}
