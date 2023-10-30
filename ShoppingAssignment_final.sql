-- Create the database
CREATE DATABASE ShoppingAssignment;

use master
drop database ShoppingAssignment

-- Use the database
USE ShoppingAssignment;

-- Create the customers table
CREATE TABLE customers (
  userId INT IDENTITY(1,1) PRIMARY KEY,
  fullName VARCHAR(255),
  password VARCHAR(255),
  address VARCHAR(255),
  email VARCHAR(255),
  phone VARCHAR(255),
  roleID VARCHAR(255)
);

-- Create the products table
CREATE TABLE products (
  id INT IDENTITY(1,1) PRIMARY KEY,
  name VARCHAR(255),
  price DECIMAL(10, 2),
  description TEXT,
  image VARCHAR(255),
  quantity INT,
  createDate DATE
);

-- Create the category table
CREATE TABLE category (
  id INT PRIMARY KEY IDENTITY(1,1),
  name VARCHAR(255) NOT NULL
);

-- Add the categoryId column to the products table
ALTER TABLE products
ADD categoryId INT;

-- Add foreign key constraint to establish the relationship
ALTER TABLE products
ADD CONSTRAINT FK_categoryId
    FOREIGN KEY (categoryId)
    REFERENCES category(id);

-- Create the orders table
CREATE TABLE orders (
  id VARCHAR(255) PRIMARY KEY,
  customerId INT,
  shippingAddress VARCHAR(255),
  orderDate DATE,
  totalPrice DECIMAL(10, 2),
  phone VARCHAR(255),
  FOREIGN KEY (customerId) REFERENCES customers(userId)
);

-- Create the orderDetails table
CREATE TABLE orderDetails (
  id INT IDENTITY(1,1) PRIMARY KEY,
  orderId VARCHAR(255),
  productId INT,
  price DECIMAL(10, 2),
  quantity INT,
  FOREIGN KEY (orderId) REFERENCES orders(id),
  FOREIGN KEY (productId) REFERENCES products(id)
);

CREATE TABLE carts (
  cartId INT IDENTITY(1,1) PRIMARY KEY,
  customerId INT,
  FOREIGN KEY (customerId) REFERENCES customers(userId)
);

CREATE TABLE cartItems (
  cartItemId INT IDENTITY(1,1) PRIMARY KEY,
  cartId INT,
  productId INT,
  quantity INT,
  FOREIGN KEY (cartId) REFERENCES carts(cartId),
  FOREIGN KEY (productId) REFERENCES products(id)
);

-- Insert sample data into the customers table
INSERT INTO customers (fullName, password, address, email, phone, roleId)
VALUES
  ('Toi_la_admin', '1', 'Bien Hoa', 'admin123@gmail.com', '0123456789', 'AD'),
  ('Hoadnt', '1', 'Ho Chi Minh', 'hoa13@gmail.com', '0122345678', 'AD'),
  ('HoaiPhuong', '1', 'Dong Nai', 'hoaiphuong13@gmail.com', '0998877665', 'US'),
  ('NgocNgan', '1', 'Soc Trang', 'ngocngan11@gmail.com', '0112233445', 'US'),
  ('ThuUyen', '1', 'Bien Hoa', 'thuyen1@gmail.com', '0224466889', 'US'),
  ('PhiYen', '1', 'Ha Noi', 'yen123@gmail.com', '0113355779', 'US'),
  ('BaoTran', '1', 'Long An', 'tran89@gmail.com', '0334455667', 'US'),
  ('TienDung', '1', 'Binh Thuan', 'dung15@gmail.com', '0887766554', 'US'),
  ('ThuyLinh', '1', 'Lam Dong', 'thuylinh19@gmail.com', '011998822773', 'US');

-- Insert sample data into the products table
INSERT INTO products (name, price, description, image, quantity, createDate)
VALUES
  ('LEVENTS Floral/White T-Shirt', 99.99, 'Collection Item of Levents.', 'levents.jpg', 100, '2023-01-01'),
  ('SICKKO Hand Tee', 199.99, 'New Collection of Sickko Brands 2023.', 'sickko.jpg', 15, '2023-02-05'),
  ('DirtyCoins Retro T-Shirt', 199.99, 'Next-generation DirtyCoins Tee.', 'dirtycoins.jpg', 50, '2023-12-01'),
  ('Galaxy Tee # Black', 159.99, 'High-performance galaxy items.', '8yo.jpg', 8, '2023-03-15'),
  ('Hearts Tee', 209.99, 'SWE visuals.', 'swe.jpg', 12, '2023-02-20'),
  ('FPT University x Coolmate', 299.99, 'Hot Items colab with Skillcetera.', 'coolmate.jpg', 200, '2023-02-04'),
  ('Navy Solid Tee', 109.99, 'Hot newbie Local Brand.', 'sly.jpg', 60, '2023-10-05'),
  ('Classic Logo T-Shirts 2023 Edition', 169.99, 'Freakers hot tee.', 'freakers.jpg', 180, '2023-06-10'),
  ('Leng Logo T-shirt Blue', 199.99, 'Unique Local brand.', 'leng.jpg', 100, '2023-01-15'),
  ('Local Brand TSUN Signature', 399.99, 'Favourite Hot Items.', 'tsun.jpg', 300, '2023-07-01'),
  ('Godsun Donut Tee', 499.99, 'Donut Items.', 'godson.jpg', 70, '2023-08-12'),
  ('SCC Daily Sneaker', 299.99, 'High performance with Sneaker.', 'sneakero.jpg', 90, '2023-09-18'),
  ('ClownZ Big Logo T-Shirt', 149.99, 'Premium collection.', 'clownz.jpg', 150, '2023-03-25'),
  ('Love Peace', 349.99, 'Bobui Brand for Peace.', 'bobui.jpg', 250, '2023-01-30'),
  ('Degrey Madmnonks 84 Sport T-Shirt', 599.99, 'Hot items for boys.', 'degrey.jpg', 100, '2023-07-05'),
  ('5theway VietNam', 199.99, 'Find The Way Items.', '5theway.jpg', 150, '2023-01-10'),
  ('Gori Clothes', 99.99, 'Just your style.', 'gori.jpg', 200, '2023-02-15'),
  ('TOBI 280gsm Regular Boxy T-shirt', 499.99, 'Compact and lightweight.', 'tobi.jpg', 80, '2023-03-20'),
  ('Goblin Signature Suede Tee Grey', 249.99, 'Urban Monkey Tee.', 'monkey.jpg', 100, '2023-04-25'),
  ('GENTO - SB19 Essential T-Shirt', 149.99, 'Premium Collection.', 'gento.jpg', 150, '2023-05-01');

UPDATE products
SET image = CONCAT('./imgs/', image);