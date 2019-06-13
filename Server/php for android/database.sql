create database db490aoliu; /** Creating Database **/

use foodAmes; /** Selecting Database **/
use db490aoliu;


create table Restaurant(
	r_id int primary key NOT NULL AUTO_INCREMENT,
	r_name varchar(30) not null,
	r_address varchar(50) not null unique,
	r_phone varchar(20) not null unique,
	r_email varchar(30) not null unique,
	r_username varchar(10) not null unique,
	r_pwd varchar(20) not null,
	created_at datetime
); /** Creating Restaurant Users Table **/

create table Customer(
	c_id int primary key NOT NULL AUTO_INCREMENT,
    c_name varchar(30) not null,
    c_address varchar(50) not null unique,
	c_phone varchar(30) not null unique,
	c_email varchar(30) not null unique,
	c_username varchar(30) not null unique,
	c_pwd varchar(30) not null,
	created_at datetime
); /** Creating Customer Users Table **/

DROP TABLE Customer ;


create table Deliver(
	d_id int primary key NOT NULL AUTO_INCREMENT,
    d_name varchar(30) not null,
	d_phone varchar(20) not null unique,
	d_email varchar(30) not null unique,
	d_username varchar(10) not null unique,
	d_pwd varchar(20) not null,
	created_at datetime
); /** Creating Deliver Users Table **/

create table Admin(
	a_id int primary key NOT NULL AUTO_INCREMENT,
    a_name char(30) not null,
	a_username char(10) not null unique,
	a_pwd char(20) not null
); /** Creating Administrator Table **/

UPDATE Food set f_price = 10 where f_id=4;

create table Food(
	f_id int primary key NOT NULL AUTO_INCREMENT,
    f_name char(30) not null,
	f_price int not null,
    f_char varchar(100) NOT NULL,
	f_type char(20) not null,
    f_from varchar(100) not null references Restaurant(r_name),
    binarydata mediumblob NOT NULL,
    fp_type varchar(100) NOT NULL
)ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

create table Orderlist(
	o_id int primary key NOT NULL AUTO_INCREMENT,
    o_price int,
    o_cusname char(30) not null,
    o_cusphone varchar(20) not null unique,
    o_address char(50) not null,
    o_res int,
	o_cus int,
	o_deli int,
    situation int not null
); /** Creating order Table **/

create table Cart(
	ca_id int primary key NOT NULL AUTO_INCREMENT,
    ca_name char(30) not null,
    ca_price int not null,
    ca_type char(50) not null,
    c_from varchar(100) not null,
    c_owner char(30) not null,
    c_res int
); 

CREATE TABLE photo (  
  id int(10) unsigned NOT NULL auto_increment,  
  type varchar(100) NOT NULL,  
  binarydata mediumblob NOT NULL,  
  PRIMARY KEY  (id)  
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

