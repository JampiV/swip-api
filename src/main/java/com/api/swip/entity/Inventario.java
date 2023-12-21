package com.api.swip.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
public class Inventario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate fechaActualizacion;

    @JsonBackReference()
    @OneToMany( mappedBy = "inventario", cascade = {CascadeType.ALL})
    private List<Bien> bienes;

    public Inventario ()
    {

    }

    public Inventario (int id, LocalDate fechaActualizacion, List<Bien> bienes)
    {
        this.id = id;
        this.fechaActualizacion = fechaActualizacion;
        this.bienes = bienes;
    }

    /*
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public List<Bien> getBienes() {
        return bienes;
    }

    public void setBienes(List<Bien> bienes) {
        this.bienes = bienes;
    }*/
}