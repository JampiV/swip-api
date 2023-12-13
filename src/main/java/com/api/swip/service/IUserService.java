package com.api.swip.service;

import com.api.swip.entity.User;

public interface IUserService {
    User registrarUsuario (User usuario) throws Exception;
    Integer findIdUserByUsercode(String usercode) throws Exception;
}
