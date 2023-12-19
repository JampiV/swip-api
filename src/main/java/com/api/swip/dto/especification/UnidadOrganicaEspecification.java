package com.api.swip.dto.especification;

import com.api.swip.entity.UnidadOrganica;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UnidadOrganicaEspecification {
    public static Specification<UnidadOrganica> withDynamicQuery(String filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter != null && !filter.isEmpty()) {
                String filterLower = filter.toLowerCase();
                Predicate nombreUnidadPredicate = cb.like(cb.lower(root.get("nombreUnidad")), "%" + filterLower + "%");
                Predicate ubicacionPoliticaPredicate = cb.like(cb.lower(root.get("ubicacionPolitica")), "%" + filterLower + "%");
                Predicate ubicacionGeograficaPredicate = cb.like(cb.lower(root.get("ubicacionGeografica")), "%" + filterLower + "%");
                Predicate combinedPredicate = cb.or(nombreUnidadPredicate, ubicacionPoliticaPredicate, ubicacionGeograficaPredicate);
                predicates.add(combinedPredicate);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
