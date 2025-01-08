-- 创建demo库
create database demo;

-- 切换到demo库
use demo;

-- 创建sys_user表
drop table if exists sys_user;
create table sys_user
(
    id          bigint      not null auto_increment comment '主键',
    username    varchar(64) not null default '' comment '用户名',
    nickname    varchar(64) not null default '' comment '昵称',
    password    varchar(64) not null default '' comment '密码',
    status      char(1)              default '0' comment '账号状态（0正常 1停用）',
    email       varchar(64)          default NULL comment '邮箱',
    phonenumber varchar(32)          default NULL comment '手机号',
    gender      char(1)              default null comment '用户性别（0 男 1 女 2 未知）',
    avatar      varchar(128)         default null comment '头像',
    user_type   char(1)     not null default '1' comment '用户类型（0 管理员 1 普通用户）',
    create_by   bigint               default null comment '创建者id',
    create_time DATETIME             default null comment '创建时间',
    update_by   bigint               default null comment '更新者id',
    update_time DATETIME             default null comment '更新时间',
    del_flag    int                  default 0 comment '删除标志（0 未删除 1 已删除）',
    primary key (id)
) engine INNODB
  AUTO_INCREMENT 1
  DEFAULT CHARSET utf8mb4 comment "用户表";

-- 创建sys_menu表
drop table if exists sys_menu;
create table sys_menu
(
    id          bigint      not null auto_increment,
    menu_name   varchar(64) not null default '' comment '菜单名',
    path        varchar(200)         default null comment '路由地址',
    component   varchar(255)         default null comment '组件路径',
    visible     char(1)              default '0' comment '菜单状态(0显示 1隐藏)',
    status      char(1)              default '0' comment '菜单状态(0正常 1停用)',
    perms       varchar(100)         default null comment '权限标识',
    icon        varchar(100)         default null comment '菜单图标',
    create_by   bigint               default null comment '创建者id',
    create_time datetime             default null comment '创建时间',
    update_by   bigint               default null comment '更新者id',
    update_time datetime             default null comment '更新时间',
    del_flag    int                  default 0 comment '删除标志(0 未删除 1 已删除)',
    remark      varchar(500)         default null comment '备注',
    primary key (id)
) engine InnoDB
  auto_increment 1
  default charset utf8mb4 comment '菜单表';

-- 创建sys_role表
drop table if exists sys_role;
create table sys_role
(
    id          bigint not null auto_increment,
    name        varchar(128) default null comment '角色名称',
    role_key    varchar(100) default null comment '角色权限字符串',
    status      char(1)      default '0' comment '角色状态(0 正常 1 停用)',
    del_flag    int          default 0 comment '删除标志(0 未删除 1 已删除)',
    create_by   bigint       default null comment '创建者id',
    create_time datetime     default null comment '创建时间',
    update_by   bigint       default null comment '更新者id',
    update_time datetime     default null comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (id)
) engine InnoDB
  auto_increment 1
  default charset utf8mb4 comment '角色表';

-- 创建sys_role_menu表
drop table if exists sys_role_menu;
create table sys_role_menu
(
    role_id bigint not null auto_increment comment '角色id',
    menu_id bigint not null default 0 comment '菜单id',
    primary key (role_id, menu_id)
) engine innodb
  auto_increment 1
  default charset utf8mb4;

-- 创建sys_user_role表
drop table if exists sys_user_role;
create table sys_user_role
(
    user_id bigint not null auto_increment comment '用户id',
    role_id bigint not null default 0 comment '角色id',
    primary key (user_id, role_id)
) engine innodb
  auto_increment 1
  default charset utf8mb4;

-- 插入数据
insert into sys_user(username, password) value (
                                                'zhengwei',
                                                '$2a$10$mDQc5cQCxwzEQxiwiaSNteoDxNzLKSSual8utvvLIn2GJhBmrcZ4a'
    );
insert into sys_menu(menu_name, path, component, perms) value ('部门', 'sys/dept', 'Dept', 'sys:dept:list'), ('测试', 'sys/test', 'test', 'sys:test:list');
insert into sys_role(name, role_key) value ('CEO', 'ceo'), ('Coder', 'coder');
insert into sys_role_menu value (1, 1), (1, 2);
insert into sys_user_role value (1, 1), (1, 2);

-- 根据userid查询对应的权限信息
select u.id, m.perms from sys_user u ,sys_user_role ur,sys_role r,sys_role_menu rm , sys_menu m where u.id=ur.user_id and ur.role_id=r.id and rm.role_id=r.id and rm.menu_id = m.id and u.status=0 and u.del_flag=0 and m.status=0 and m.del_flag=0