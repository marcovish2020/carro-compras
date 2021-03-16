DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  email VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  fecha_nacimiento DATE NULL
);

INSERT INTO usuario (email, password, fecha_nacimiento) VALUES
  ('Elon','Musk',null),
  ('Bill', 'Gates', null),
  ('Carlos', 'Slim', null);
