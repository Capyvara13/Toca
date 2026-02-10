package com.atdo.toca_cms.application.dto.user;

import com.atdo.toca_cms.domain.util.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * Data Transfer Object (DTO) to encapsulate filter and pagination criteria
 * when searching for User entities.
 */
public record UserFilterDto(
        String username,
        UserRole role,
        Instant createdAfter,
        Instant updatedAfter,
        Instant createdBefore,
        Integer page,
        Integer size
) {
    // Construtor compacto para definir valores padrão, caso venham nulos
    public UserFilterDto {
        if (page == null) page = 0;
        if (size == null) size = 10;
    }
}
