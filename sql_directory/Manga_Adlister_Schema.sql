-- Drop the database if it exists
DROP DATABASE IF EXISTS manga_adlister;

-- Recreate and use the database
CREATE DATABASE manga_adlister;

USE manga_adlister;


-- Generate Required Tables
CREATE TABLE users(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email TEXT NOT NULL,
    password TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    created DATETIME,
    roles TEXT
);

CREATE TABLE genre(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    genre TEXT NOT NULL
);

CREATE TABLE listing(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title Text NOT NULL,
    image TEXT NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL NOT NULL,
    users_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (users_id) REFERENCES users(id)
);

CREATE TABLE assoc_genre_listing(
    genre_id INT UNSIGNED NOT NULL,
    listing_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (genre_id) REFERENCES genre(id),
    FOREIGN KEY (listing_id) REFERENCES listing(id)
);

CREATE TABLE cart(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    users_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (users_id) REFERENCES users(id)
);

CREATE TABLE cart_item(
    users_id INT UNSIGNED NOT NULL,
    listing_id INT UNSIGNED NOT NULL,
    cart_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (users_id) REFERENCES users(id),
    FOREIGN KEY (listing_id) REFERENCES listing(id),
    FOREIGN KEY (cart_id) REFERENCES cart(id)
);

CREATE TABLE messages(
    from_id INT UNSIGNED NOT NULL,
    to_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (from_id) REFERENCES users(id),
    FOREIGN KEY (to_id) REFERENCES users(id)
);

CREATE TABLE recovery(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    code TEXT,
    userid INT UNSIGNED NOT NULL,
    FOREIGN KEY (userid) REFERENCES users(id)
)

 