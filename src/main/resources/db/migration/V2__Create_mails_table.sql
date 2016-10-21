CREATE TABLE `mails` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `mail` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `group` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;