
CREATE SCHEMA IF NOT EXISTS `newdb` DEFAULT CHARACTER SET utf8 ;
USE `newdb` ;

-- -----------------------------------------------------
-- Table `newdb`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `newdb`.`users` ;

CREATE TABLE IF NOT EXISTS `newdb`.`users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(20) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `reg_date` DATETIME NOT NULL DEFAULT now(),
  `mail` VARCHAR(45) NOT NULL,
  `role` SMALLINT(1) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_nickname_UNIQUE` (`nickname` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `newdb`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `newdb`.`order` ;

CREATE TABLE IF NOT EXISTS `newdb`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sendable` TINYINT(1) NOT NULL,
  `custom_price` FLOAT UNSIGNED NOT NULL,
  `name` VARCHAR(60) NOT NULL,
  `phone` VARCHAR(14) NOT NULL,
  `mail` VARCHAR(45) NOT NULL,
  `updated` TIMESTAMP(6) NULL,
  `cancel_date` DATE NULL,
  `users_id` INT unsigned NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_users1_idx` (`users_id` ASC),
  CONSTRAINT `fk_order_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `newdb`.`users` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `newdb`.`disk`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `newdb`.`disk` ;

CREATE TABLE IF NOT EXISTS `newdb`.`disk` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(30),
  `sellable` TINYINT(1) NOT NULL DEFAULT 0,
  `release` YEAR NULL,
  `price` FLOAT NOT NULL,
  `stored` INT UNSIGNED NOT NULL,
  `description` TEXT(1538) NULL,
  `cover` VARCHAR(45) NULL,
  `rating` ENUM('G', 'PG', 'PG13', 'R', 'NC17') NOT NULL DEFAULT 'G',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `newdb`.`genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `newdb`.`genre` ;

CREATE TABLE IF NOT EXISTS `newdb`.`genre` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `newdb`.`delivery`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `newdb`.`delivery` ;

CREATE TABLE IF NOT EXISTS `newdb`.`delivery` (
  `order_id` INT NOT NULL,
  `response_date` DATE NULL,
  `request_date` DATE NULL,
  `region` VARCHAR(45) NOT NULL,
  `city` VARCHAR(40) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `post_index` INT UNSIGNED NOT NULL,
  INDEX `fk_delivery_order1_idx` (`order_id` ASC),
  PRIMARY KEY (`order_id`),
  CONSTRAINT `fk_delivery_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `newdb`.`order` (`id`)
    ON DELETE CASCE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;




-- -----------------------------------------------------
-- Table `newdb`.`disk_genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `newdb`.`disk_genre` ;

CREATE TABLE IF NOT EXISTS `newdb`.`disk_genre` (
  `disk_id` INT NOT NULL,
  `genre_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`disk_id`, `genre_id`),
  INDEX `fk_disk_genre_genre1_idx` (`genre_id` ASC),
  CONSTRAINT `fk_disk_genre_disk1`
    FOREIGN KEY (`disk_id`)
    REFERENCES `newdb`.`disk` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_disk_genre_genre1`
    FOREIGN KEY (`genre_id`)
    REFERENCES `newdb`.`genre` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `newdb`.`order_disk`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `newdb`.`order_disk` ;

CREATE TABLE IF NOT EXISTS `newdb`.`order_disk` (
  `order_id` INT NOT NULL,
  `disk_id` INT NOT NULL,
  `return_date` DATE NULL,
  `hire_end` DATE NULL,
  `count` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`order_id`, `disk_id`),
  INDEX `fk_order-disk_disk1_idx` (`disk_id` ASC),
  UNIQUE INDEX `order_id_UNIQUE` (`order_id` ASC),
  CONSTRAINT `fk_order-disk_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `newdb`.`order` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_order-disk_disk1`
    FOREIGN KEY (`disk_id`)
    REFERENCES `newdb`.`disk` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `newdb`.`actor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `newdb`.`actor` ;

CREATE TABLE IF NOT EXISTS `newdb`.`actor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `newdb`.`disk_actor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `newdb`.`disk_actor` ;

CREATE TABLE IF NOT EXISTS `newdb`.`disk_actor` (
  `disk_id` INT NOT NULL,
  `actor_id` INT NOT NULL,
  PRIMARY KEY (`disk_id`, `actor_id`),
  INDEX `fk_disk_actor_actor1_idx` (`actor_id` ASC),
  CONSTRAINT `fk_disk_actor_disk1`
    FOREIGN KEY (`disk_id`)
    REFERENCES `newdb`.`disk` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_disk_actor_actor1`
    FOREIGN KEY (`actor_id`)
    REFERENCES `newdb`.`actor` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `newdb`;
DROP procedure IF EXISTS `exActor`;

DELIMITER $$
USE `newdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `exActor`(in bid int,in aut varchar(60))
BEGIN

set @ex = (select actor.id from actor where `name` = aut);
    IF (@ex is null)THEN
	insert into actor (name) value (aut);
    insert into disk_actor (disk_id, actor_id) values (bid, last_insert_id());
    end if;
	if (@ex is not  null) then
    insert into disk_actor (disk_id, actor_id) values (bid, @ex);
	end if;
    
END$$

DELIMITER ;


USE `newdb`;
DROP procedure IF EXISTS `exGenre`;

DELIMITER $$
USE `newdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `exGenre`(in bid int,in gen varchar(30))
BEGIN

set @ex = (select genre.id from genre where `name` = gen);

    IF (@ex is null)THEN
	insert into genre (name) value (gen);
    insert into disk_genre (disk_id, genre_id) values (bid, last_insert_id());
    end if;
	if (@ex is not null) then
    insert into disk_genre (disk_id, genre_id) values (bid, @ex);
	end if;
     
END$$

DELIMITER ;

USE `newdb`;
DROP procedure IF EXISTS `optionOrder`;

DELIMITER $$
USE `newdb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `optionOrder`(in opt int, in oid int)
begin
case
when opt = 1
then
set @che = (select cancel_date from `order` where `order`.id = oid);
if(@che is null) then
UPDATE `order` SET confirmed=now(), updated=current_timestamp WHERE `order`.id = oid;
end if;
when opt = 2
then
set @che = (select confirmed from `order` where `order`.id = oid);
if(@che is null) then
UPDATE `order` SET cancel_date=now(), updated=current_timestamp WHERE `order`.id = oid;
end if;
when opt = 3
then
set @che = (select confirmed from `order` where `order`.id = oid);
if(@che is not null) then
UPDATE `delivery`, `order` SET request_date=now(), `order`.updated=current_timestamp WHERE  order_id = `order`.id and `order`.id = oid;
end if;
when opt = 4
then
UPDATE `order` SET earned=now(), updated=current_timestamp WHERE `order`.id = oid;

when opt = 5
then
UPDATE `order` SET cancel_date=null, updated=current_timestamp WHERE `order`.id = oid;

when opt = 6
then
DELETE FROM `order` WHERE `order`.`id`= oid;
end case;
end$$

DELIMITER ;



USE `newdb`;

DELIMITER $$

DROP TRIGGER IF EXISTS newdb.order_AFTER_UPDATE$$
USE `newdb`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `newdb`.`order_AFTER_UPDATE` AFTER UPDATE ON `order` FOR EACH ROW
BEGIN
	DECLARE done INT DEFAULT 0;
	DECLARE ocount INT DEFAULT 0;
	DECLARE diskid INT DEFAULT 0; 
	DECLARE cur CURSOR FOR 
		SELECT `count`, disk_id FROM `order_disk`
		WHERE  `order_id` = NEW.`id`;
    DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
    IF NEW.`cancel_date` is not null AND `OLD`.`cancel_date` is null AND new.`confirmed` is null THEN
		OPEN cur;
		FETCH cur INTO ocount, diskid;
		WHILE done = 0 DO
			UPDATE `disk` SET `stored` = (ocount + `stored`) WHERE disk.`id` = diskid; 
			FETCH cur INTO ocount, diskid;
		END WHILE; 
	END IF;
    
    IF NEW.`cancel_date` is null AND `OLD`.`cancel_date` is not null AND new.`confirmed` is null THEN
    		OPEN cur;
		FETCH cur INTO ocount, diskid;
		WHILE done = 0 DO
			UPDATE `disk` SET `disk`.`stored` = (`disk`.`stored` - ocount ) WHERE disk.`id` = diskid; 
			FETCH cur INTO ocount, diskid;
		END WHILE; 
	END IF;
END$$
DELIMITER ;


USE `newdb`;

DELIMITER $$

DROP TRIGGER IF EXISTS newdb.order_disk_BEFORE_INSERT$$
USE `newdb`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `newdb`.`order_disk_BEFORE_INSERT` BEFORE INSERT ON `order_disk` FOR EACH ROW
BEGIN 


    SET @store = (select `stored` from disk where id = NEW.`disk_id`) - NEW.`count`;
    UPDATE disk SET disk.`stored` = @store, rating = rating + new.`count`  where id = NEW.disk_id;

END$$
DELIMITER ;

CREATE DEFINER=`root`@`localhost` TRIGGER `newdb`.`order_disk_BEFORE_UPDATE` BEFORE UPDATE ON `order_disk` FOR EACH ROW
BEGIN
if(new.return_date is not null and `old`.return_date is null) then
UPDATE `disk` SET disk.`stored` =  disk.`stored` + new.`count`
  WHERE  new.disk_id = disk.id;
end if;
if(new.return_date is null and `old`.return_date is not null) then
UPDATE `disk` SET disk.`stored` =  disk.`stored` - new.`count`
  WHERE  new.disk_id = disk.id;
end if;
END

