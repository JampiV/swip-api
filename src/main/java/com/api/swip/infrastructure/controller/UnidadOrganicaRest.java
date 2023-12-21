package com.api.swip.infrastructure.controller;

import com.api.swip.entity.UnidadOrganica;
import com.api.swip.service.IUnidadOrganicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/unidades-organicas")
public class UnidadOrganicaRest
{
    private final IUnidadOrganicaService service;

    @PostMapping()
    public ResponseEntity<UnidadOrganica> register(@Valid @RequestBody UnidadOrganica obj) throws Exception {
        UnidadOrganica objCreated = service.save(obj);

        return new ResponseEntity<>(objCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadOrganica> update(@Valid @RequestBody UnidadOrganica obj, @PathVariable("id") Integer id) throws Exception {
        UnidadOrganica objUpdated = service.update(obj, id);

        return new ResponseEntity<>(objUpdated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadOrganica> findById(@PathVariable("id") Integer id) throws Exception {
        UnidadOrganica obj = service.readById(id);

        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Page<UnidadOrganica>> findAll(
            @RequestParam(required = false) String filter,
            Pageable pageable
    ) throws Exception {
        Page<UnidadOrganica> obj = service.readAll(filter, pageable);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

     @GetMapping("/name/{id}")
     public ResponseEntity<String> getNameUnidad(@PathVariable("id") Integer id) throws Exception{
        String nombreUnidad = service.getNameUnidad(id);
        return new ResponseEntity<>(nombreUnidad, HttpStatus.OK);
     }

     @GetMapping("name/inventario/{id}")
     public ResponseEntity<String> getNameForIdInventario(@PathVariable("id") Integer id) throws Exception{
        String nombreUnidad = service.getNameForIdInventario(id);
        return new ResponseEntity<>(nombreUnidad, HttpStatus.OK);
     }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/without-bienes/{id}")
    public ResponseEntity<Void> deleteByIdWithoutBienes(@PathVariable("id") Integer id) throws Exception
    {
        service.deleteByIdWithoutBienes(id);
        return ResponseEntity.noContent().build();
    }
}
