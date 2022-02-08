SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS restaurants (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  location VARCHAR,
  description VARCHAR
);
