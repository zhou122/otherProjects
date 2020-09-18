DROP TABLE IF EXISTS t_user;

CREATE TABLE t_user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	old_name VARCHAR(30) NULL DEFAULT NULL COMMENT '老姓名',
	new_name VARCHAR(30) NULL DEFAULT NULL COMMENT '新姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);