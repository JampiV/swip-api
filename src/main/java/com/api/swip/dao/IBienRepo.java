package com.api.swip.dao;

import com.api.swip.entity.Bien;
import com.api.swip.entity.UnidadOrganica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBienRepo extends JpaRepository<Bien, Integer>
{
    List<Bien> findByInventarioId(Integer id);

   /* List<Bien> findByUnidadOrganica(UnidadOrganica unidadOrganica);*/
}
