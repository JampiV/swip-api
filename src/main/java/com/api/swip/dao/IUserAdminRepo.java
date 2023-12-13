package com.api.swip.dao;

import com.api.swip.entity.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserAdminRepo extends JpaRepository<UserAdmin, Integer>
{

}
