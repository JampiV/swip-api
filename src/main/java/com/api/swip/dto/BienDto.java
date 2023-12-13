package com.api.swip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BienDto implements Serializable {
    private Integer id;
    private String nombreDescriptivo;
    private String marca;
    private String modelo;
    private String serie;
    private String estado;
}
