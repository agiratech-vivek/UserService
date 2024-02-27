CREATE TABLE address
(
    id              BINARY(16)   NOT NULL,
    created_at      datetime     NULL,
    updated_at      datetime     NULL,
    deleted         BIT(1)       NOT NULL,
    house_number    INT          NOT NULL,
    locality        VARCHAR(255) NULL,
    city            VARCHAR(255) NULL,
    geo_location_id BINARY(16)   NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE cart
(
    id         BINARY(16) NOT NULL,
    created_at datetime   NULL,
    updated_at datetime   NULL,
    deleted    BIT(1)     NOT NULL,
    user_id    BINARY(16) NULL,
    CONSTRAINT pk_cart PRIMARY KEY (id)
);

CREATE TABLE cart_product_list
(
    cart_list_id    BINARY(16) NOT NULL,
    product_list_id BINARY(16) NOT NULL
);

CREATE TABLE category
(
    id         BINARY(16)   NOT NULL,
    created_at datetime     NULL,
    updated_at datetime     NULL,
    deleted    BIT(1)       NOT NULL,
    name       VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE geo_location
(
    id         BINARY(16)   NOT NULL,
    created_at datetime     NULL,
    updated_at datetime     NULL,
    deleted    BIT(1)       NOT NULL,
    latitude   VARCHAR(255) NULL,
    longitude  VARCHAR(255) NULL,
    CONSTRAINT pk_geolocation PRIMARY KEY (id)
);

CREATE TABLE name
(
    id         BINARY(16)   NOT NULL,
    created_at datetime     NULL,
    updated_at datetime     NULL,
    deleted    BIT(1)       NOT NULL,
    first_name VARCHAR(255) NULL,
    last_name  VARCHAR(255) NULL,
    CONSTRAINT pk_name PRIMARY KEY (id)
);

CREATE TABLE `order`
(
    id          BINARY(16) NOT NULL,
    created_at  datetime   NULL,
    updated_at  datetime   NULL,
    deleted     BIT(1)     NOT NULL,
    user_id     BINARY(16) NULL,
    cart_id     BINARY(16) NULL,
    total_price DOUBLE     NOT NULL,
    CONSTRAINT pk_order PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            BINARY(16)   NOT NULL,
    created_at    datetime     NULL,
    updated_at    datetime     NULL,
    deleted       BIT(1)       NOT NULL,
    title         VARCHAR(255) NULL,
    category_id   BINARY(16)   NULL,
    price         DOUBLE       NOT NULL,
    `description` VARCHAR(255) NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    id         BINARY(16)   NOT NULL,
    created_at datetime     NULL,
    updated_at datetime     NULL,
    deleted    BIT(1)       NOT NULL,
    name       VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE role_user_list
(
    role_list_id BINARY(16) NOT NULL,
    user_list_id BINARY(16) NOT NULL
);

CREATE TABLE user
(
    id         BINARY(16)   NOT NULL,
    created_at datetime     NULL,
    updated_at datetime     NULL,
    deleted    BIT(1)       NOT NULL,
    name_id    BINARY(16)   NULL,
    address_id BINARY(16)   NULL,
    contact    VARCHAR(255) NULL,
    email      VARCHAR(255) NULL,
    password   VARCHAR(255) NULL,
    cart_id    BINARY(16)   NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE address
    ADD CONSTRAINT FK_ADDRESS_ON_GEO_LOCATION FOREIGN KEY (geo_location_id) REFERENCES geo_location (id);

ALTER TABLE cart
    ADD CONSTRAINT FK_CART_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE `order`
    ADD CONSTRAINT FK_ORDER_ON_CART FOREIGN KEY (cart_id) REFERENCES cart (id);

ALTER TABLE `order`
    ADD CONSTRAINT FK_ORDER_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_CART FOREIGN KEY (cart_id) REFERENCES cart (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_NAME FOREIGN KEY (name_id) REFERENCES name (id);

ALTER TABLE cart_product_list
    ADD CONSTRAINT fk_carprolis_on_cart FOREIGN KEY (cart_list_id) REFERENCES cart (id);

ALTER TABLE cart_product_list
    ADD CONSTRAINT fk_carprolis_on_product FOREIGN KEY (product_list_id) REFERENCES product (id);

ALTER TABLE role_user_list
    ADD CONSTRAINT fk_roluselis_on_role FOREIGN KEY (role_list_id) REFERENCES `role` (id);

ALTER TABLE role_user_list
    ADD CONSTRAINT fk_roluselis_on_user FOREIGN KEY (user_list_id) REFERENCES user (id);