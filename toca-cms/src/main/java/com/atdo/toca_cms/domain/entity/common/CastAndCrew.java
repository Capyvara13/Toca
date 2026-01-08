package com.atdo.toca_cms.domain.entity.common;

import com.atdo.toca_cms.domain.entity.Media;
import com.atdo.toca_cms.domain.exceptions.EntityNotFoundException;
import com.atdo.toca_cms.domain.util.enums.MediaType;
import com.atdo.toca_cms.domain.util.enums.RoleType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@Table(name = "cast_and_crew")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CastAndCrew {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cast_and_crew")
    private long idCastAndCrew;

    @JoinColumn(name = "fk_media_id", nullable = false)
    @ManyToOne
    private Media media;

    @JoinColumn(name = "fk_artist_id", nullable = false)
    @ManyToOne
    private Artist artist;

    @Column(name = "role_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private MediaType getMediaType() {
        if (this.media == null) {
            throw  new EntityNotFoundException(("No Media found!"));
        }
        return this.media.getMediaType();
    }

    public List<RoleType> getRolesSuggestion() {
        MediaType mediaType = getMediaType();
        RoleType.DomainType domain = null;


        switch (mediaType) {
            case MOVIE:
                domain = RoleType.DomainType.MOVIES;
                break;
            case SERIE:
                domain = RoleType.DomainType.SERIES;
                break;
            case MUSIC:
                domain = RoleType.DomainType.MUSICS;
                break;
            case BOOK:
                domain = RoleType.DomainType.BOOKS;
                break;
            case GAME:
                domain = RoleType.DomainType.GAMES;
                break;
        }
        
        return RoleType.getRolesForDomain(domain);
    }

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;


}
