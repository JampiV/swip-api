package com.api.swip.dao;

import com.api.swip.dto.BienCentralDto;
import com.api.swip.entity.Bien;
import com.api.swip.entity.UnidadOrganica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBienRepo extends JpaRepository<Bien, Integer>
{
    Page<Bien> findByInventarioId(Integer id, Pageable pageable);
    Page<Bien> findAll(Specification<Bien> spec, Pageable pageable);


    @Query("SELECT new com.api.swip.dto.BienCentralDto(b, u.nombreUnidad) " +
            "FROM Bien b " +
            "LEFT JOIN b.inventario i " +
            "LEFT JOIN UnidadOrganica u ON u.inventario.id = i.id " + // Agrega un espacio aquí
            "WHERE b.nombreDescriptivo LIKE %:nombreDescriptivo%")
    Page<BienCentralDto> findBienByInventarioU(@Param("nombreDescriptivo") String spec, Pageable pageable);

    @Query("SELECT new com.api.swip.dto.BienCentralDto(b, u.nombreUnidad) " +
            "FROM Bien b " +
            "LEFT JOIN b.inventario i " +
            "LEFT JOIN UnidadOrganica u ON u.inventario.id = i.id ")
    Page<BienCentralDto> findBienByInventarioU(Pageable pageable);

}
