-- ======================================================
-- TOCA CMS - COMPLETE DATABASE SCHEMA (MySQL/InnoDB)
-- ======================================================

-- 1. CORE MODULE
-- Independent tables or those with minimal dependencies

CREATE TABLE IF NOT EXISTS user (
    id_user BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(60) NOT NULL,
    bio LONGTEXT,
    role VARCHAR(255) DEFAULT 'COMMON',
    is_actived BOOLEAN DEFAULT TRUE,
    last_login DATETIME(6),
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS artist (
    id_artist BIGINT AUTO_INCREMENT PRIMARY KEY,
    slug VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    birth_date DATE,
    death_date DATE,
    bio LONGTEXT NOT NULL,
    photo_url VARCHAR(500),
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS media (
    id_media BIGINT AUTO_INCREMENT PRIMARY KEY,
    media_type VARCHAR(50) NOT NULL, -- MOVIE, SERIE, MUSIC, BOOK, GAME
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 2. CORE RELATIONSHIPS & CONTENT

CREATE TABLE IF NOT EXISTS cast_and_crew (
    id_cast_and_crew BIGINT AUTO_INCREMENT PRIMARY KEY,
    fk_media_id BIGINT NOT NULL,
    fk_artist_id BIGINT NOT NULL,
    role_type VARCHAR(50) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    CONSTRAINT fk_cc_media FOREIGN KEY (fk_media_id) REFERENCES media(id_media) ON DELETE CASCADE,
    CONSTRAINT fk_cc_artist FOREIGN KEY (fk_artist_id) REFERENCES artist(id_artist) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS article (
    id_article BIGINT AUTO_INCREMENT PRIMARY KEY,
    slug VARCHAR(255) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    content LONGTEXT NOT NULL,
    summary VARCHAR(500) NOT NULL,
    view_count BIGINT DEFAULT 0,
    has_AI BOOLEAN DEFAULT FALSE,
    status VARCHAR(50) DEFAULT 'DRAFT',
    fk_user_article BIGINT NOT NULL,
    fk_media_article BIGINT NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    CONSTRAINT fk_article_user FOREIGN KEY (fk_user_article) REFERENCES user(id_user),
    CONSTRAINT fk_article_media FOREIGN KEY (fk_media_article) REFERENCES media(id_media)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS comment (
    id_comment BIGINT AUTO_INCREMENT PRIMARY KEY,
    content LONGTEXT NOT NULL,
    fk_article_comment BIGINT NOT NULL,
    fk_user_comment BIGINT NOT NULL,
    fk_parent_comment_id BIGINT,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    CONSTRAINT fk_comment_article FOREIGN KEY (fk_article_comment) REFERENCES article(id_article) ON DELETE CASCADE,
    CONSTRAINT fk_comment_user FOREIGN KEY (fk_user_comment) REFERENCES user(id_user),
    CONSTRAINT fk_comment_parent FOREIGN KEY (fk_parent_comment_id) REFERENCES comment(id_comment) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. MEDIA SPECIALIZATIONS (Expansion)

CREATE TABLE IF NOT EXISTS movie (
    id_movie BIGINT AUTO_INCREMENT PRIMARY KEY,
    slug VARCHAR(255) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    release_date DATE NOT NULL,
    duration_minute INT NOT NULL,
    synopsis LONGTEXT NOT NULL,
    poster_url VARCHAR(500),
    rating DECIMAL(3, 1) DEFAULT 0.0,
    status VARCHAR(50) DEFAULT 'DRAFT',
    fk_media_id BIGINT NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    CONSTRAINT fk_movie_media FOREIGN KEY (fk_media_id) REFERENCES media(id_media) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS music (
    id_music BIGINT AUTO_INCREMENT PRIMARY KEY,
    slug VARCHAR(255) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    album VARCHAR(200),
    genre VARCHAR(100) NOT NULL,
    release_date DATE NOT NULL,
    duration_second INT NOT NULL,
    lyrics LONGTEXT,
    cover_url VARCHAR(500),
    rating DECIMAL(3, 1) DEFAULT 0.0,
    status VARCHAR(50) DEFAULT 'DRAFT',
    fk_media_id BIGINT NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    CONSTRAINT fk_music_media FOREIGN KEY (fk_media_id) REFERENCES media(id_media) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS book (
    id_book BIGINT AUTO_INCREMENT PRIMARY KEY,
    slug VARCHAR(255) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    subtitle VARCHAR(255),
    isbn VARCHAR(13) NOT NULL UNIQUE,
    publication_year INT,
    edition INT DEFAULT 1,
    publisher VARCHAR(255),
    num_pages INT NOT NULL,
    synopsis LONGTEXT NOT NULL,
    cover_url VARCHAR(500),
    rating DECIMAL(3, 1) DEFAULT 0.0,
    status VARCHAR(50) DEFAULT 'DRAFT',
    fk_media_id BIGINT NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    CONSTRAINT fk_book_media FOREIGN KEY (fk_media_id) REFERENCES media(id_media) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS serie (
    id_serie BIGINT AUTO_INCREMENT PRIMARY KEY,
    slug VARCHAR(255) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    original_title VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    num_seasons INT DEFAULT 1,
    num_episodes INT DEFAULT 1,
    synopsis LONGTEXT NOT NULL,
    poster_url VARCHAR(500),
    rating DECIMAL(3, 1) DEFAULT 0.0,
    status VARCHAR(50) DEFAULT 'DRAFT',
    fk_media_id BIGINT NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    CONSTRAINT fk_serie_media FOREIGN KEY (fk_media_id) REFERENCES media(id_media) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 4. SERIES HIERARCHY

CREATE TABLE IF NOT EXISTS season (
    id_season BIGINT AUTO_INCREMENT PRIMARY KEY,
    season_num INT DEFAULT 1 NOT NULL,
    title VARCHAR(255) NOT NULL,
    synopsis LONGTEXT NOT NULL,
    poster_url VARCHAR(500),
    fk_serie_season BIGINT NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    CONSTRAINT fk_season_serie FOREIGN KEY (fk_serie_season) REFERENCES serie(id_serie) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS episode (
    id_episode BIGINT AUTO_INCREMENT PRIMARY KEY,
    season_num INT DEFAULT 1 NOT NULL,
    title VARCHAR(255) NOT NULL,
    synopsis LONGTEXT NOT NULL,
    poster_url VARCHAR(500),
    fk_season_episode BIGINT NOT NULL,
    UNIQUE KEY uc_season_episode (id_episode, season_num),
    CONSTRAINT fk_episode_season FOREIGN KEY (fk_season_episode) REFERENCES season(id_season) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 5. GAMES MODULE

CREATE TABLE IF NOT EXISTS game (
    id_game BIGINT AUTO_INCREMENT PRIMARY KEY,
    slug VARCHAR(255) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    synopsis LONGTEXT NOT NULL,
    release_date DATE,
    dev VARCHAR(255) NOT NULL,
    publisher VARCHAR(255),
    cover_url VARCHAR(500),
    rating DECIMAL(3, 1) DEFAULT 0.0,
    status VARCHAR(50) NOT NULL,
    fk_media_id BIGINT NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    CONSTRAINT fk_game_media FOREIGN KEY (fk_media_id) REFERENCES media(id_media) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS platform (
    id_platform BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    manufacturer VARCHAR(255),
    release_date DATE,
    logo_url VARCHAR(500),
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS game_platform (
    fk_game_id BIGINT NOT NULL,
    fk_platform_id BIGINT NOT NULL,
    added_at DATETIME(6) NOT NULL,
    PRIMARY KEY (fk_game_id, fk_platform_id),
    CONSTRAINT fk_gp_game FOREIGN KEY (fk_game_id) REFERENCES game(id_game) ON DELETE CASCADE,
    CONSTRAINT fk_gp_platform FOREIGN KEY (fk_platform_id) REFERENCES platform(id_platform) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;