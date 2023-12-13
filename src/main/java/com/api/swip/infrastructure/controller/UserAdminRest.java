package com.api.swip.infrastructure.controller;

import com.api.swip.entity.UserAdmin;
import com.api.swip.service.IUserAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-admins")
public class UserAdminRest
{
    private final IUserAdminService service;

    @PostMapping()
    public ResponseEntity<UserAdmin> register(@Valid @RequestBody UserAdmin userAdmin) throws Exception {
        UserAdmin userAdminSaved = service.save(userAdmin);

        return new ResponseEntity<>(userAdminSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAdmin> update(@Valid @RequestBody UserAdmin userAdmin, @PathVariable("id") Integer id) throws Exception {
        UserAdmin userAdminSaved = service.update(userAdmin, id);

        return new ResponseEntity<>(userAdminSaved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAdmin> findById(@PathVariable("id") Integer id) throws Exception {
        UserAdmin userAdmin = service.readById(id);

        return new ResponseEntity<>(userAdmin, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserAdmin>> findAll() throws Exception {
        List<UserAdmin> userAdmins = service.readAll();

        return new ResponseEntity<>(userAdmins, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
