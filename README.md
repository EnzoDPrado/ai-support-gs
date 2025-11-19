# Backend AI MAP - GLOBAL SOLUTION 

## Crie as seguintes tabelas no seu banco da fiap


CREATE SEQUENCE seq_tb_users
START WITH 1
INCREMENT BY 1;

CREATE TABLE tb_users (
cd_user        integer PRIMARY KEY,
name           VARCHAR2(155) NOT NULL,
email          VARCHAR2(155) NOT NULL,
password       VARCHAR2(155) NOT NULL
);


CREATE SEQUENCE seq_tb_soft_skils
START WITH 1
INCREMENT BY 1;

CREATE TABLE tb_soft_skils (
cd_soft_skils  integer PRIMARY KEY,
cd_user        integer NOT NULL,
name           VARCHAR2(30) NOT NULL,
CONSTRAINT fk_tb_soft_skils_on_tb_users FOREIGN KEY (cd_user)
REFERENCES tb_users (cd_user)
);


CREATE SEQUENCE seq_tb_courses
START WITH 1
INCREMENT BY 1;

CREATE TABLE tb_courses (
cd_courses     integer PRIMARY KEY,
cd_user        integer NOT NULL,
name           VARCHAR2(30) NOT NULL,
hours          NUMBER(10) NOT NULL,
description    CLOB,
CONSTRAINT fk_tb_courses_on_tb_users FOREIGN KEY (cd_user)
REFERENCES tb_users (cd_user)
);

CREATE SEQUENCE seq_tb_road_map
START WITH 1
INCREMENT BY 1;

CREATE TABLE tb_road_map (
cd_road_map    integer PRIMARY KEY,
cd_user        integer NOT NULL,
title          varchar(120) NOT NULL,
description    CLOB NOT NULL,
CONSTRAINT fk_tb_road_map_on_tb_users FOREIGN KEY (cd_user)
REFERENCES tb_users (cd_user)
);

## Após isto vá ao arquivo DatabaseConnection e insira as suas credenciais do banco da FIAP

## Só rodar o projeto :)