CREATE TABLE `mailqueue` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `from` varchar(50) NOT NULL,
  `to` varchar(50) NOT NULL,
  `subject` VARCHAR(255),
  `content` TEXT,
  `sendDate` DATE,
  `inserted` DATETIME,
  `status` ENUM('1','2') DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;