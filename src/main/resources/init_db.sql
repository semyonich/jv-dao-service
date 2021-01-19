CREATE SCHEMA `taxi-service` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `taxi-service`.`manufacturers` (
                                                `manufacturer_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                                `manufacturer_name` VARCHAR(45) NOT NULL,
                                                `manufacturer_country` VARCHAR(45) NOT NULL,
                                                `deleted` TINYINT NOT NULL DEFAULT 0,
                                                PRIMARY KEY (`manufacturer_id`));
CREATE TABLE `taxi-service`.`drivers` (
                                          `driver_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                          `driver_name` VARCHAR(100) NOT NULL,
                                          `driver_license` VARCHAR(150) NOT NULL,
                                          `deleted` TINYINT NOT NULL DEFAULT 0,
                                          PRIMARY KEY (`driver_id`));
CREATE TABLE `taxi-service`.`cars` (
                                       `car_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                       `manufacturer_id` BIGINT(11) NOT NULL,
                                       `car_model` VARCHAR(100) NOT NULL,
                                       `deleted` TINYINT NOT NULL DEFAULT 0,
                                       PRIMARY KEY (`car_id`),
                                       INDEX `cars_manufacturers_fk_idx` (`manufacturer_id` ASC) VISIBLE,
                                       CONSTRAINT `cars_manufacturers_fk`
                                           FOREIGN KEY (`manufacturer_id`)
                                               REFERENCES `taxi-service`.`manufacturers` (`manufacturer_id`)
                                               ON DELETE NO ACTION
                                               ON UPDATE NO ACTION);
CREATE TABLE `taxi-service`.`cars_drivers` (
                                               `driver_id` BIGINT(11) NOT NULL,
                                               `car_id` BIGINT(11) NOT NULL,
                                               INDEX `cd_cars_fk_idx` (`car_id` ASC) VISIBLE,
                                               CONSTRAINT `cd_drivers_fk`
                                                   FOREIGN KEY (`driver_id`)
                                                       REFERENCES `taxi-service`.`drivers` (`driver_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION,
                                               CONSTRAINT `cd_cars_fk`
                                                   FOREIGN KEY (`car_id`)
                                                       REFERENCES `taxi-service`.`cars` (`car_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION);
