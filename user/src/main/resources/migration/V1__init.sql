-- user
DROP TABLE IF EXISTS `daehwa_user`;
DROP TABLE IF EXISTS `friend_map`;
DROP TABLE IF EXISTS `profile`;

CREATE TABLE `daehwa_user`
(
    `id`                       INT          NOT NULL AUTO_INCREMENT,
    `email`                    VARCHAR(255) NOT NULL,
    `password`                 VARCHAR(100) NOT NULL,
    `name`                     VARCHAR(100) NOT NULL,
    `role`                     VARCHAR(20)  NOT NULL,
    `is_enabled`               TINYINT      NOT NULL,
    `is_deleted`               TINYINT      NOT NULL,
    `created_at`               DATETIME     NOT NULL,
    `updated_at`               DATETIME     NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `friend_map`
(
    `id`                INT      NOT NULL AUTO_INCREMENT,
    `user_profile_id`   INT      NOT NULL,
    `friend_profile_id` INT      NOT NULL,
    `created_at`        DATETIME NOT NULL,
    `updated_at`        DATETIME NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `profile`
(
    `id`             INT         NOT NULL AUTO_INCREMENT,
    `nickname`       VARCHAR(50) NULL,
    `image`          VARCHAR(255) NULL,
    `status_message` VARCHAR(50) NULL,
    `user_id`        INT         NOT NULL,
    `created_at`     DATETIME    NOT NULL,
    `updated_at`     DATETIME    NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;
