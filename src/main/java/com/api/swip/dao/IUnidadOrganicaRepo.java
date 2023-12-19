package com.api.swip.dao;

import com.api.swip.entity.UnidadOrganica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUnidadOrganicaRepo extends JpaRepository<UnidadOrganica, Integer>
{
    // En tu interfaz de repositorio, puedes usar Specifications
    Page<UnidadOrganica> findAll(Specification<UnidadOrganica> spec, Pageable pageable);

}
