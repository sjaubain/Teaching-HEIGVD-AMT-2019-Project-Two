SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS users;
CREATE SCHEMA users;
USE users;

CREATE TABLE amt_user (
  firstName VARCHAR(16),
  lastName VARCHAR(16),
  username VARCHAR(16),
  password VARCHAR(100),
  email VARCHAR(64) NOT NULL,
  is_admin BOOLEAN,
  is_locked BOOLEAN,
  PRIMARY KEY(email)
);
  
INSERT INTO amt_user (firstName, lastName, username, password, email, is_admin, is_locked)
VALUES ("Pierre", "Lexemple", "plexemple", "$2a$10$TwAvxHMClpMq2cWBvvb49eKogrXmmdgbX9cNaggdOdIEQoAJ/og52",
"test@gmail.com", true, false);
INSERT INTO amt_user (firstName, lastName, username, password, email, is_admin, is_locked)
VALUES ("Simon", "Jobin", "sjaubain", "$2a$10$TwAvxHMClpMq2cWBvvb49eKogrXmmdgbX9cNaggdOdIEQoAJ/og52",
"simon.jobin@bluewin.ch", true, false);
INSERT INTO amt_user (firstName, lastName, username, password, email, is_admin, is_locked)
VALUES ("Pierre", "Dupont", "pdupont", "$2a$10$TwAvxHMClpMq2cWBvvb49eKogrXmmdgbX9cNaggdOdIEQoAJ/og52",
"pierre.dupont@bluewin.ch", false, false);
INSERT INTO amt_user (firstName, lastName, username, password, email, is_admin, is_locked)
VALUES ("Olaf", "Reutype", "olaf", "$2a$10$TwAvxHMClpMq2cWBvvb49eKogrXmmdgbX9cNaggdOdIEQoAJ/og52",
"olaf.reutype@gmail.com", false, false);
INSERT INTO amt_user (firstName, lastName, username, password, email, is_admin, is_locked)
VALUES ("Ivan", "Destrucs", "ives", "$2a$10$TwAvxHMClpMq2cWBvvb49eKogrXmmdgbX9cNaggdOdIEQoAJ/og52",
"ivan.destrucs@gmail.com", false, false);
INSERT INTO amt_user (firstName, lastName, username, password, email, is_admin, is_locked)
VALUES ("Alex", "Terieur", "alex", "$2a$10$TwAvxHMClpMq2cWBvvb49eKogrXmmdgbX9cNaggdOdIEQoAJ/og52",
"alex.terieur@gmail.com", false, false);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;