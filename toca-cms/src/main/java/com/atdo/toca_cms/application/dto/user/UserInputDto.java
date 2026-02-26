package com.atdo.toca_cms.application.dto.user;

import com.atdo.toca_cms.domain.util.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInputDto {
    /**
     * The unique public username for the user.
     * Mandatory field.
     */
    @NotBlank(message = "The user needs a username.")
    @Size(max = 100, message = "The username must not exceed 100 characters.")
    private String username;

    /**
     * The unique email address for the user.
     * Mandatory field and must be a valid email format.
     */
    @NotBlank(message = "The user needs an email.")
    @Email(message = "The email must be a valid format.")
    @Size(max = 255, message = "The email must not exceed 255 characters.")
    private String email;

    /**
     * The user's password in plain text. Will be hashed by the application service.
     * Mandatory field.
     */
    @NotBlank(message = "The password needs to exist.")
    @Size(min = 8, message = "The password must have at least 8 characters.") // Adicionei validação de tamanho mínimo de senha
    private String password;

    /**
     * Optional biography or description of the user.
     */
    private String bio;

    /**
     * The user's role in the system (e.g., COMMON, ADMIN).
     * Mapped to the 'role' field in the Entity.
     */
    private UserRole role;

    /**
     * Status indicating if the user is active.
     * If not provided, the default (true) is applied by the system/database.
     */
    private Boolean active;
}