package com.api.swip.dao;

import com.api.swip.entity.UnidadInventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUnidadInventarioRepo extends JpaRepository<UnidadInventario, Integer>
{

}
