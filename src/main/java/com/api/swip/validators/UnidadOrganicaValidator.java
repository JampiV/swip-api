package com.api.swip.validators;

import com.api.swip.entity.UnidadOrganica;
import com.api.swip.exception.ResourceValidationException;

public class UnidadOrganicaValidator
{
    public static void validate(UnidadOrganica obj)
    {
        if (obj == null)
        {
            throw new ResourceValidationException("La unidad orgánica no puede ser nula");
        }

        if (obj.getUbicacionGeografica() == null || obj.getUbicacionGeografica().isEmpty())
        {
            throw new ResourceValidationException("La ubicación geográfica no puede ser nulo o vacío");
        }

        if (obj.getUbicacionPolitica() == null || obj.getUbicacionPolitica().isEmpty())
        {
            throw new ResourceValidationException("La ubicación política n de la unidad orgánica no puede ser nulo o vacío");
        }
    }
}
