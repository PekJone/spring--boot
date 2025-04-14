CREATE TABLE `user` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(255) NOT NULL DEFAULT '',
	`password` VARCHAR(255) NOT NULL DEFAULT '',
	`sex` TINYINT(4) unsigned not null DEFAULT '0' COMMENT '性别 0女 1男',
	`deleted` TINYINT(4) unsigned not null DEFAULT '0' COMMENT '默认是0 不删除',
	`create_time` timestamp not null DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
	`update_time` timestamp not null DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY(`id`)
)ENGINE=INNODB AUTO_INCREMENT=1001 CHARSET=UTF8;