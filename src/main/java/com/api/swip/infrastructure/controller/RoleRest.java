package com.api.swip.infrastructure.controller;

import com.api.swip.entity.Role;
import com.api.swip.service.IRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleRest
{
    private final IRoleService service;

    @PostMapping()
    public ResponseEntity<Role> register(@Valid @RequestBody Role role) throws Exception
    {
        Role roleCreated = service.save(role);
        return new ResponseEntity<>(roleCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@Valid @RequestBody Role role, @PathVariable("id") Integer id) throws Exception
    {
        return new ResponseEntity<>(service.update(role, id), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable("id") Integer id) throws Exception
    {
        return new ResponseEntity<>(service.readById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Role>> findAll() throws Exception {
        return new ResponseEntity<>(service.readAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception
    {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
