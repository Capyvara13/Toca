package com.atdo.toca_cms.application.usecase;

import com.atdo.toca_cms.application.dto.user.UserFilterDto;
import com.atdo.toca_cms.domain.entity.User;
import com.atdo.toca_cms.domain.exceptions.EntityNotFoundException;
import com.atdo.toca_cms.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserUsecase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User searchOrFail(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found!"));
    }

    @Transactional(readOnly = true)
    public Page<User> findAll(UserFilterDto filterDto) {
        return userRepository.findAll(filterDto);
    }

    @Transactional
    public User save(User user) {


        User userWithEncodedPassword = user.toBuilder()
                .password(passwordEncoder.encode(user.getPassword()))
                .build();

        return userRepository.save(userWithEncodedPassword);
    }

    @Transactional
    public void delete(Long userId) {
        userRepository.findById(userId).ifPresent(user -> {
            userRepository.deleteById(userId);
        });
    }

    public User findByEmailOrUsername(String email, String username) {
        return userRepository.findByEmailOrUsername(email, username).orElseThrow(() -> new RuntimeException("User with this email not found!"));
    }

    public User authenticate(String loginIdentifier, String rawPassword) {
        User user = userRepository.findByEmailOrUsername(loginIdentifier, loginIdentifier).orElseThrow(() -> new RuntimeException("User with this email not found!"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid credentials!");
        }

        return user;
    }
}
