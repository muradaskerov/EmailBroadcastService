CREATE TABLE `config` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `mail_host` varchar(50) NOT NULL,
  `mail_port` int(5) NOT NULL,
  `mail_transport_protocol` varchar(10) NOT NULL,
  `mail_smtp_auth` BOOL NOT NULL DEFAULT TRUE ,
  `mail_smtp_starttls_enable` BOOL NOT NULL DEFAULT TRUE ,
  `mail_fullmail` varchar(50) NOT NULL,
  `mail_username` varchar(50) NOT NULL,
  `mail_password` varchar(50) NOT NULL,
  `mail_cronjob` varchar(50) NOT NULL,
  `mail_fromname` VARCHAR(50) NOT NULL,
  `mail_daily_limit` INT(5) NOT NULL ,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

INSERT INTO `config`(mail_host,mail_port,mail_transport_protocol,mail_fullmail,mail_username,mail_password,mail_cronjob,mail_fromname,mail_daily_limit)
VALUES ('smtp.default.com',0000,'smtp','default@mail.com','default','default','* 5 * * * *','Email Broadcast Service',100)