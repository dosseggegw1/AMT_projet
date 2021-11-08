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
-- Structure de la table `Article`
--

CREATE TABLE `Article` (
  `idArticle` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `price` decimal(10,2) UNSIGNED DEFAULT NULL,
  `imageURL` varchar(255) DEFAULT 'default.jpg',
  `stock` smallint(6) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `Article`
--

INSERT INTO `Article` (`idArticle`, `name`, `description`, `price`, `imageURL`, `stock`) VALUES
(1, 'T-Shirt', 'T-shirt avec le logo du club. Taille unique.', '15.50', 'default.jpg', 15),
(2, 'Jogging noire', 'Jogging de couleur noir avec le logo du club. Taille unique', '20.00', 'jogging.jpg', 15),
(3, 'Porte-clés', 'Porte-clés en forme du logo du club. Dimension : 10 mm x 12 mm x 5 mm', '5.15', 'keychain.jpg', 5);

-- --------------------------------------------------------

--
-- Structure de la table `Article_Cart`
--

CREATE TABLE `Article_Cart` (
  `fk_idArticle` int(11) DEFAULT NULL,
  `fk_idCart` int(11) DEFAULT NULL,
  `quantity` smallint(5) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `Article_Cart`
--

INSERT INTO `Article_Cart` (`fk_idArticle`, `fk_idCart`, `quantity`) VALUES
(5, 1, 2),
(2, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `Article_Category`
--

CREATE TABLE `Article_Category` (
  `fk_idArticle` int(11) DEFAULT '0',
  `fk_idCategory` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `Article_Category`
--

INSERT INTO `Article_Category` (`fk_idArticle`, `fk_idCategory`) VALUES
(1, 1),
(2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `Cart`
--

CREATE TABLE `Cart` (
  `idCart` int(11) NOT NULL
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
  `idCategory` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `Category`
--

INSERT INTO `Category` (`idCategory`, `name`) VALUES
(2, 'Accessoire'),
(1, 'Vêtement');

-- --------------------------------------------------------

--
-- Structure de la table `User`
--

CREATE TABLE `User` (
  `idUser` int(11) NOT NULL,
  `fk_cart` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `User`
--

INSERT INTO `User` (`idUser`, `fk_cart`) VALUES
(1, 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Article`
--
ALTER TABLE `Article`
  ADD PRIMARY KEY (`idArticle`),
  ADD UNIQUE KEY `name` (`name`);

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
  ADD KEY `fk_idArticle` (`fk_idArticle`,`fk_idCategory`),
  ADD KEY `fk_idCategory` (`fk_idCategory`);

--
-- Index pour la table `Cart`
--
ALTER TABLE `Cart`
  ADD PRIMARY KEY (`idCart`);

--
-- Index pour la table `Category`
--
ALTER TABLE `Category`
  ADD PRIMARY KEY (`idCategory`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Index pour la table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`idUser`),
  ADD KEY `fk_cart` (`fk_cart`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Article`
--
ALTER TABLE `Article`
  MODIFY `idArticle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `Cart`
--
ALTER TABLE `Cart`
  MODIFY `idCart` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `Category`
--
ALTER TABLE `Category`
  MODIFY `idCategory` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `User`
--
ALTER TABLE `User`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
