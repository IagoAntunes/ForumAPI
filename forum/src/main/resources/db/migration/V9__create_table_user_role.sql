CREATE TABLE user_role(
    id BIGSERIAL NOT NULL,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES usuario(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);