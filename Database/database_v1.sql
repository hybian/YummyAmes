/*create database yummy_ames; /** Creating Database **/

use yummy_ames; /** Selecting Database **/

create table Restaurant(
	r_id int primary key,
	r_name varchar(30) not null,
	r_address varchar(50) not null unique,
	r_phone varchar(20) not null unique,
	r_email varchar(30) not null unique,
	r_usrname varchar(10) not null unique,
	r_pwd varchar(20) not null,
	created_at datetime,
	updated_at datetime null
); /** Creating Restaurant Users Table **/

create table Customer(
	c_id int primary key,
    c_name varchar(30) not null,
    c_address varchar(50) not null unique,
	c_phone varchar(20) not null unique,
	c_email varchar(30) not null unique,
	c_usrname varchar(10) not null unique,
	c_pwd varchar(20) not null,
	created_at datetime,
	updated_at datetime null
); /** Creating Customer Users Table **/

create table Deliver(
	d_id int primary key,
    d_name varchar(30) not null,
	d_phone varchar(20) not null unique,
	d_email varchar(30) not null unique,
	d_usrname varchar(10) not null unique,
	d_pwd varchar(20) not null,
	created_at datetime,
	updated_at datetime null
); /** Creating Deliver Users Table **/

create table Admin(
	a_id int primary key,
    a_name char(30) not null,
	a_usrname char(10) not null unique,
	a_pwd char(20) not null
); /** Creating Administrator Table **/

create table Food(
	p_id int primary key,
    p_name char(30) not null,
	p_price int not null,
	p_type char(20) not null,
    p_from int not null references Restaurant(r_id)
); /** Creating food Table **/

create table Orderlist(
	o_id int primary key,
    o_price int not null not null,
    o_cusname char(30) not null,
    o_cusphone int,
    o_address char(50) not null,
    o_res int not null references Restaurant(r_id),
	o_cus int not null references Customer(c_id),
	o_deli int not null references Deliver(d_id)
); /** Creating order Table **/