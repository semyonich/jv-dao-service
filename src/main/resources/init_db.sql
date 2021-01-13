CREATE SCHEMA `taxi-service` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `taxi-service`.`manufacturers` (
                                                `manufacturer_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                `manufacturer_name` VARCHAR(45) NOT NULL,
                                                `manufacturer_country` VARCHAR(45) NOT NULL,
                                                `deleted` TINYINT NOT NULL DEFAULT 0,
                                                PRIMARY KEY (`manufacturer_id`));
