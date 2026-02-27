package com.atdo.toca_cms.infrastructure.persistence.specification;

import com.atdo.toca_cms.adapter.persistence.entity.mediaType.book.BookEntity;
import com.atdo.toca_cms.application.dto.mediaTypeDto.bookTypeDto.BookFilterDto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para construção de queries dinâmicas de Livros (Books).
 */
public class BookSpecification {

    public static Specification<BookEntity> withFilter(BookFilterDto filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 1. Filtro por Keyword (Busca no Título, Subtítulo ou Sinopse)
            if (StringUtils.hasText(filter.getKeyword())) {
                String keyword = "%" + filter.getKeyword().toLowerCase() + "%";
                Predicate titlePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), keyword);
                Predicate subtitlePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("subtitle")), keyword);
                Predicate synopsisPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("synopsis")), keyword);
                predicates.add(criteriaBuilder.or(titlePredicate, subtitlePredicate, synopsisPredicate));
            }

            // 2. Filtro por Editora (Publisher)
            if (StringUtils.hasText(filter.getPublisher())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("publisher")),
                        "%" + filter.getPublisher().toLowerCase() + "%"
                ));
            }

            // 3. Filtro por ISBN (Exato)
            if (StringUtils.hasText(filter.getIsbn())) {
                predicates.add(criteriaBuilder.equal(root.get("isbn"), filter.getIsbn()));
            }

            // 4. Filtro por Número de Páginas (Exato - se informado > 0)
            if (filter.getNumPages() > 0) {
                predicates.add(criteriaBuilder.equal(root.get("numPages"), filter.getNumPages()));
            }

            // 5. Filtro por Edição
            if (filter.getEdition() > 0) {
                predicates.add(criteriaBuilder.equal(root.get("edition"), filter.getEdition()));
            }

            // 6. Filtro por Range de Avaliação (Rating)
            if (filter.getMinRating() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), filter.getMinRating()));
            }
            if (filter.getMaxRating() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("rating"), filter.getMaxRating()));
            }

            // 7. Filtro por Status (Enum ContentStatus)
            if (filter.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
            }

            // 8. Filtro por Ano de Publicação
            if (filter.getPublicationYear() > 0) {
                predicates.add(criteriaBuilder.equal(root.get("publicationYear"), filter.getPublicationYear()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}