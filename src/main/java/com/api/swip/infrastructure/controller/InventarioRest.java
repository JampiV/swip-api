package com.api.swip.infrastructure.controller;

import com.api.swip.entity.Inventario;
import com.api.swip.service.IInventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventarios")
public class InventarioRest
{
    private final IInventarioService service;

    @PostMapping()
    public ResponseEntity<Inventario> register(@Valid @RequestBody Inventario inventario) throws Exception
    {
        return new ResponseEntity<>(service.save(inventario), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> update(@Valid @RequestBody Inventario inventario, @PathVariable("id") Integer id) throws Exception
    {
        return new ResponseEntity<>(service.update(inventario, id), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> findById(@PathVariable("id") Integer id) throws Exception
    {
        return new ResponseEntity<>(service.readById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Inventario>> findAll() throws Exception
    {
        return new ResponseEntity<>(service.readAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception
    {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
