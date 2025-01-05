create table sys_user (
    id BIGINT(20) not null auto_increment comment '主键',
    user_name varchar(64) not null default 'NULL' comment '用户名',
    nick_name varchar(64) not null default 'NULL' comment '昵称',
    password varchar(64) not null default 'NULL' comment '密码',
    status char(1) default '0' comment '账号状态（0正常 1停用）',
    email varchar(64) default NULL comment '邮箱',
    phonenumber varchar(32) default NULL comment '手机号',
    gender char(1) default null comment '用户性别（0 男 1 女 2 未知）',
    avatar varchar(128) default  null comment '头像',
    user_type char(1) not null default '1' comment '用户类型（0 管理员 1 普通用户）',
    create_by bigint(20) default null comment '创建者id',
    create_time DATETIME default null comment '创建时间',
    update_by bigint(20) default null comment '更新者id',
    update_time DATETIME default null comment '更新时间',
    del_flag int(11) default 0 comment '删除标志（0 未删除 1 已删除）',
    primary key (id)
) engine=INNODB AUTO_INCREMENT = 2  DEFAULT CHARSET=utf8mb4 comment "用户表"