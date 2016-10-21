CREATE TABLE `sentmails` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `from` varchar(50) NOT NULL,
  `to` varchar(50) NOT NULL,
  `subject` VARCHAR(255),
  `content` TEXT,
  `sentDate` DATE,
  `inserted` DATETIME,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;