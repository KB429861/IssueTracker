CREATE TABLE IF NOT EXISTS users (
  username NVARCHAR2(255) NOT NULL,
  password NVARCHAR2(255) NOT NULL,
  PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS issues (
  id          INTEGER        NOT NULL AUTO_INCREMENT,
  summary     NVARCHAR2(255) NOT NULL,
  description NVARCHAR2(4000),
  author      NVARCHAR2(255) NOT NULL,
  start_date  SMALLDATETIME  NOT NULL,
  status      NVARCHAR2(30)  NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (author) REFERENCES users (username)
);

CREATE TABLE IF NOT EXISTS comments (
  id       INTEGER        NOT NULL AUTO_INCREMENT,
  issue_id INTEGER        NOT NULL,
  date     SMALLDATETIME  NOT NULL,
  author   NVARCHAR2(255) NOT NULL,
  text     NVARCHAR2(4000),
  PRIMARY KEY (id),
  FOREIGN KEY (issue_id) REFERENCES issues (id),
  FOREIGN KEY (author) REFERENCES users (username)
);
