package com.atdo.toca_cms.application.usecase;

import com.atdo.toca_cms.application.dto.user.UserFilterDto;
import com.atdo.toca_cms.domain.entity.User;
import com.atdo.toca_cms.domain.exceptions.EntityNotFoundException;
import com.atdo.toca_cms.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserUsecase {
    @Autowired
    private final UserRepository userRepository;

    public User searchOrFail(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(("User not found!")));
    }

    @Transactional(readOnly = true)
    public Page<User> findAll(UserFilterDto filterDto) {
        // A lógica de paginação e mapeamento de entidade para domínio
        // já está implementada no seu UserPersistenceAdapter.
        return userRepository.findAll(filterDto);
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long userId) {
        userRepository.findById(userId).ifPresent(user -> {
            userRepository.deleteById(userId);
        });
    }
}
