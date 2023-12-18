package com.api.swip.dao;

import com.api.swip.entity.UnidadOrganica;
import com.api.swip.entity.UserLocal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserLocalRepo extends JpaRepository<UserLocal, Integer>
{
}
