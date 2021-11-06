-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : sam. 06 nov. 2021 à 16:33
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

-- --------------------------------------------------------

--
-- Structure de la table `Article`
--

CREATE TABLE `Article` (
  `idArticle` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `price` decimal(10,2) UNSIGNED DEFAULT NULL,
  `imageURL` text NOT NULL,
  `stock` smallint(6) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Article_Carte`
--

CREATE TABLE `Article_Carte` (
  `fk_idArticle` int(11) DEFAULT NULL,
  `fk_idCart` int(11) DEFAULT NULL,
  `quantity` smallint(5) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Article_Category`
--

CREATE TABLE `Article_Category` (
  `fk_idArticle` int(11) DEFAULT '0',
  `fk_idCategory` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Cart`
--

CREATE TABLE `Cart` (
  `idCart` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Category`
--

CREATE TABLE `Category` (
  `idCategory` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `User`
--

CREATE TABLE `User` (
  `idUser` int(11) NOT NULL,
  `fk_cart` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- Index pour la table `Article_Carte`
--
ALTER TABLE `Article_Carte`
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
  MODIFY `idArticle` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `Cart`
--
ALTER TABLE `Cart`
  MODIFY `idCart` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `Category`
--
ALTER TABLE `Category`
  MODIFY `idCategory` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `User`
--
ALTER TABLE `User`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Article_Carte`
--
ALTER TABLE `Article_Carte`
  ADD CONSTRAINT `Article_Carte_ibfk_1` FOREIGN KEY (`fk_idArticle`) REFERENCES `Article` (`idArticle`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `Article_Carte_ibfk_2` FOREIGN KEY (`fk_idCart`) REFERENCES `Cart` (`idCart`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Article_Category`
--
ALTER TABLE `Article_Category`
  ADD CONSTRAINT `Article_Category_ibfk_1` FOREIGN KEY (`fk_idArticle`) REFERENCES `Article` (`idArticle`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `Article_Category_ibfk_2` FOREIGN KEY (`fk_idCategory`) REFERENCES `Category` (`idCategory`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Contraintes pour la table `User`
--
ALTER TABLE `User`
  ADD CONSTRAINT `User_ibfk_1` FOREIGN KEY (`fk_cart`) REFERENCES `Cart` (`idCart`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
