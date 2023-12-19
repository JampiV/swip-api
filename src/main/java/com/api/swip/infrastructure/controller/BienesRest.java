package com.api.swip.infrastructure.controller;

import com.api.swip.dto.BienCentralDto;
import com.api.swip.dto.BienCentralSelecDto;
import com.api.swip.dto.BienDto;
import com.api.swip.entity.Bien;
import com.api.swip.service.IBienService;
import com.api.swip.service.IUserLocalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<BienDto>> findAllByUnidad(Pageable pageable) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer inventarioId = userLocalService.obtenerInventarioId(username);

        // Obtener la página de Bienes usando el método del repositorio
        Page<Bien> pageBienes = service.findByInventarioId(inventarioId, pageable);

        // Convertir cada Bien a BienDto
        Page<BienDto> bienDtoPage = pageBienes.map(bien -> {
            BienDto bienDto = new BienDto();
            bienDto.setId(bien.getId());
            bienDto.setEstado(bien.getEstado());
            bienDto.setMarca(bien.getMarca());
            bienDto.setModelo(bien.getModelo());
            bienDto.setSerie(bien.getSerie());
            bienDto.setNombreDescriptivo(bien.getNombreDescriptivo());
            return bienDto;
        });

        return new ResponseEntity<>(bienDtoPage, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Page<BienCentralDto>> findAllWithUnidad(Pageable pageable) throws Exception {
        Page<BienCentralDto> bienesConUnidad = service.findAllBienesWithUnidad(pageable);
        return new ResponseEntity<>(bienesConUnidad, HttpStatus.OK);
    }

    /*Obteber bienes sin doc_baja*/
    @GetMapping("/unidad/{id}")
    public ResponseEntity<Page<BienCentralSelecDto>> findCentralBienByUnidad(@PathVariable("id") Integer id, Pageable pageable) throws Exception {

        // Obtener la página de Bienes usando el método del repositorio
        Page<Bien> pageBienes = service.findByInventarioId(id, pageable);

        // Convertir cada Bien a BienDto
        Page<BienCentralSelecDto> bienCentralSelDtoPage = pageBienes.map(bien -> {
            BienCentralSelecDto bienCentralSelDto = new BienCentralSelecDto();
            bienCentralSelDto.setId(bien.getId());
            bienCentralSelDto.setEstado(bien.getEstado());
            bienCentralSelDto.setMarca(bien.getMarca());
            bienCentralSelDto.setModelo(bien.getModelo());
            bienCentralSelDto.setSerie(bien.getSerie());
            bienCentralSelDto.setNombreDescriptivo(bien.getNombreDescriptivo());
            bienCentralSelDto.setDocumentoAlta(bien.getDocumentoAlta());
            bienCentralSelDto.setFecActualizacion(bien.getFecActualizacion());
            bienCentralSelDto.setFecIngreso(bien.getFecIngreso());
            bienCentralSelDto.setSitBinv(bien.getSitBinv());
            bienCentralSelDto.setValorAdquisicion(bien.getValorAdquisicion());
            bienCentralSelDto.setValorActual(bien.getValorActual());
            bienCentralSelDto.setValorActualDos(bien.getValorActualDos());
            return bienCentralSelDto;
        });

        return new ResponseEntity<>(bienCentralSelDtoPage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Bien> deleteById(@PathVariable("id") Integer id) throws Exception
    {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
