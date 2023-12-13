package com.api.swip.infrastructure.controller;

import com.api.swip.dto.BienDto;
import com.api.swip.entity.Bien;
import com.api.swip.service.IBienService;
import com.api.swip.service.IUserLocalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bienes")
@RequiredArgsConstructor
public class BienesRest
{
    private final IBienService service;
    private final IUserLocalService userLocalService;

    @PostMapping()
    public ResponseEntity<Bien> register(@Valid @RequestBody Bien obj) throws Exception
    {
        Bien bien = service.save(obj);
        return new ResponseEntity<>(bien, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bien> update(@Valid @RequestBody Bien obj,@PathVariable("id") Integer id) throws Exception
    {
        Bien bien = service.update(obj, id);
        return new ResponseEntity<>(bien, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bien> findById(@PathVariable("id") Integer id) throws Exception
    {
        Bien bien = service.readById(id);
        return new ResponseEntity<>(bien, HttpStatus.OK);
    }

    @GetMapping("/unidad")
    public ResponseEntity<List<BienDto>> findAllByUnidad() throws Exception{
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<BienDto> bienDtoList = new ArrayList<>();
        List<Bien> bienes = service.findByInventarioId(userLocalService.obtenerInventarioId(username));
        for (int i = 0; i < bienes.size();i++){
            BienDto bienDto = new BienDto();
            Bien bien = bienes.get(i);
            bienDto.setId(bien.getId());
            bienDto.setEstado(bien.getEstado());
            bienDto.setMarca(bien.getMarca());
            bienDto.setModelo(bien.getModelo());
            bienDto.setSerie(bien.getSerie());
            bienDto.setNombreDescriptivo(bien.getNombreDescriptivo());
            bienDtoList.add(bienDto);
        }
        return new ResponseEntity<>(bienDtoList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BienDto>> findAll() throws Exception{
        List<BienDto> bienDtoList = new ArrayList<>();
        List<Bien> bienes = service.readAll();
        for (int i = 0; i < bienes.size();i++){
            BienDto bienDto = new BienDto();
            Bien bien = bienes.get(i);
            bienDto.setId(bien.getId());
            bienDto.setEstado(bien.getEstado());
            bienDto.setMarca(bien.getMarca());
            bienDto.setModelo(bien.getModelo());
            bienDto.setSerie(bien.getSerie());
            bienDto.setNombreDescriptivo(bien.getNombreDescriptivo());
            bienDtoList.add(bienDto);
        }
        return new ResponseEntity<>(bienDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Bien> deleteById(@PathVariable("id") Integer id) throws Exception
    {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
