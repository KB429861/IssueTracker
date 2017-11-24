CREATE TABLE IF NOT EXISTS issues (
  id          INTEGER        NOT NULL AUTO_INCREMENT PRIMARY KEY,
  summary     NVARCHAR2(255) NOT NULL,
  description NVARCHAR2(4000),
  author      NVARCHAR2(255) NOT NULL,
  start_date  DATE           NOT NULL,
  status      NVARCHAR2(30)  NOT NULL
);
