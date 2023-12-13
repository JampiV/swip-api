package com.api.swip.validators;


import com.api.swip.entity.Role;
import com.api.swip.exception.ModelNotFoundException;
import com.api.swip.exception.ResourceValidationException;

public class RoleValidator
{
  public static void validate(Role role)
  {
      if (role == null)
      {
        throw new ModelNotFoundException("El rol no puede ser nulo");
      }

        if (role.getName() == null || role.getName().isEmpty())
        {
            throw new ResourceValidationException("El nombre del rol no puede ser nulo o vacío");
        }

        if (role.getName().length() > 30) {
          throw new ResourceValidationException("El nombre del rol no puede tener más de 30 caracteres");
        }
  }
}
