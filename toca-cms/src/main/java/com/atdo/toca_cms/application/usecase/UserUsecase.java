package com.atdo.toca_cms.application.usecase;

import com.atdo.toca_cms.adapter.persistence.UserPersistenceAdapter;
import com.atdo.toca_cms.application.dto.user.UserFilterDto;
import com.atdo.toca_cms.domain.entity.User;
import com.atdo.toca_cms.domain.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserUsecase {
    @Autowired
    private final UserPersistenceAdapter adapter;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public User searchOrFail(Long userId) {
        return adapter.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found!"));
    }

    @Transactional(readOnly = true)
    public Page<User> findAll(UserFilterDto filterDto) {
        return adapter.findAll(filterDto);
    }

    @Transactional
    public User save(User user) {


        User userWithEncodedPassword = user.toBuilder()
                .password(passwordEncoder.encode(user.getPassword()))
                .build();

        return adapter.save(userWithEncodedPassword);
    }

    @Transactional
    public void delete(Long userId) {
        adapter.findById(userId).ifPresent(user -> {
            adapter.deleteById(userId);
        });
    }

    public User findByEmailOrUsername(String email, String username) {
        return adapter.findByEmailOrUsername(email, username).orElseThrow(() -> new RuntimeException("User with this email not found!"));
    }

    public User authenticate(String loginIdentifier, String rawPassword) {
        User user = adapter.findByEmailOrUsername(loginIdentifier, loginIdentifier).orElseThrow(() -> new RuntimeException("User with this email not found!"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid credentials!");
        }

        return user;
    }
}
