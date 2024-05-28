CREATE TABLE users
(
    id       LONG         NOT NULL AUTO_INCREMENT,
    email    VARCHAR(45)  NOT NULL,
    username VARCHAR(45)  NOT NULL,
    password VARCHAR(130) NOT NULL,
    enabled  BOOLEAN DEFAULT NULL,
    roles    VARCHAR      NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
)