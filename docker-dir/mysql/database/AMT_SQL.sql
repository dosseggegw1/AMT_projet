DROP SCHEMA IF EXISTS AMT_SQL;
CREATE SCHEMA AMT_SQL DEFAULT CHARSET = utf8mb4;
USE AMT_SQL;

CREATE TABLE Utilisateur (
	 idUtilisateur INTEGER AUTO_INCREMENT,
     email VARCHAR(50) NOT NULL,
     motDePasse VARCHAR(255) NOT NULL, 
     nom VARCHAR(50) NOT NULL,
     prenom VARCHAR(50) NOT NULL,
     CONSTRAINT PK_Utilisateur PRIMARY KEY (idUtilisateur)
);

CREATE TABLE UtilisateurAdresse (
	 idUtilisateurAdresse INTEGER AUTO_INCREMENT,
     idUtilisateur INTEGER, 
     rue VARCHAR(50) NOT NULL,
     numero INTEGER NOT NULL, 
     npa INTEGER NOT NULL,
     ville VARCHAR(50) NOT NULL,
     pays VARCHAR(50) NOT NULL,
     CONSTRAINT PK_UtilisateurAdresse PRIMARY KEY (idUtilisateurAdresse)
);

CREATE TABLE Article (
     idArticle INTEGER AUTO_INCREMENT,
     nom VARCHAR(50) NOT NULL,
     description VARCHAR(255) NOT NULL,
     prix INTEGER NOT NULL,
     CONSTRAINT PK_Article PRIMARY KEY (idArticle)
);

CREATE TABLE Commande (
     idCommande INTEGER AUTO_INCREMENT,
     CONSTRAINT PK_Commande PRIMARY KEY (idCommande)
);

ALTER TABLE Adresse
	ADD CONSTRAINT FK_Adresse_idUtilisateur
		FOREIGN KEY (idUtilisateur)
		REFERENCES Utilisateur (idUtilisateur)
        ON DELETE CASCADE
        ON UPDATE CASCADE;

/*CREATE TABLE Quantite (
);

CREATE TABLE Paiement (
);

CREATE TABLE Carte (
);

CREATE TABLE Facture (
);

*/




