CREATE DATABASE db_happytravelling; USE db_happytravelling; 
 
CREATE TABLE t_user(username VARCHAR(50), pwd VARCHAR(50), roleid INT); 
CREATE TABLE t_role(roleid INT, roledesc VARCHAR(50)); 
CREATE TABLE t_login(username VARCHAR(50), roleid INT, lastlogin DATETIME, isactive TINYINT(1)); 
 
INSERT INTO t_role VALUES(1, 'ROLE_ADMIN'); 
INSERT INTO t_role VALUES(2, 'ROLE_USER');

CREATE TABLE t_penerbangan (
	asal VARCHAR(50),
	tujuan VARCHAR(50),
	tanggal_berangkat DATE,
	tanggal_kembali DATE,
	harga INT
);
	
INSERT INTO t_penerbangan (asal,tujuan)VALUES ('Medan','Jakarta');
INSERT INTO t_penerbangan (asal,tujuan)VALUES ('Bekasi','Yogyajarta');

