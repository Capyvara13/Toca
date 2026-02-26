package com.atdo.toca_cms.application.dto.user;

import com.atdo.toca_cms.domain.util.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    /**
     * The unique identifier of the user.
     */
    private Long idUser;

    /**
     * The unique public username for the user.
     */
    private String username;

    /**
     * The unique email address for the user.
     */
    private String email;

    /**
     * Optional biography or description of the user.
     */
    private String bio;

    /**
     * The user's role in the system (e.g., COMMON, ADMIN).
     */
    private UserRole role;

    /**
     * Status indicating if the user is active.
     */
    private Boolean active;

    /**
     * The instant when the user record was created.
     */
    private Instant createdAt;

    /**
     * The instant when the user record was last updated.
     */
    private Instant updatedAt;
}