-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : sam. 06 nov. 2021 à 17:17
-- Version du serveur : 5.6.48
-- Version de PHP : 7.4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `AMT_SQL`
--
DROP SCHEMA IF EXISTS AMT_SQL;
CREATE DATABASE IF NOT EXISTS `AMT_SQL` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `AMT_SQL`;
-- --------------------------------------------------------

--
-- Structure de la table `ArticleController`
--

CREATE TABLE `Article` (
  `idArticle` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL UNIQUE,
  `description` text NOT NULL,
  `price` decimal(10,2) UNSIGNED DEFAULT NULL,
  `imageURL` varchar(255) DEFAULT 'default.jpg',
  `stock` smallint(6) UNSIGNED DEFAULT NULL,
  primary key (`idArticle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `ArticleController`
--

INSERT INTO `Article` (`idArticle`, `name`, `description`, `price`, `imageURL`, `stock`) VALUES
(1, 'T-Shirt', 'T-shirt avec le logo du club. Taille unique.', '15.50', 'default.jpg', 15),
(2, 'Jogging noir', 'Jogging de couleur noire avec le logo du club. Taille unique', '20.00', 'jogging.jpg', 15),
(3, 'Porte-clés', 'Porte-clés en forme du logo du club. Dimension : 10 mm x 12 mm x 5 mm', '5.15', 'keychain.jpg', 5);

-- --------------------------------------------------------

--
-- Structure de la table `Article_Cart`
--

CREATE TABLE `Article_Cart` (
  `idArticleCart` int(11) NOT NULL AUTO_INCREMENT,
  `article` int(11) DEFAULT NULL AUTO_INCREMENT,
  `cart` int(11) DEFAULT NULL,
  `quantity` smallint(5) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `Article_Cart`
--

INSERT INTO `Article_Cart` (`idArticleCart`, `article`, `cart`, `quantity`) VALUES
(1, 5, 1, 2),
(2, 2, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `Article_Category`
--

CREATE TABLE `Article_Category` (
  `idArticleCategory` int(11) NOT NULL AUTO_INCREMENT,
  `idArticle` int(11) DEFAULT '0',
  `idCategory` int(11) DEFAULT '0',
  primary key (`idArticleCategory`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `Article_Category`
--

INSERT INTO `Article_Category` (`idArticle`, `idCategory`) VALUES
(1, 1),
(2, 1),
(3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `Cart`
--

CREATE TABLE `Cart` (
  `idCart` int(11) NOT NULL AUTO_INCREMENT,
  primary key (`idCart`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `Cart`
--

INSERT INTO `Cart` (`idCart`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `Category`
--

CREATE TABLE `Category` (
  `idCategory` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL UNIQUE,
  primary key (`idCategory`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `Category`
--

INSERT INTO `Category` (`idCategory`, `name`) VALUES
(2, 'Accessoires'),
(1, 'Vêtements');

-- --------------------------------------------------------

--
-- Structure de la table `User`
--

CREATE TABLE `User` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `fk_cart` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `User`
--

INSERT INTO `User` (`idUser`, `fk_cart`) VALUES
(1, 1);

--
-- Index pour la table `Article_Cart`
--
ALTER TABLE `Article_Cart`
  ADD KEY `fk_idArticle` (`fk_idArticle`,`fk_idCart`),
  ADD KEY `fk_idCart` (`fk_idCart`);

--
-- Index pour la table `Article_Category`
--
ALTER TABLE `Article_Category`
  ADD FOREIGN KEY (`idCategory`)
      REFERENCES Category (`idCategory`);

ALTER TABLE `Article_Category`
  ADD FOREIGN KEY (`idArticle`)
      REFERENCES Article (`idArticle`);

--
-- Index pour la table `User`
--
ALTER TABLE `User`
  ADD KEY `fk_cart` (`fk_cart`);

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
