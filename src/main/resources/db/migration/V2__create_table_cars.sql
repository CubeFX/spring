CREATE TABLE cars
(
    id           LONG        NOT NULL AUTO_INCREMENT,
    model        VARCHAR(80) NOT NULL,
    color        VARCHAR(80) NOT NULL,
    max_speed    VARCHAR(30) NOT NULL,
    engine_type  VARCHAR(30) NOT NULL,
    engine_power VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
)