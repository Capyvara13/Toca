package com.atdo.toca_cms.adapter.persistence.mapper;

import com.atdo.toca_cms.adapter.persistence.entity.UserEntity;
import com.atdo.toca_cms.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceMapper {
    public UserEntity toEntity(User domain) {
        if (domain == null) return null;
        UserEntity entity = new UserEntity();
        entity.setIdUser(domain.getIdUser());
        entity.setBio(domain.getBio());
        entity.setEmail(domain.getEmail());
        entity.setActive(domain.isActive());
        entity.setRole(domain.getRole());
        entity.setComments(null);
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setPassword(domain.getPassword());
        entity.setUsername(domain.getUsername());
        entity.setLastLogin(domain.getLastLogin());
        return entity;
    }

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;

        return User.builder()
                .idUser(entity.getIdUser())
                .bio(entity.getBio())
                .email(entity.getEmail())
                .active(entity.isActive())
                .role(entity.getRole())
                .comments(null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .password(entity.getPassword())
                .username(entity.getUsername())
                .lastLogin(entity.getLastLogin())
                .build();
    }
}
