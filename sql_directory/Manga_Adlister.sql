DROP DATABASE IF EXISTS manga_adlister;
CREATE DATABASE manga_adlister;

USE manga_adlister;
CREATE TABLE users(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email TEXT NOT NULL,
    password TEXT NOT NULL,
    created DATETIME
);

CREATE TABLE genre(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    genre TEXT Not NULL
);

CREATE TABLE listing(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title Text NOT NULL,
    image TEXT NOT NULL,
    description TEXT NOT NULL,
    price Decimal NOT NULL,
    users_id int unsigned not null,
    Foreign key (users_id) references users(id)
);

CREATE TABLE assoc_genre_listing(
    genre_id INT UNSIGNED NOT NULL,
    listing_id INT UNSIGNED NOT NULL,
    Foreign key (genre_id) references genre(id),
    Foreign key (listing_id) references listing(id)
);

CREATE TABLE cart(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    users_id int UNSIGNED not null,
    Foreign key (users_id) references users(id)
);

 CREATE TABLE cart_item(
    users_id INT UNSIGNED NOT NULL,
    listing_id INT UNSIGNED NOT NULL,
    cart_id int unsigned not null,
    Foreign key (users_id) references users(id),
    Foreign key (listing_id) references listing(id),
    Foreign key (cart_id) references cart(id)
);

 CREATE TABLE messages(
    from_id Int UNSIGNED Not Null,
    to_id int UNSIGNED Not null,
    Foreign key (from_id) references users(id),
    Foreign key (to_id) references users(id)
);

 