package com.api.swip.validators;

import com.api.swip.entity.UnidadInventario;
import com.api.swip.exception.ResourceValidationException;

public class UnidadInventarioValidator
{

    public static void validate(UnidadInventario obj)
    {
        if (obj == null)
        {
            throw new ResourceValidationException("La unidad de inventario no puede ser nula");
        }

        if (obj.getInventario() == null)
        {
            throw new ResourceValidationException("El inventario no pude ser nulo");
        }

        if (obj.getUbicacionPolitica() == null || obj.getUbicacionPolitica().isEmpty())
        {
            throw new ResourceValidationException("La Ubicación política de inventario no puede ser nulo o vacío");
        }

        if (obj.getUbicacionGeografica() == null || obj.getUbicacionGeografica().isEmpty())
        {
            throw new ResourceValidationException("La Ubicación geográfica no puede ser nulo o vacío");
        }
    }
}
