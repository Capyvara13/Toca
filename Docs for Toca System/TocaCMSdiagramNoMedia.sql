CREATE TABLE `Media`(
    `id_media` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `media_type` ENUM(
        'ARTICLE',
        'MOVIE',
        'SERIE',
        'BOOK',
        'MUSIC'
    ) NOT NULL
);
CREATE TABLE `User`(
    `id_user` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(100) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(60) NOT NULL,
    `bio` TEXT NULL,
    `role` BOOLEAN NOT NULL DEFAULT '0',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `actived` BOOLEAN NOT NULL DEFAULT '1'
);
CREATE TABLE `Article`(
    `id_article` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `slug` VARCHAR(255) NOT NULL,
    `user_id` BIGINT NOT NULL,
    `media_id` BIGINT NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    `content` TEXT NOT NULL,
    `summary` VARCHAR(500) NOT NULL,
    `view_count` BIGINT NOT NULL DEFAULT '0',
    `status` ENUM(
        'DRAFT',
        'PENDING',
        'PUBLISHED',
        'ARCHIVED'
    ) NOT NULL DEFAULT 'DRAFT',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
ALTER TABLE
    `Article` ADD INDEX `article_user_id_index`(`user_id`);
ALTER TABLE
    `Article` ADD INDEX `article_media_id_index`(`media_id`);
CREATE TABLE `Comment`(
    `id_comment` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `fk_article_id` BIGINT NOT NULL,
    `fk_user_id` BIGINT NOT NULL,
    `content` TEXT NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `parent_id` BIGINT NOT NULL
);
ALTER TABLE
    `Comment` ADD INDEX `comment_fk_article_id_index`(`fk_article_id`);
ALTER TABLE
    `Comment` ADD INDEX `comment_fk_user_id_index`(`fk_user_id`);
ALTER TABLE
    `Comment` ADD INDEX `comment_parent_id_index`(`parent_id`);
ALTER TABLE
    `Comment` ADD CONSTRAINT `comment_parent_id_foreign` FOREIGN KEY(`parent_id`) REFERENCES `Comment`(`id_comment`);
ALTER TABLE
    `Comment` ADD CONSTRAINT `comment_fk_article_id_foreign` FOREIGN KEY(`fk_article_id`) REFERENCES `Article`(`id_article`);
ALTER TABLE
    `Comment` ADD CONSTRAINT `comment_fk_user_id_foreign` FOREIGN KEY(`fk_user_id`) REFERENCES `User`(`id_user`);
ALTER TABLE
    `Article` ADD CONSTRAINT `article_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `User`(`id_user`);
ALTER TABLE
    `Article` ADD CONSTRAINT `article_media_id_foreign` FOREIGN KEY(`media_id`) REFERENCES `Media`(`id_media`);