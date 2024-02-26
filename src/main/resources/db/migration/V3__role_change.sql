ALTER TABLE role_user_list
    DROP FOREIGN KEY FK4xunm7tpkqjb5ra9bpahkovca;

ALTER TABLE user_role_list
    DROP FOREIGN KEY FK8hyj0c7rglkeyinrekdeul39;

ALTER TABLE user_role_list
    DROP FOREIGN KEY fk_userollis_on_role;

ALTER TABLE role_user_list
    ADD role_list_id BIGINT NULL;

ALTER TABLE role_user_list
    MODIFY role_list_id BIGINT NOT NULL;

ALTER TABLE role_user_list
    ADD CONSTRAINT fk_roluselis_on_role FOREIGN KEY (role_list_id) REFERENCES `role` (id);

DROP TABLE user_role_list;

ALTER TABLE role_user_list
    DROP COLUMN role_id;