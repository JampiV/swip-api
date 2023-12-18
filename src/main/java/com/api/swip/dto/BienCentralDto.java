package com.api.swip.dto;

import com.api.swip.entity.Bien;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BienCentralDto implements Serializable {
    private Bien bien;
    private String nombre_unidad;
}
