-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 12 déc. 2020 à 11:50
-- Version du serveur :  10.4.14-MariaDB
-- Version de PHP : 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `taquin`
--

-- --------------------------------------------------------

--
-- Structure de la table `ajouer`
--

CREATE TABLE `ajouer` (
  `idJ` int(10) NOT NULL,
  `idP` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `ajouer`
--

INSERT INTO `ajouer` (`idJ`, `idP`) VALUES
(1, 1),
(1, 2),
(1, 3);

-- --------------------------------------------------------

--
-- Structure de la table `joueur`
--

CREATE TABLE `joueur` (
  `id` int(10) NOT NULL,
  `pseudo` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `joueur`
--

INSERT INTO `joueur` (`id`, `pseudo`) VALUES
(1, 'valentin'),
(2, 'manon'),
(3, 'nathan');

-- --------------------------------------------------------

--
-- Structure de la table `partie`
--

CREATE TABLE `partie` (
  `id` int(10) NOT NULL,
  `score` int(10) DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `partie`
--

INSERT INTO `partie` (`id`, `score`, `date`) VALUES
(1, 10, '2020-12-10'),
(2, 100, '2020-12-11'),
(3, 1000, '2020-12-12');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `ajouer`
--
ALTER TABLE `ajouer`
  ADD PRIMARY KEY (`idJ`,`idP`),
  ADD KEY `idP` (`idP`);

--
-- Index pour la table `joueur`
--
ALTER TABLE `joueur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `partie`
--
ALTER TABLE `partie`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `joueur`
--
ALTER TABLE `joueur`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `partie`
--
ALTER TABLE `partie`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `ajouer`
--
ALTER TABLE `ajouer`
  ADD CONSTRAINT `ajouer_ibfk_1` FOREIGN KEY (`idJ`) REFERENCES `joueur` (`id`),
  ADD CONSTRAINT `ajouer_ibfk_2` FOREIGN KEY (`idP`) REFERENCES `partie` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
