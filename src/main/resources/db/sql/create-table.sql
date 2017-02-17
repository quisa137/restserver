CREATE SCHEMA IF NOT EXISTS `rest-server` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use `rest-server`;

CREATE TABLE IF NOT EXISTS `users` (
  `userno` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(120) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(200) NOT NULL,
  `isdeleted` char(1) DEFAULT 'N',
  `isEmailAuth` char(1) DEFAULT 'N',
  `lastLogin` timestamp NULL DEFAULT NULL,
  `writedate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `grantuserno` bigint(20) DEFAULT NULL,
  `usertype` char(1) DEFAULT 'U' COMMENT 'A: ������, M: ���� �����, U: �����',
  PRIMARY KEY (`userno`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `groups` (
  `groupno` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `addeduserno` bigint(20) DEFAULT NULL,
  `writedate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`groupno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `roles` (
  `roleno` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `groupno` bigint(20) DEFAULT NULL COMMENT '���� �Ҽӵ� �׷��� id',
  `addeduserno` bigint(20) DEFAULT NULL,
  `writedate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`roleno`),
  KEY `fk_groupno_idx` (`groupno`),
  KEY `fk_roles_groupno_idx` (`groupno`),
  KEY `fk_roles_addeduserno_idx` (`addeduserno`),
  CONSTRAINT `fk_roles_groupno` FOREIGN KEY (`groupno`) REFERENCES `groups` (`groupno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `group_user` (
  `groupno` bigint(20) NOT NULL,
  `userno` bigint(20) NOT NULL,
  `writedate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `addeduserno` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`groupno`,`userno`),
  KEY `fk_group_user_userno_idx` (`userno`),
  CONSTRAINT `fk_group_user_userno` FOREIGN KEY (`userno`) REFERENCES `users` (`userno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_user_groupno` FOREIGN KEY (`groupno`) REFERENCES `groups` (`groupno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `role_target` (
  `roleauthno` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleno` bigint(20) DEFAULT NULL COMMENT '�Ҽӵ� ���� ��',
  `targetURI` varchar(120) DEFAULT NULL,
  `targetMethod` char(1) DEFAULT 'A' COMMENT 'A = ALL, G = GET, P = POST, U = PUT, D = DELETE',
  `isDenied` char(1) DEFAULT 'Y',
  `addeduserno` bigint(20) DEFAULT NULL,
  `writedate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `permission` bigint(20) DEFAULT '0' COMMENT '0:���Ѿ���,1:���μ��� ����,2:�б�,4:����,8:����,16:�絵����, ������ �� ���ڵ��� ������ ��Ÿ����.',
  PRIMARY KEY (`roleauthno`),
  KEY `fk_role_target_roleno_idx` (`roleno`),
  CONSTRAINT `fk_role_target_roleno` FOREIGN KEY (`roleno`) REFERENCES `roles` (`roleno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='���Ѵ�� ���� ���̺�, �ش� URI�� ���ٽ� ���� ����';

CREATE TABLE IF NOT EXISTS `role_user` (
  `userno` bigint(20) NOT NULL,
  `groupno` bigint(20) NOT NULL,
  `roleno` bigint(20) NOT NULL,
  `writedate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `addeduserno` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userno`,`groupno`,`roleno`),
  KEY `fk_role_user_groupno_idx` (`groupno`),
  KEY `fk_role_user_roleno_idx` (`roleno`),
  CONSTRAINT `fk_role_user_userno` FOREIGN KEY (`userno`) REFERENCES `users` (`userno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_user_groupno` FOREIGN KEY (`groupno`) REFERENCES `groups` (`groupno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_user_roleno` FOREIGN KEY (`roleno`) REFERENCES `roles` (`roleno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='user�� �����ϰ� �ִ� ���� ����, ���� ǥ���ϸ� "A ������ B �׷��� C ���� �����ϰ� �ִ�"�� �ȴ�.';
