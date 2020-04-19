DROP TABLE IF EXISTS ims;
CREATE TABLE IF NOT EXISTS ims;
DROP TABLE IF EXISTS ims.customers;
CREATE TABLE ims.customers(
customer_id INT AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
address VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
postcode VARCHAR(10) NOT NULL,
user_id INT,
PRIMARY KEY (customer_id)
FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE
);
DROP TABLE IF EXISTS ims.items;
CREATE TABLE ims.items(
item_id INT AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
price DOUBLE(4,2) NOT NULL,
genre VARCHAR(10),
min_players INT,
max_players INT,
avg_play_time INT,
user_id INT,
PRIMARY KEY (item_id)
FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE
);
DROP TABLE IF EXISTS ims.user;
CREATE TABLE ims.user(
user_id INT AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
username VARCHAR(16) NOT NULL,
PRIMARY KEY (user_id)
);
DROP TABLE IF EXISTS ims.orders;
CREATE TABLE ims.orders(
order_id INT AUTO_INCREMENT,
customer_id INT,
total_price DOUBLE(4,2) NOT NULL,
date_ordered DATE NOT NULL,
user_id INT,
PRIMARY KEY (order_id),
FOREIGN KEY (customer_id) REFERENCES customers (customer_id) ON DELETE CASCADE, 
FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE
);
DROP TABLE IF EXISTS ims.order_items;
CREATE TABLE ims.order_items(
order_id INT,
item_id INT,
FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE,
FOREIGN KEY (item_id) REFERENCES items (item_id) ON DELETE CASCADE
);
DROP TABLE IF EXISTS ims.user_password;
CREATE TABLE ims.user_password(
user_id INT,
password VARBINARY(128) NOT NULL,
FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE
);
insert into ims.customers(first_name, last_name, address, email, postcode) values('Luke1', 'Conway', '30 Test Road', 'Test@tester.com', 'testing');
insert into ims.customers(first_name, last_name, address, email, postcode) values('Luke2', 'Conway', '30 Test Road', 'Test@tester.com', 'testing');
insert into ims.customers(first_name, last_name, address, email, postcode) values('Luke3', 'Conway', '30 Test Road', 'Test@tester.com', 'testing');
insert into ims.customers(first_name, last_name, address, email, postcode) values('Luke4', 'Conway', '30 Test Road', 'Test@tester.com', 'testing');
insert into ims.items(name, price, genre, min_players, max_players, avg_play_time) values('Carcassonne', 20.00, 'Tile Based', 2, 12, 30);
insert into ims.items(name, price, genre, min_players, max_players, avg_play_time) values('Settlers of Catan', 30, 'Resource Management', 3, 6, 30);
insert into ims.items(name, price, genre, min_players, max_players, avg_play_time) values('Pandemic', 40.00, 'Players vs Game', 1, 6, 60);
insert into ims.orders(customer_id, total_price, date_ordered) values(1, 90.00, '2020-04-17');
insert into ims.orders(customer_id, total_price, date_ordered) values(2, 70.00, '2020-04-17');
insert into ims.orders(customer_id, total_price, date_ordered) values(3, 50.00, '2020-04-17');
insert into ims.order_items(order_id, item_id) values(1, 1);
insert into ims.order_items(order_id, item_id) values(1, 2);
insert into ims.order_items(order_id, item_id) values(1, 3);
insert into ims.order_items(order_id, item_id) values(2, 2);
insert into ims.order_items(order_id, item_id) values(2, 3);
insert into ims.order_items(order_id, item_id) values(3, 1);
insert into ims.order_items(order_id, item_id) values(3, 2);
INSERT INTO ims.user(first_name, last_name, username) VALUES('Luke','Conway','Admin');
INSERT INTO ims.user_password(user_id, password) VALUES('1',AES_ENCRYPT('securePass','Admin'));
INSERT INTO ims.user(first_name, last_name, username) VALUES('Bob','McDuck','Manager');
INSERT INTO ims.user_password(user_id, password) VALUES('1',AES_ENCRYPT('managerPass','Manager'));
