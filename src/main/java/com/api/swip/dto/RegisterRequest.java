package com.api.swip.dto;

import com.api.swip.entity.Role;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements Serializable {
    private String codigo;
    private String contrasenia;
    private String cargoPolicial;
    private Role role;
}
