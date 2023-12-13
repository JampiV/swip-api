package com.api.swip.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    protected Integer id;

    protected String cargoPolicial;

    @Column(nullable = false, unique = true)
    protected String codigo;

    @Column(nullable = false, length = 72)
    protected String contrasenia;

    @ManyToOne()
    @JoinColumn(name = "role_id", nullable = false, foreignKey = @ForeignKey(name = "FK_role_user"))
    private Role role;

/*  @JsonBackReference()
    @ManyToOne()
    @JoinColumn(name="unidad", foreignKey = @ForeignKey(name = "FK_unidad_user"))
    private UnidadOrganica unidad;*/
}
