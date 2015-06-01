CREATE DATABASE IF NOT EXISTS businesscard CHARACTER SET utf8 COLLATE utf8_general_ci;
GRANT ALL privileges ON businesscard.* TO businesscard@localhost;
USE businesscard;
CREATE TABLE users
(
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    mail VARCHAR(50),
    password VARCHAR(50),
    role VARCHAR(50),
    isconfirm INT DEFAULT 0,
    created TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY(id)
);
CREATE TABLE contacts
(
	id INT NOT NULL AUTO_INCREMENT,
    type VARCHAR(50),
    content VARCHAR(50),
    user_id INT NOT NULL,
    PRIMARY KEY(id),
    KEY FK_USER_idx (user_id),
    CONSTRAINT FK_USER FOREIGN KEY (user_id) REFERENCES users(id)
);
CREATE TABLE cards
(
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    url VARCHAR(50),
    json TEXT,
    user_id INT NOT NULL,
	PRIMARY KEY(id),
    KEY FK_USERS_idx (user_id),
    CONSTRAINT FK_USERS2 FOREIGN KEY (user_id) REFERENCES users(id)
);
CREATE TABLE jobs
(
	id INT NOT NULL AUTO_INCREMENT,
    company VARCHAR(50),
    post VARCHAR(50),
    user_id INT NOT NULL,
	PRIMARY KEY(id),
    KEY FK_USERS_idx (user_id),
    CONSTRAINT FK_USERS3 FOREIGN KEY (user_id) REFERENCES users(id)
);