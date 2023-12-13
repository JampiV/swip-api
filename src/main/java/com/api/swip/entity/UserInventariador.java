package com.api.swip.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_inventariador")
public class UserInventariador extends User
{
   @OneToOne()
   @JoinColumn(name = "id_unidad_inventario", nullable = false, foreignKey = @jakarta.persistence.ForeignKey(name = "FK_user_inventariador_unidad_inventario"))
   private UnidadInventario unidadInventario;
}
