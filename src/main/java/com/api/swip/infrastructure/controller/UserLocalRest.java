package com.api.swip.infrastructure.controller;

import com.api.swip.entity.UserLocal;
import com.api.swip.service.IUserLocalService;
import com.api.swip.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-locales")
public class UserLocalRest
{
    private final IUserLocalService service;
    private final IUserService userService;

    @PostMapping()
    public ResponseEntity<UserLocal> register(@Valid @RequestBody UserLocal userLocal) throws Exception {
        UserLocal userLocalSaved = service.save(userLocal);

        return new ResponseEntity<>(userLocalSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserLocal> update(@Valid @RequestBody UserLocal userLocal, @PathVariable("id") Integer id) throws Exception {
        UserLocal userLocalSaved = service.update(userLocal, id);

        return new ResponseEntity<>(userLocalSaved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserLocal> findById(@PathVariable("id") Integer id) throws Exception {
        UserLocal userLocal = service.readById(id);

        return new ResponseEntity<>(userLocal, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserLocal>> findAll() throws Exception {
        List<UserLocal> userLocals = service.readAll();

        return new ResponseEntity<>(userLocals, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
