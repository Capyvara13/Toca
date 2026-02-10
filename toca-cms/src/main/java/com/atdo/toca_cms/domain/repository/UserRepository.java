package com.atdo.toca_cms.domain.repository;

import com.atdo.toca_cms.application.dto.user.UserFilterDto;
import com.atdo.toca_cms.domain.entity.User;
import com.atdo.toca_cms.domain.util.enums.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

/**
 * Domain Port (Repository Interface) for managing User persistence operations.
 * This interface defines the contract that the persistence adapter must fulfill.
 */
public interface UserRepository {

    /**
     * Saves a new User entity or updates an existing one.
     * @param user The User entity to save.
     * @return The persisted User entity.
     */
    User save(User user);

    /**
     * Retrieves a User entity by its unique ID.
     * @param idUser The ID of the user.
     * @return An Optional containing the User if found, or empty otherwise.
     */
    Optional<User> findById(Long idUser);

    /**
     * Retrieves a User entity by its unique email address.
     * Assumes email is unique in the domain.
     * @param email The email to search for.
     * @return An Optional containing the User if found, or empty otherwise.
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds all Users based on the provided filtering criteria.
     * This method consolidates the various filtering needs into a single interface method,
     * leveraging the UserFilterDto from the Application layer.
     *
     * @param filterDto The DTO containing search criteria and pagination information.
     * @return A page of User matching the filter criteria.
     */
    Page<User> findAll(UserFilterDto filterDto);

    void deleteById(Long id);
}