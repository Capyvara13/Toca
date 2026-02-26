package com.atdo.toca_cms.adapter.persistence;

import com.atdo.toca_cms.adapter.persistence.entity.UserEntity;
import com.atdo.toca_cms.adapter.persistence.jpa.JpaUserRepository;
import com.atdo.toca_cms.adapter.persistence.mapper.UserPersistenceMapper;
import com.atdo.toca_cms.application.dto.user.UserFilterDto;
import com.atdo.toca_cms.domain.entity.User;
import com.atdo.toca_cms.domain.repository.UserRepository;
import com.atdo.toca_cms.infrastructure.persistence.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepository {
    private final JpaUserRepository jpaUserRepository;
    private final UserPersistenceMapper mapper;

    @Override
    @Transactional
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        UserEntity savedEntity = jpaUserRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id).map(mapper::toDomain);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<User> findAll(UserFilterDto filterDto) {
        Pageable pageable = PageRequest.of(
                filterDto.getPage() != null ? filterDto.getPage() : 0,
                filterDto.getSize() != null ? filterDto.getSize() : 10
        );

        Specification<UserEntity> specification = UserSpecification.withFilter(filterDto);

        return jpaUserRepository.findAll(specification, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmailOrUsername(String email, String username) {
        return jpaUserRepository.findByEmailOrUsername(email, username).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaUserRepository.deleteById(id);
    }
}
