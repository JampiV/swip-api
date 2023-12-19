package com.api.swip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BienCentralSelecDto implements Serializable {
    private Integer id;
    private String nombreDescriptivo;
    private String marca;
    private String modelo;
    private String serie;
    private String fecIngreso;
    private String fecActualizacion;
    private String documentoAlta;
    private String sitBinv;
    private Double valorAdquisicion;
    private Double valorActual;
    private Double valorActualDos;
    private String estado;
}
