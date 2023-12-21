package com.api.swip.dto.especification;

import com.api.swip.entity.Bien;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

public class BienEspecification {

    public static Specification<Bien> withFilterAndInventarioId(String filter, Integer inventarioId) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtro por inventario ID
            predicates.add(cb.equal(root.join("inventario").get("id"), inventarioId));

            if (filter != null && !filter.isEmpty()) {
                String filterLower = filter.toLowerCase();
                Predicate nombreDescriptivo = cb.like(cb.lower(root.get("nombreDescriptivo")), "%" + filterLower + "%");
                Predicate marca = cb.like(cb.lower(root.get("marca")), "%" + filterLower + "%");
                Predicate modelo = cb.like(cb.lower(root.get("modelo")), "%" + filterLower + "%");
                Predicate serie = cb.like(cb.lower(root.get("serie")), "%" + filterLower + "%");
                Predicate fecIngreso = cb.like(cb.lower(root.get("fecIngreso")), "%" + filterLower + "%");
                Predicate fecActualizacion = cb.like(cb.lower(root.get("fecActualizacion")), "%" + filterLower + "%");
                Predicate documentoAlta = cb.like(cb.lower(root.get("documentoAlta")), "%" + filterLower + "%");
                Predicate sitBinv = cb.like(cb.lower(root.get("sitBinv")), "%" + filterLower + "%");
                Predicate estado = cb.like(cb.lower(root.get("estado")), "%" + filterLower + "%");

                Predicate combinedPredicate = cb.or(nombreDescriptivo, marca, modelo, serie, fecIngreso, fecActualizacion,
                        documentoAlta, sitBinv, estado); // otros predicados
                predicates.add(combinedPredicate);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
