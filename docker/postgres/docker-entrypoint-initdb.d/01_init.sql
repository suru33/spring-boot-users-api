CREATE ROLE test_user PASSWORD 'test_password' LOGIN;
CREATE DATABASE users_db;
CREATE DATABASE users_db_test;
GRANT ALL PRIVILEGES ON DATABASE users_db TO test_user;
GRANT ALL PRIVILEGES ON DATABASE users_db_test TO test_user;