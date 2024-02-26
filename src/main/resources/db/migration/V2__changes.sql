ALTER TABLE user_role_list
    DROP FOREIGN KEY fk_userollis_on_user;

DROP TABLE hibernate_sequence;

ALTER TABLE user_role_list
    DROP COLUMN user_list_id;