package com.atdo.toca_cms.adapter.persistence.entity.mediaType.game;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamePlatformIdEntity implements Serializable {
    private Long gameId;
    private Long platformId;
}
