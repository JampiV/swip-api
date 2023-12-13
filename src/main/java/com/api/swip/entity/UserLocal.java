package com.api.swip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_local")
public class UserLocal extends User
{
    @ManyToOne()
    @JoinColumn(name = "id_unidad_organica", nullable = false, foreignKey = @jakarta.persistence.ForeignKey(name = "FK_user_local_unidad_organica"))
    private UnidadOrganica unidadOrganica;
}
