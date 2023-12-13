package com.api.swip.dao;

import com.api.swip.entity.UnidadOrganica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUnidadOrganicaRepo extends JpaRepository<UnidadOrganica, Integer>
{
}
