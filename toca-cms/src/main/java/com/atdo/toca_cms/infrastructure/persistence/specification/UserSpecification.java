package com.atdo.toca_cms.infrastructure.persistence.specification;

import com.atdo.toca_cms.adapter.persistence.entity.UserEntity;
import com.atdo.toca_cms.application.dto.user.UserFilterDto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {
    public static Specification<UserEntity> withFilter(UserFilterDto filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(filter.getEmail())) {
                predicates.add(cb.like(
                        cb.lower(root.get("email")),
                        "%" + filter.getEmail().toLowerCase() + "%"
                ));
            }

            if (StringUtils.hasText(filter.getUsername())) {
                predicates.add(cb.like(
                        cb.lower(root.get("username")),
                        "%" + filter.getUsername().toLowerCase() + "%"
                ));
            }

            if (filter.getRole() != null) {
                predicates.add(cb.equal(root.get("role"), filter.getRole()));
            }

            if (filter.getCreatedAfter() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), filter.getCreatedAfter()));
            }

            if (filter.getCreatedBefore() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), filter.getCreatedBefore()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}