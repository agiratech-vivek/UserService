CREATE TABLE address
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      date                  NULL,
    updated_at      date                  NULL,
    deleted         BIT(1)                NOT NULL,
    house_number    INT                   NOT NULL,
    locality        VARCHAR(255)          NULL,
    city            VARCHAR(255)          NULL,
    geo_location_id BIGINT                NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE geo_location
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at date                  NULL,
    updated_at date                  NULL,
    deleted    BIT(1)                NOT NULL,
    latitude   VARCHAR(255)          NULL,
    longitude  VARCHAR(255)          NULL,
    CONSTRAINT pk_geolocation PRIMARY KEY (id)
);

CREATE TABLE name
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at date                  NULL,
    updated_at date                  NULL,
    deleted    BIT(1)                NOT NULL,
    first_name VARCHAR(255)          NULL,
    last_name  VARCHAR(255)          NULL,
    CONSTRAINT pk_name PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at date                  NULL,
    updated_at date                  NULL,
    deleted    BIT(1)                NOT NULL,
    name       VARCHAR(255)          NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE user
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at date                  NULL,
    updated_at date                  NULL,
    deleted    BIT(1)                NOT NULL,
    name_id    BIGINT                NULL,
    address_id BIGINT                NULL,
    contact    VARCHAR(255)          NULL,
    email      VARCHAR(255)          NULL,
    password   VARCHAR(255)          NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_role_list
(
    role_list_id BIGINT NOT NULL,
    user_list_id BIGINT NOT NULL
);

ALTER TABLE address
    ADD CONSTRAINT FK_ADDRESS_ON_GEO_LOCATION FOREIGN KEY (geo_location_id) REFERENCES geo_location (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_NAME FOREIGN KEY (name_id) REFERENCES name (id);

ALTER TABLE user_role_list
    ADD CONSTRAINT fk_userollis_on_role FOREIGN KEY (role_list_id) REFERENCES `role` (id);

ALTER TABLE user_role_list
    ADD CONSTRAINT fk_userollis_on_user FOREIGN KEY (user_list_id) REFERENCES user (id);