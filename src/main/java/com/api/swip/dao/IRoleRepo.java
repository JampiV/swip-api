package com.api.swip.dao;

import com.api.swip.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepo extends JpaRepository<Role, Integer>
{

}
