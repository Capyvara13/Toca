    package com.atdo.toca_cms.domain.entity;

    import com.atdo.toca_cms.domain.util.enums.UserRole;
    import lombok.Builder;
    import lombok.NonNull;
    import lombok.Value;

    import java.time.Instant;
    import java.util.List;

    @Builder(toBuilder = true)
    @Value
    public class User {
        long idUser;

        @NonNull
        String username;

        @NonNull
        String email;

        @NonNull
        String passwordHash;

        String bio;

        @Builder.Default
        UserRole role = UserRole.COMMON;

        @NonNull
        Instant createdAt;

        Instant updatedAt;

        @Builder.Default
        boolean active = true;

        List<Comment> comments;

        Instant lastLogin;
    }
