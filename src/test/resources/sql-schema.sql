create database if not exists ims;
drop table if exists ims.customers;
CREATE TABLE customers(
		customer_id INT AUTO_INCREMENT,
		first_name VARCHAR(50) NOT NULL,
		last_name VARCHAR(50) NOT NULL,
		address VARCHAR(50) NOT NULL,
		email VARCHAR(50) NOT NULL,
		postcode VARCHAR(10) NOT NULL,
		PRIMARY KEY (customer_id)
);


drop table if exists ims.items;
CREATE TABLE items(
		item_id INT AUTO_INCREMENT,
		name VARCHAR(50) NOT NULL,
		price DOUBLE(4,2) NOT NULL,
		genre VARCHAR(10),
		min_players INT,
		max_players INT,
		avg_play_time INT,
		PRIMARY KEY (item_id)
);

drop table if exists ims.user;
CREATE TABLE user(
		user_id INT AUTO_INCREMENT,
		first_name VARCHAR(50) NOT NULL,
		last_name VARCHAR(50) NOT NULL,
		username VARCHAR(16) NOT NULL,
		PRIMARY KEY (user_id)
);

drop table if exists ims.orders;
CREATE TABLE orders(
		order_id INT AUTO_INCREMENT,
		customer_id INT,
		user_id INT,
		total_price DOUBLE(4,2) NOT NULL,
		date_ordered DATE NOT NULL,
		PRIMARY KEY (order_id),
		FOREIGN KEY (customer_id) REFERENCES customers (customer_id), 
		FOREIGN KEY (user_id) REFERENCES user (user_id)
);

drop table if exists ims.order_items;
CREATE TABLE order_items(
		order_id INT,
		item_id INT,
		FOREIGN KEY (order_id) REFERENCES orders (order_id),
		FOREIGN KEY (item_id) REFERENCES items (item_id)
);

drop table if exists ims.user_password;
CREATE TABLE user_password(
		user_id INT,
		password VARBINARY(128) NOT NULL,
		FOREIGN KEY (user_id) REFERENCES user (user_id)
);