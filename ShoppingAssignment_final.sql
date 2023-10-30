-- Create the database
CREATE DATABASE UserManagement;

-- Use the database
USE UserManagement;

-- Create the customers table
CREATE TABLE customers (
  userId INT IDENTITY(1,1) PRIMARY KEY,
  fullName VARCHAR(255),
  password VARCHAR(255),
  address VARCHAR(255),
  email VARCHAR(255),
  phone VARCHAR(255)
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
INSERT INTO customers (fullName, password, address, email, phone)
VALUES
  ('Toi_la_admin', '1', 'Bien Hoa', 'admin123@gmail.com', '0123456789'),
  ('Hoadnt', '1', 'Ho Chi Minh', 'hoa13@gmail.com', '0122345678'),
  ('HoaiPhuong', '1', 'Dong Nai', 'hoaiphuong13@gmail.com', '0998877665'),
  ('NgocNgan', '1', 'Soc Trang', 'ngocngan11@gmail.com', '0112233445'),
  ('ThuUyen', '1', 'Bien Hoa', 'thuyen1@gmail.com', '0224466889'),
  ('PhiYen', '1', 'Ha Noi', 'yen123@gmail.com', '0113355779'),
  ('BaoTran', '1', 'Long An', 'tran89@gmail.com', '0334455667'),
  ('TienDung', '1', 'Binh Thuan', 'dung15@gmail.com', '0887766554'),
  ('ThuyLinh', '1', 'Lam Dong', 'thuylinh19@gmail.com', '011998822773');


-- Insert sample data into the products table
INSERT INTO products (name, price, description, image, quantity, createDate)
VALUES
  ('iPhone 12', 999.99, 'New Iphone.', 'iphone12.jpg', 10, '2022-01-13'),
  ('Samsung Galaxy S21', 990.99, 'New Powerful Android smartphone.', 'galaxyS21.jpg', 15, '2022-02-23'),
  ('Sony PlayStation 5', 399.99, 'Next-generation gaming.', 'ps5.jpg', 5, '2021-12-20'),
  ('Apple MacBook Pro', 1700.99, 'High-performance laptop.', 'macbookPro.jpg', 8, '2022-03-15'),
  ('Samsung 65" 4K Smart TV', 880.99, 'Immersive entertainment experience.', 'samsungTV.jpg', 12, '2022-02-24'),
  ('Bose QuietComfort 35 II', 300.99, 'Wireless noise-canceling headphones.', 'boseHeadphones.jpg', 20, '2022-03-18'),
  ('Canon EOS Rebel T7i', 800.99, 'Entry-level DSLR camera.', 'canonCamera.jpg', 6, '2022-05-19'),
  ('Fitbit Versa 3', 211.99, 'Smartwatch with health and fitness.', 'fitbitVersa.jpg', 18, '2022-09-10'),
  ('Nintendo Switch', 322.99, 'Hybrid gaming console for at-home.', 'nintendoSwitch.jpg', 10, '2021-11-10'),
  ('Dyson V11 Absolute', 599.99, 'Powerful cordless vacuum cleaner.', 'dysonVacuum.jpg', 3, '2022-04-01'),
  ('LG 55" OLED 4K TV', 1399.99, 'Premium TV with deep blacks and vibrant colors.', 'lgOLED.jpg', 7, '2022-09-12'),
  ('Microsoft Surface Pro 7', 809.99, 'Versatile 2-in-1 laptop and tablet.', 'surfacePro.jpg', 9, '2022-10-18'),
  ('Sony WH-1000XM4', 309.99, 'Premium wireless headphones.', 'sonyHeadphones.jpg', 15, '2022-10-29'),
  ('Amazon Echo Dot (4th Gen)', 79.99, 'Smart speaker with Alexa voice assistant.', 'echoDot.jpg', 25, '2022-10-30'),
  ('GoPro HERO9 Black', 389.99, 'Action camera with 5K video.', 'goproCamera.jpg', 10, '2022-12-19'),
  ('Apple Watch Series 6', 309.99, 'Advanced smartwatch .', 'appleWatch.jpg', 15, '2023-01-11'),
  ('Bose SoundLink Revolve', 299.99, 'Portable Bluetooth speaker.', 'boseSpeaker.jpg', 20, '2023-02-16'),
  ('Nintendo Switch Lite', 189.99, 'Compact and lightweight gaming.', 'switchLite.jpg', 8, '2023-03-21'),
  ('Samsung Galaxy Tab S7', 699.99, 'Premium Android tablet.', 'galaxyTab.jpg', 10, '2023-04-20'),
  ('Sony WH-1000XM4', 321.99, 'Premium wireless headphones with exceptional.', 'sonyHeadphones.jpg', 15, '2023-05-10');

UPDATE products
SET image = CONCAT('./imgs/', image);