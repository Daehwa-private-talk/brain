-- chat
DROP TABLE IF EXISTS `chat_room`;
DROP TABLE IF EXISTS `chat_room_user`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `chat_room`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(255) NOT NULL,
    `created_by` VARCHAR(255) NOT NULL,
    `created_at` DATETIME     NOT NULL,
    `updated_at` DATETIME     NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `chat_room_user`
(
    `id`           INT NOT NULL AUTO_INCREMENT,
    `chat_room_id` INT NOT NULL,
    `user_id`      INT NOT NULL,
    `created_at` DATETIME     NOT NULL,
    `updated_at` DATETIME     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_chat_room_user` (`chat_room_id`, `user_id`)
) ENGINE = InnoDB;

CREATE TABLE `user`
(
    `id`       INT          NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(255) NOT NULL,
    `nickname` VARCHAR(255) NOT NULL,
    `email`    VARCHAR(255) NOT NULL,
    `created_at` DATETIME     NOT NULL,
    `updated_at` DATETIME     NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;
