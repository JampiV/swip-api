package com.api.swip.validators;

import com.api.swip.entity.Bien;
import com.api.swip.exception.ModelNotFoundException;
import com.api.swip.exception.ResourceValidationException;

public class BienValidator
{
    public static void validate(Bien bien)
    {
        if (bien == null)
        {
            throw new ModelNotFoundException("El bien es nulo");
        }

        if (bien.getDocumentoAlta() == null || bien.getDocumentoAlta().isEmpty())
        {
            throw new ResourceValidationException("El Documento de Alta del bien es nulo");
        }

        if (bien.getDocumentoBaja() == null || bien.getDocumentoBaja().isEmpty())
        {
            throw new ResourceValidationException("El Documento de baja del bien es nulo");
        }

        if(bien.getMarca() == null || bien.getMarca().isEmpty())
        {
            throw new ResourceValidationException("La marca del bien es nulo");
        }

        if (bien.getModelo() == null || bien.getModelo().isEmpty())
        {
            throw new ResourceValidationException("El modelo del bien es nulo");
        }

        if (bien.getNombreDescriptivo() == null || bien.getNombreDescriptivo().isEmpty())
        {
            throw new ResourceValidationException("El nombre descriptivo del bien es nulo");
        }

        if (bien.getValorActual() == null)
        {
            throw new ResourceValidationException("El valor actual del bien es nulo");
        }

        if (bien.getValorActualDos() == null)
        {
            throw new ResourceValidationException("El valor actual dos del bien es nulo");
        }

        if (bien.getValorAdquisicion() == null)
        {
            throw new ResourceValidationException("El valor adquisición del bien es nulo");
        }

        if (bien.getValorActual() < 0)
        {
            throw new ResourceValidationException("El valor actual del bien es menor a 0");
        }

        if (bien.getValorActualDos() < 0)
        {
            throw new ResourceValidationException("El valor actual dos del bien es menor a 0");
        }

        if (bien.getValorAdquisicion() < 0)
        {
            throw new ResourceValidationException("El valor adquisición del bien es menor a 0");
        }

        if (bien.getEstado() == null || bien.getEstado().isEmpty())
        {
            throw new ResourceValidationException("El nombre del bien es nulo");
        }
    }
}
