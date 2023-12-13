package com.api.swip.validators;

import com.api.swip.entity.Inventario;
import com.api.swip.exception.ModelNotFoundException;

public class InventarioValidator
{
    public static void validate(Inventario inventario)
    {
        if (inventario == null)
        {
            throw new ModelNotFoundException("El inventario es nulo");
        }

        if (inventario.getBienes() == null || inventario.getBienes().isEmpty())
        {
            throw new ModelNotFoundException("El inventario no tiene bienes");
        }

        if (inventario.getFechaActualizacion() == null)
        {
            throw new ModelNotFoundException("La fecha de actualización es nula");
        }
    }
}
