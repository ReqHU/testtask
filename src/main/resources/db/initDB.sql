CREATE SEQUENCE global_seq AS INTEGER START WITH 1;

CREATE TABLE users
(
    id      INTEGER         GENERATED BY DEFAULT AS SEQUENCE global_seq,
    name    VARCHAR(255)    NOT NULL, -- Врач/медсестра/и т.д.

    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE rooms
(
    id      INTEGER         GENERATED BY DEFAULT AS SEQUENCE global_seq,
    type    VARCHAR(255)    NOT NULL,

    PRIMARY KEY (id),
    UNIQUE (type)
);

CREATE TABLE bookings
(
    id              INTEGER         GENERATED BY DEFAULT AS SEQUENCE global_seq,
    name            VARCHAR(255)    NOT NULL,
    description     VARCHAR(255)    ,
    start_date_time TIMESTAMP       NOT NULL,
    end_date_time   TIMESTAMP       NOT NULL,
    user_id         INTEGER         NOT NULL,
    room_id         INTEGER         NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES rooms (id) ON DELETE CASCADE
);

INSERT INTO users (name) VALUES
('Врач'), -- 1
('Медсестра'); -- 2

INSERT INTO rooms (type) VALUES
('Тип помещения 1'), -- 3
('Тип помещения 2'); -- 4