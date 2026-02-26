package com.atdo.toca_cms.adapter.api;

import com.atdo.toca_cms.application.dto.user.UserFilterDto;
import com.atdo.toca_cms.application.dto.user.UserResponseDto;
import com.atdo.toca_cms.application.usecase.UserUsecase;
import com.atdo.toca_cms.domain.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserUsecase userUsecase;

    @GetMapping("/{userId}")
    public ResponseEntity<User> findById(@PathVariable Long userId) {
        User user = userUsecase.searchOrFail(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid User user) {
        User savedUser = userUsecase.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> update(@PathVariable Long userId, @RequestBody @Valid User user) {
        User userWithId = user.toBuilder().idUser(userId).build();
        User updatedUser = userUsecase.save(userWithId);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId) {
        userUsecase.delete(userId);
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDto>> list(UserFilterDto filter) {
        Page<User> userPage = userUsecase.findAll(filter);

        Page<UserResponseDto> responsePage = userPage.map(user -> new UserResponseDto(
                user.getIdUser(),
                user.getUsername(),
                user.getEmail(),
                user.getBio(),
                user.getRole(),
                user.isActive(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        ));

        return ResponseEntity.ok(responsePage);
    }
}