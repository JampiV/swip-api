package com.api.swip.infrastructure.controller;

import com.api.swip.entity.UnidadInventario;
import com.api.swip.service.IUnidadInventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/unidades-inventarios")
public class UnidadInventarioRest
{
    private final IUnidadInventarioService service;

    @PostMapping()
    public ResponseEntity<UnidadInventario> register(@Valid @RequestBody UnidadInventario unidadInventario) throws Exception
    {
        return ResponseEntity.ok(service.save(unidadInventario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadInventario> update(@Valid @RequestBody UnidadInventario unidadInventario,@PathVariable("id") Integer id) throws Exception
    {
        return ResponseEntity.ok(service.update(unidadInventario, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadInventario> findById(@PathVariable("id") Integer id) throws Exception
    {
        return ResponseEntity.ok(service.readById(id));
    }

    @GetMapping()
    public ResponseEntity<List<UnidadInventario>> findAll() throws Exception
    {
        return ResponseEntity.ok(service.readAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception
    {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
