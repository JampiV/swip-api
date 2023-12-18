package com.api.swip.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Unidad
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ubicacionPolitica;

    private String ubicacionGeografica;

    @Column(nullable = false, unique = true)
    private String nombreUnidad;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_inventario", unique = true, foreignKey = @ForeignKey(name = "FK_UNIDAD_INVENTARIO"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Inventario inventario;
}
