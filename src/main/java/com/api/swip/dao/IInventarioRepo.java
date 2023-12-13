package com.api.swip.dao;

import com.api.swip.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInventarioRepo extends JpaRepository<Inventario, Integer>
{

}
