package com.api.swip.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Bien
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombreDescriptivo;

    private String marca;

    private String modelo;

    private String serie;

    private String fecIngreso;

    private String fecActualizacion;

    private String documentoAlta;

    private String documentoBaja;

    private String sitBinv;

    private Double valorAdquisicion;

    private Double valorActual;

    private Double valorActualDos;

    private String estado;

    @JsonBackReference()
    @ManyToOne()
    @JoinColumn(name = "id_inventario", foreignKey = @ForeignKey(name = "FK_inventario_bienes"))
    private Inventario inventario;

}