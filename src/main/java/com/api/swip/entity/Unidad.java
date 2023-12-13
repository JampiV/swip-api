package com.api.swip.entity;

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

    @Column(nullable = false)
    private String nombreUnidad;

    /*
    @OneToMany(mappedBy = "unidad", cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_user", unique = true, foreignKey = @ForeignKey(name = "FK_USER_UNIDAD"))
    private User user;*/

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_inventario", unique = true, foreignKey = @ForeignKey(name = "FK_UNIDAD_INVENTARIO"))
    private Inventario inventario;
}
