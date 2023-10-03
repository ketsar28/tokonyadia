CREATE DATABASE incubation_spring_restful_api;
CREATE DATABASE tokonyadiadb;

SHOW DATABASES ;
USE incubation_spring_restful_api;
USE tokonyadiadb;

SHOW TABLES;

DESC m_store;
DESC m_product;
DESC m_product_price;
DESC m_customer;
DESC m_order;
DESC m_order_detail;
DESC m_role;
DESC m_seller;
DESC m_user_credential;

SHOW CREATE TABLE m_store;
SHOW CREATE TABLE m_product;
SHOW CREATE TABLE m_product_price;
SHOW CREATE TABLE m_order;
SHOW CREATE TABLE m_order_detail;

SELECT * FROM m_store;
SELECT * FROM m_product_price;
SELECT * FROM m_product;
SELECT * FROM m_customer;
SELECT * FROM m_order;
SELECT * FROM m_order_detail;
SELECT * FROM m_role;
SELECT * FROM m_user_role;
SELECT * FROM m_seller;
SELECT * FROM m_user_credential;

DELETE FROM m_store;
DELETE FROM m_product;
DELETE FROM m_product_price;
DELETE FROM m_customer;
DELETE FROM m_order;
DELETE FROM m_order_detail;



DROP TABLE m_store;
DROP TABLE m_product;
DROP TABLE m_product_price;
DROP TABLE m_customer;
DROP TABLE m_order;
DROP TABLE m_order_detail;