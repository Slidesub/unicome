-- mysql
CREATE DATABASE IF NOT EXISTS `UNICOME_BASIC` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE UNICOME_BASIC;

CREATE TABLE `USER`(
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL COMMENT '主键',
    username VARCHAR(256) COMMENT '用户名',
    password VARCHAR(256) COMMENT '密码',
    nickname VARCHAR(256) COMMENT '昵称',
    mobile VARCHAR(256) COMMENT '手机号',
    email VARCHAR(256) COMMENT '邮箱',
    enabled TINYINT DEFAULT 1 COMMENT '是否可用{1: 可用, 0: 不可用}',
    account_non_expired TINYINT DEFAULT 1 COMMENT '账户是否过期{1: 未过期, 0: 过期}',
    credentials_non_expired TINYINT DEFAULT 1 COMMENT '密码是否过期{1: 未过期, 0: 过期}',
    account_non_locked TINYINT DEFAULT 1 COMMENT '账户是否锁定{1: 未锁定, 0: 锁定}',
    created_by INTEGER COMMENT '创建者',
    updated_by INTEGER COMMENT '更新者',
    created_at DATETIME COMMENT '创建日期',
    updated_at DATETIME COMMENT '更新日期'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

CREATE TABLE `GROUP`(
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL COMMENT '主键',
    name VARCHAR(256) COMMENT '名称',
    remark VARCHAR(256) COMMENT '备注',
    enabled TINYINT DEFAULT 1 COMMENT '是否可用{1: 可用, 0: 不可用}',
    created_by INTEGER COMMENT '创建者',
    updated_by INTEGER COMMENT '更新者',
    created_at DATETIME COMMENT '创建日期',
    updated_at DATETIME COMMENT '更新日期'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组表';

CREATE TABLE `ROLE`(
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL COMMENT '主键',
    name VARCHAR(256) COMMENT '名称',
    remark VARCHAR(256) COMMENT '备注',
    enabled TINYINT DEFAULT 1 COMMENT '是否可用{1: 可用, 0: 不可用}',
    created_by INTEGER COMMENT '创建者',
    updated_by INTEGER COMMENT '更新者',
    created_at DATETIME COMMENT '创建日期',
    updated_at DATETIME COMMENT '更新日期'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

CREATE TABLE `AUTHORITY`(
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL COMMENT '主键',
    name VARCHAR(256) COMMENT '名称',
    remark VARCHAR(256) COMMENT '备注',
    enabled TINYINT DEFAULT 1 COMMENT '是否可用{1: 可用, 0: 不可用}',
    created_by INTEGER COMMENT '创建者',
    updated_by INTEGER COMMENT '更新者',
    created_at DATETIME COMMENT '创建日期',
    updated_at DATETIME COMMENT '更新日期'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

CREATE TABLE `USER_GROUP`(
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL COMMENT '主键',
    user_id INTEGER NOT NULL COMMENT '用户id',
    group_id INTEGER NOT NULL COMMENT '组id',
    enabled TINYINT DEFAULT 1 COMMENT '是否可用{1: 可用, 0: 不可用}',
    created_by INTEGER COMMENT '创建者',
    updated_by INTEGER COMMENT '更新者',
    created_at DATETIME COMMENT '创建日期',
    updated_at DATETIME COMMENT '更新日期',
    FOREIGN KEY (`user_id`) REFERENCES `USER` (`id`),
    FOREIGN KEY (`group_id`) REFERENCES `GROUP` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-组表';

CREATE TABLE `USER_ROLE`(
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL COMMENT '主键',
    user_id INTEGER NOT NULL COMMENT '用户id',
    role_id INTEGER NOT NULL COMMENT '角色id',
    enabled TINYINT DEFAULT 1 COMMENT '是否可用{1: 可用, 0: 不可用}',
    created_by INTEGER COMMENT '创建者',
    updated_by INTEGER COMMENT '更新者',
    created_at DATETIME COMMENT '创建日期',
    updated_at DATETIME COMMENT '更新日期',
    FOREIGN KEY (`user_id`) REFERENCES `USER` (`id`),
    FOREIGN KEY (`role_id`) REFERENCES `ROLE` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色表';

CREATE TABLE `GROUP_ROLE`(
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL COMMENT '主键',
    group_id INTEGER NOT NULL COMMENT '组id',
    role_id INTEGER NOT NULL COMMENT '角色id',
    enabled TINYINT DEFAULT 1 COMMENT '是否可用{1: 可用, 0: 不可用}',
    created_by INTEGER COMMENT '创建者',
    updated_by INTEGER COMMENT '更新者',
    created_at DATETIME COMMENT '创建日期',
    updated_at DATETIME COMMENT '更新日期',
    FOREIGN KEY (`group_id`) REFERENCES `GROUP` (`id`),
    FOREIGN KEY (`role_id`) REFERENCES `ROLE` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组-角色表';

CREATE TABLE `ROLE_AUTHORITY`(
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL COMMENT '主键',
    role_id INTEGER NOT NULL COMMENT '角色id',
    authority_id INTEGER NOT NULL COMMENT '权限id',
    enabled TINYINT DEFAULT 1 COMMENT '是否可用{1: 可用, 0: 不可用}',
    created_by INTEGER COMMENT '创建者',
    updated_by INTEGER COMMENT '更新者',
    created_at DATETIME COMMENT '创建日期',
    updated_at DATETIME COMMENT '更新日期',
    FOREIGN KEY (`role_id`) REFERENCES `ROLE` (`id`),
    FOREIGN KEY (`authority_id`) REFERENCES `AUTHORITY` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-权限表';

CREATE TABLE `CLIENT`(
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL COMMENT '主键',
    client_id VARCHAR(256) COMMENT '客户端id',
    client_secret VARCHAR(256) COMMENT '客户端密钥',
    client_name VARCHAR(256) COMMENT '客户端名称',
    remark VARCHAR(256) COMMENT '备注',
    enabled TINYINT DEFAULT 1 COMMENT '是否可用{1: 可用, 0: 不可用}',
    authorities VARCHAR(1024) COMMENT '权限列表, 以,分割',
    scopes VARCHAR(1024) COMMENT '作用域列表, 以,分割',
    resource_ids VARCHAR(1024) COMMENT '资源列表, 以,分割',
    authorized_grant_types VARCHAR(256) COMMENT '授权类型列表, 以,分割',
    registered_redirect_uris VARCHAR(1024) COMMENT '跳转列表, 以,分割',
    auto_approve_scopes VARCHAR(1024) COMMENT '自动批准作用域列表, 以,分割',
    access_token_validity_seconds INTEGER DEFAULT 43200 COMMENT 'access token过期时间, 默认43200s',
    refresh_token_validity_seconds INTEGER DEFAULT 2592000 COMMENT 'refresh token过期时间, 默认2592000s',
    created_by INTEGER COMMENT '创建者',
    updated_by INTEGER COMMENT '更新者',
    created_at DATETIME COMMENT '创建日期',
    updated_at DATETIME COMMENT '更新日期'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户端表';

INSERT `USER`(username, password, nickname, mobile, email) values ('gd', '$2a$10$gdhK/REgXBig.zNDMDvFBOXLKhwcL36FFTL.2WcgsZ5E4F5.nBeS2',
'gd', '15562305255', 'gd@hotmail.com');
insert `USER`(username, password, nickname, mobile, email) values ('gd2', '$2a$10$gdhK/REgXBig.zNDMDvFBOXLKhwcL36FFTL.2WcgsZ5E4F5.nBeS2',
'gd2', '15562305251', 'gd2@hotmail.com');
insert `USER`(username, password, nickname, mobile, email) values ('gd3', '$2a$10$gdhK/REgXBig.zNDMDvFBOXLKhwcL36FFTL.2WcgsZ5E4F5.nBeS2',
'gd3', '15562305252', 'gd3@hotmail.com');
insert `USER`(username, password, nickname, mobile, email) values ('gd4', '$2a$10$gdhK/REgXBig.zNDMDvFBOXLKhwcL36FFTL.2WcgsZ5E4F5.nBeS2',
'gd4', '15562305253', 'gd4@hotmail.com');

INSERT `GROUP`(name, remark) values ('group', '组');
INSERT `GROUP`(name, remark) values ('group2', '组2');
INSERT `GROUP`(name, remark) values ('group3', '组3');
INSERT `GROUP`(name, remark) values ('group4', '组4');

INSERT `ROLE`(name, remark) values ('role', '角色');
INSERT `ROLE`(name, remark) values ('role2', '角色2');
INSERT `ROLE`(name, remark) values ('role3', '角色3');
INSERT `ROLE`(name, remark) values ('role4', '角色4');

INSERT `AUTHORITY`(name, remark) values ('authority', '权限');
INSERT `AUTHORITY`(name, remark) values ('authority2', '权限2');
INSERT `AUTHORITY`(name, remark) values ('authority3', '权限3');
INSERT `AUTHORITY`(name, remark) values ('authority4', '权限4');

INSERT `USER_GROUP`(user_id, group_id) values (1, 1);
INSERT `USER_GROUP`(user_id, group_id) values (1, 2);
INSERT `USER_GROUP`(user_id, group_id) values (2, 1);
INSERT `USER_GROUP`(user_id, group_id) values (2, 2);

INSERT `USER_ROLE`(user_id, role_id) values (1, 1);
INSERT `USER_ROLE`(user_id, role_id) values (1, 2);
INSERT `USER_ROLE`(user_id, role_id) values (2, 1);
INSERT `USER_ROLE`(user_id, role_id) values (2, 2);

INSERT `GROUP_ROLE`(group_id, role_id) values (1, 1);
INSERT `GROUP_ROLE`(group_id, role_id) values (1, 2);
INSERT `GROUP_ROLE`(group_id, role_id) values (2, 1);
INSERT `GROUP_ROLE`(group_id, role_id) values (2, 2);

INSERT `ROLE_AUTHORITY`(role_id, authority_id) values (1, 1);
INSERT `ROLE_AUTHORITY`(role_id, authority_id) values (1, 2);
INSERT `ROLE_AUTHORITY`(role_id, authority_id) values (2, 1);c
INSERT `ROLE_AUTHORITY`(role_id, authority_id) values (2, 2);

-- admin - abc123_
INSERT `CLIENT`(client_id, client_secret, client_name, remark,
authorities, scopes, authorized_grant_types, registered_redirect_uris) VALUES
('admin', '$2a$10$gdhK/REgXBig.zNDMDvFBOXLKhwcL36FFTL.2WcgsZ5E4F5.nBeS2', '客户端a', '测试客户端', '', 'all, abc',
'authorization_code, password, refresh_token, client_credentials',
'http://localhost/login');