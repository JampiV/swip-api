package com.api.swip.infrastructure.controller;

import com.api.swip.entity.UserInventariador;
import com.api.swip.service.IUserInventariadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-inventariadores")
public class UserInventariadorRest
{
    private final IUserInventariadorService service;

    @PostMapping()
    public ResponseEntity<UserInventariador> register(@Valid @RequestBody UserInventariador userInventariador) throws Exception {
        UserInventariador userInventariadorSaved = service.save(userInventariador);

        return new ResponseEntity<>(userInventariadorSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserInventariador> update(@Valid @RequestBody UserInventariador userInventariador, @PathVariable("id") Integer id) throws Exception {
        UserInventariador userInventariadorSaved = service.update(userInventariador, id);

        return new ResponseEntity<>(userInventariadorSaved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInventariador> findById(@PathVariable("id") Integer id) throws Exception {
        UserInventariador userInventariador = service.readById(id);

        return new ResponseEntity<>(userInventariador, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserInventariador>> findAll() throws Exception {
        List<UserInventariador> userInventariadors = service.readAll();

        return new ResponseEntity<>(userInventariadors, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
