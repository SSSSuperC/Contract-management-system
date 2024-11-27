-- 创建数据库
-- create database contract;

-- 使用数据库
use contract;

-- 用户表
create table user (
                      id int unsigned primary key auto_increment comment 'ID',
                      username varchar(20) not null unique comment '用户名',
                      password varchar(32)  comment '密码',
                      nickname varchar(10)  default '' comment '昵称',
                      email varchar(128) default '' comment '邮箱',
                      user_pic varchar(128) default '' comment '头像',
                      create_time datetime not null comment '创建时间',
                      update_time datetime not null comment '修改时间'
) comment '用户表';

-- 分类表
create table category(
                         id int unsigned primary key auto_increment comment 'ID',
                         category_name varchar(32) not null comment '分类名称',
                         category_alias varchar(32) not null comment '分类别名',
                         create_user int unsigned not null comment '创建人ID',
                         create_time datetime not null comment '创建时间',
                         update_time datetime not null comment '修改时间',
                         constraint fk_category_user foreign key (create_user) references user(id) -- 外键约束
)comment '分类表';

-- 文书表
create table article(
                        id int unsigned primary key auto_increment comment 'ID',
                        title varchar(30) not null comment '文章标题',
                        content varchar(10000) not null comment '文章内容',
                        cover_img varchar(128) not null  comment '文章封面',
                        state varchar(3) default '草稿' comment '文章状态: 只能是[已发布] 或者 [草稿]',
                        category_id int unsigned comment '文章分类ID',
                        create_user int unsigned not null comment '创建人ID',
                        create_time datetime not null comment '创建时间',
                        update_time datetime not null comment '修改时间',
                        party_a varchar(80) comment'甲方',
                        party_b varchar(80) comment'乙方',
                        location varchar(80) comment'地点',
                        note varchar(10000) comment '文章笔记',
                        checked bool not null comment '是否被查看',
                        constraint fk_article_category foreign key (category_id) references category(id),-- 外键约束
                        constraint fk_article_user foreign key (create_user) references user(id) -- 外键约束
)comment '文书表';

-- 企业表
create table business(
						id int unsigned primary key auto_increment comment 'ID',
                        business_name  varchar(30) not null comment '公司名',
                        business_img varchar(128) not null  comment '公司图片',
                        business_url varchar(128) not null  comment '公司链接'
)comment '企业表';

-- 搜索记录
create table log(
	id int unsigned primary key auto_increment comment 'ID',
	title varchar(30)  comment '文章标题',
    party_a varchar(80) comment'甲方',
	party_b varchar(80) comment'乙方',
	location varchar(80) comment'地点',
    search_time datetime not null comment '查找时间'
)comment '搜索记录表'