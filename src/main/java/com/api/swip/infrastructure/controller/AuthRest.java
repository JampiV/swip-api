package com.api.swip.infrastructure.controller;

import com.api.swip.dao.IUserRepo;
import com.api.swip.dto.LoginRequest;
import com.api.swip.dto.RegisterRequest;
import com.api.swip.entity.Role;
import com.api.swip.entity.User;
import com.api.swip.entity.UserLocal;
import com.api.swip.security.JwUtil;
import com.api.swip.service.ITokenService;
import com.api.swip.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRest {

    @Autowired
    private IUserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwUtil jwUtil;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private IUserRepo userRepo;

    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegisterRequest registerRequest) throws Exception {

        User user = new User();
        user.setCodigo(registerRequest.getCodigo());
        user.setContrasenia(registerRequest.getContrasenia());
        user.setCargoPolicial(registerRequest.getCargoPolicial());
        user.setRole(registerRequest.getRole());
        // Configuración del usuario
        User userCreated = userService.registrarUsuario(user);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){

        Authentication auth = authenticate(loginRequest.getCodigo(), loginRequest.getContrasenia());

        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        // Invalidar tokens anteriores
        User user = userRepo.findByCodigo(loginRequest.getCodigo()).orElseThrow();

        // Crear o actualizar el token
        String token = jwUtil.generateToken(userDetails);
        tokenService.createOrUpdateToken(user, token, jwUtil.extractExpiration(token));

        System.out.println(token);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);

        return ResponseEntity.ok(tokenMap);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {
        String tokenHeader = request.getHeader("Authorization");
        String token = null;
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            token = tokenHeader.substring(7);
        }

        if (token != null) {
            String username = jwUtil.getUsernameFromToken(token);
            User user = userRepo.findByCodigo(username).orElseThrow();
            tokenService.invalidateOldToken(user);
        }
        return ResponseEntity.ok("Sesión cerrada con éxito");
    }

    private Authentication authenticate(String username, String password)
    {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    /*
    @GetMapping("holamundo")
    public ResponseEntity<Integer> findillo() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer idcillo = userService.findIdUserByUsercode(username);
        //List<UserLocal> userLocals = service.readAll();

        return new ResponseEntity<>(idcillo, HttpStatus.OK);
    }*/
}
