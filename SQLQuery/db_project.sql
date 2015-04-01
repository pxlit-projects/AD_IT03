-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Machine: localhost
-- Genereertijd: 01 apr 2015 om 11:24
-- Serverversie: 5.5.41-0ubuntu0.14.04.1
-- PHP-versie: 5.5.9-1ubuntu4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Databank: `db_project`
--
CREATE DATABASE IF NOT EXISTS `db_project` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `db_project`;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `answer`
--

DROP TABLE IF EXISTS `answer`;
CREATE TABLE IF NOT EXISTS `answer` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(256) COLLATE utf8_bin NOT NULL,
  `number` int(11) NOT NULL,
  `choice` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=6 ;

--
-- Gegevens worden uitgevoerd voor tabel `answer`
--

INSERT INTO `answer` (`id`, `title`, `number`, `choice`) VALUES
(1, 'Verloopt naar wens', 1, 0),
(2, 'Probleem niet hinderlijk', 1, 0),
(3, 'Probleem hinderlijk voor cliënt.', 3, 0),
(4, 'Probleem hinderlijk voor mantelzorger.', 4, 0),
(5, 'Probleem hinderlijk voor beide.', 5, 0);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `answerlist`
--

DROP TABLE IF EXISTS `answerlist`;
CREATE TABLE IF NOT EXISTS `answerlist` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `list` int(10) unsigned NOT NULL,
  `answer` int(11) unsigned NOT NULL,
  `question` int(11) unsigned NOT NULL,
  `workpoint` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `client` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `answer` (`answer`,`workpoint`,`user`),
  KEY `question` (`question`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=15 ;

--
-- Gegevens worden uitgevoerd voor tabel `answerlist`
--

INSERT INTO `answerlist` (`id`, `list`, `answer`, `question`, `workpoint`, `user`, `client`) VALUES
(1, 1, 1, 1, 0, 3, 4),
(2, 1, 1, 2, 0, 3, 4),
(3, 1, 1, 3, 0, 3, 4),
(4, 1, 1, 4, 0, 3, 4),
(5, 1, 1, 5, 0, 3, 4),
(6, 1, 2, 6, 1, 3, 4),
(7, 1, 3, 7, 1, 3, 4),
(8, 1, 1, 1, 0, 4, 4),
(9, 1, 1, 2, 0, 4, 4),
(10, 1, 1, 3, 0, 4, 4),
(11, 1, 3, 4, 1, 4, 4),
(12, 1, 1, 5, 1, 4, 4),
(13, 1, 2, 6, 1, 4, 4),
(14, 1, 1, 7, 0, 4, 4);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `question`
--

DROP TABLE IF EXISTS `question`;
CREATE TABLE IF NOT EXISTS `question` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(512) COLLATE utf8_bin NOT NULL,
  `description` varchar(512) COLLATE utf8_bin NOT NULL,
  `theme` int(11) unsigned NOT NULL,
  `choice` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_question_theme_theme_id` (`theme`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=64 ;

--
-- Gegevens worden uitgevoerd voor tabel `question`
--

INSERT INTO `question` (`id`, `title`, `description`, `theme`, `choice`) VALUES
(1, 'Iets nieuw leren.', 'Het leren omgaan met bijv. een nieuwe GSM, vaatwasmachine of afstandsbediening. Een nieuwe hobby leren.', 1, 0),
(2, 'Zich kunnen concentreren zonder afgeleid te worden.', 'Het goed kunnen volgen van een gesprek in een drukke omgeving of het volgen van een tv-programma.', 1, 0),
(3, 'Nadenken en psychologie.', 'Goed kunnen fantaseren, een mening vormen, met ideeën spelen, nadenken.', 1, 0),
(4, 'Lezen', 'boeken lezen, instructies, kranten. Text of braille', 1, 0),
(5, 'Rekenen', 'zoals gepast betalen in een winkel.\r\nrekemsommen kunnen maken.', 1, 0),
(6, 'Oplossen van problemen.', 'zoals een afspraak bij de dokter\r\nverzetten, of weten wat je moet\r\ndoen als er iets stuk gaat in huis.', 1, 0),
(7, 'Keuzes maken', 'zoals kiezen wat je wil eten, welk TV\r\nprogramma je wil zien.', 1, 0),
(8, 'Uitvoeren van dagdagelijkse taken en routinehandelingen.', 'zoals zich wassen, ontbijten...', 2, 0),
(9, 'Ondernemen van een eenvoudige taak op eigen initiatief.', 'Een boodschappenlijstje opmaken, de vuilzak buitenzetten, de tafel dekken', 2, 0),
(10, 'Ondernemen van complexe taken op eigen initiatief.', 'autorijden, boodschappen doen,\r\nuitgebreid koken.', 2, 0),
(11, 'Omgaan met stressvolle situaties.', 'zoals het autorijden in druk verkeer of het verzorgen van meerdere kinderen.', 2, 0),
(13, 'Begrijpen wat iemand vertelt of vraagt.', '', 3, 0),
(14, 'Begrijpen van non - verbale (niet gesproken) boodschappen.', 'pictogrammen, afbeeldingen, symbolen, lichaamstaal en gezichtsuitdrukkingen.', 3, 0),
(15, 'Begrijpen van geschreven boodschappen.', 'Het lezen van de krant.', 3, 0),
(16, 'Spreken', '', 3, 0),
(17, 'Zich uiten met lichaamstaal, handgebaren en gezichtsuitdrukkingen.', '', 3, 0),
(18, 'Schrijven van berichten.', 'Een boodschappenlijstje maken, een e-mail schrijven.', 3, 0),
(19, 'Het voeren van een gesprek.', '', 3, 0),
(20, 'Gebruiken van communicatieapparatuur en - technieken', 'gebruik van telefoon, GSM, computer, hoorapparaat, etc', 3, 0),
(27, 'Zich kunnen bewegen en verplaatsen, met of zonder gebruik van hulpmiddelen zoals rolstoel, wandelstok of rollator.', 'uit bed komen, wandelen, opstaan uit stoel.', 4, 0),
(28, 'Gebruiken van handen en armen.', 'Grote bewegingen zoals voorwerpen optillen en meenemen.', 4, 0),
(29, 'Nauwkeurig gebruik van handen.', 'kleine bewegingen zoals grijpen en loslaten, schrijven, gebruik van sleutels of GSM, iets snijden met een mes.', 4, 0),
(30, 'Gebruik van openbaar vervoer.', 'De trein of de bus nemen.', 4, 0),
(31, 'Nauwkeurig gebruik van handen.', 'kleine bewegingen zoals grijpen en loslaten, schrijven, gebruik van sleutels of GSM, iets snijden met een mes.', 4, 0),
(32, 'Gebruik van openbaar vervoer.', 'De trein of de bus nemen.', 4, 0),
(33, 'Besturen van vervoermiddel.', 'bijvoorbeeld de auto of een fiets.', 4, 0),
(34, 'Zich wassen', '', 5, 0),
(35, 'Verzorgen van lichaamsdelen', 'tanden poetsen, nagels knippen, make up gebruiken.', 5, 0),
(36, 'Zelfstandig naar het toilet kunnen gaan.', '', 5, 0),
(37, 'Zich aankleden', '', 5, 0),
(38, 'Eten', '', 5, 0),
(39, 'Drinken', '', 5, 0),
(40, 'Letten op de gezondheid.', 'gevarieerd eten, voldoende lichaamsbeweging, gezondheidsrisico''s vermijden.', 5, 0),
(41, 'Gaan winkelen.', '', 6, 0),
(42, 'Bereiden van maaltijden.', '', 6, 0),
(43, 'Huishouden doen.', 'Onderhoud van huis en tuin, schoonmaken, kleren wassen...', 6, 0),
(44, 'Op sociaal gepaste wijze contact maken met bekende en onbekende mensen.', 'Respectvol, rekening houden met de situatie, etc.', 7, 0),
(45, 'Intieme relaties en seksualiteit.', '', 7, 0),
(46, 'Omgaan met familieleden.', '', 7, 0),
(47, 'Omgaan met vrienden en kennissen.', '', 7, 0),
(48, 'Formele relaties', 'zoals omgang met de collega''s, werkgever, dokter, en verzorgenden.', 7, 0),
(49, 'Het volgen van een vorming, training en/of opleiding.', '', 8, 0),
(50, 'Werken of andere zinvolle dagbesteding', 'vrijwilligerswerk, het huishouding.', 8, 0),
(51, 'Financiële mogelijkheden voor jezelf en je gezin.', '', 8, 0),
(52, 'Deelnemen aan het maatschappelijk leven.', 'zoals gaan stemmen, een huwelijk of begravenis bijwonen, lid zijn van een vereniging.', 9, 0),
(53, 'Ontspanning en vrije tijd', 'activiteit gericht op amuzement.', 9, 0),
(54, 'Religie en spiriualiteit.', '', 9, 0),
(56, 'Somber, neerslachtig, depressief.', '', 10, 0),
(57, 'Angstgevoelens.', '', 10, 0),
(58, 'Onrealistische verwachtingen.', '', 10, 0),
(59, 'Sneller emotieneel ', 'bijvoorbeeld : huilen', 10, 0),
(60, 'Sneller geïrriteerd en prikkelbaar.', '', 10, 0),
(61, 'Onverschilligheid.', '', 10, 0),
(62, 'Ontremming en problemen met controle van gedrag.', 'het maken van ongepaste opmerkingen, overmatig eten.', 10, 0),
(63, 'Sneller en vaker moe.', '', 10, 0);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `questionlist`
--

DROP TABLE IF EXISTS `questionlist`;
CREATE TABLE IF NOT EXISTS `questionlist` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `list` int(10) unsigned NOT NULL,
  `question` int(11) unsigned NOT NULL,
  `user` int(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `question` (`question`,`user`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=72 ;

--
-- Gegevens worden uitgevoerd voor tabel `questionlist`
--

INSERT INTO `questionlist` (`id`, `list`, `question`, `user`) VALUES
(15, 1, 1, 1),
(16, 1, 2, 1),
(17, 1, 3, 1),
(18, 1, 4, 1),
(19, 1, 5, 1),
(20, 1, 6, 1),
(21, 1, 7, 1),
(22, 1, 8, 1),
(23, 1, 9, 1),
(24, 1, 10, 1),
(25, 1, 11, 1),
(26, 1, 13, 1),
(27, 1, 14, 1),
(28, 1, 15, 1),
(29, 1, 16, 1),
(30, 1, 17, 1),
(31, 1, 18, 1),
(32, 1, 19, 1),
(33, 1, 20, 1),
(34, 1, 27, 1),
(35, 1, 28, 1),
(36, 1, 29, 1),
(37, 1, 30, 1),
(38, 1, 31, 1),
(39, 1, 32, 1),
(40, 1, 33, 1),
(41, 1, 34, 1),
(42, 1, 35, 1),
(43, 1, 36, 1),
(44, 1, 37, 1),
(45, 1, 38, 1),
(46, 1, 39, 1),
(47, 1, 40, 1),
(48, 1, 41, 1),
(49, 1, 42, 1),
(50, 1, 43, 1),
(51, 1, 44, 1),
(52, 1, 45, 1),
(53, 1, 46, 1),
(54, 1, 47, 1),
(55, 1, 48, 1),
(56, 1, 49, 1),
(57, 1, 50, 1),
(58, 1, 51, 1),
(59, 1, 52, 1),
(60, 1, 53, 1),
(61, 1, 54, 1),
(62, 1, 56, 1),
(63, 1, 57, 1),
(64, 1, 57, 1),
(65, 1, 58, 1),
(66, 1, 59, 1),
(67, 1, 60, 1),
(68, 1, 61, 1),
(69, 1, 62, 1),
(70, 1, 63, 1);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `theme`
--

DROP TABLE IF EXISTS `theme`;
CREATE TABLE IF NOT EXISTS `theme` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(2048) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=11 ;

--
-- Gegevens worden uitgevoerd voor tabel `theme`
--

INSERT INTO `theme` (`id`, `title`, `description`) VALUES
(1, 'Leren en toepassen van kennis', 'Dit thema gaat over het leren en toepassen van kennis.'),
(2, 'Algemene taken en activiteiten.', 'uitvoeren van dagdagelijkse taken en omgaan met situaties.'),
(3, 'Communicatie.', 'het omgaan met mensen en communiceren.'),
(4, 'Mobiliteit', 'Verplaatsen en bewegen'),
(5, 'Zelfverzorging.', NULL),
(6, 'Huishouden.', NULL),
(7, 'Omgaan met andere mensen.', ''),
(8, 'Belangrijke levensgebieden', 'opleiding, werk of financiële zaken'),
(9, 'Maatschappelijk, sociaal en burgerlijk leven.', NULL),
(10, 'Emoties en gedrag.', NULL);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `time`
--

DROP TABLE IF EXISTS `time`;
CREATE TABLE IF NOT EXISTS `time` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `datetime` datetime NOT NULL,
  `list` int(11) NOT NULL,
  `time` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=3 ;

--
-- Gegevens worden uitgevoerd voor tabel `time`
--

INSERT INTO `time` (`id`, `datetime`, `list`, `time`, `user`) VALUES
(1, '2015-03-22 07:26:35', 1, 360, 3),
(2, '2015-03-22 13:30:22', 1, 720, 4);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) COLLATE utf8_bin NOT NULL,
  `firstname` varchar(50) COLLATE utf8_bin NOT NULL,
  `lastname` varchar(50) COLLATE utf8_bin NOT NULL,
  `password` varchar(50) COLLATE utf8_bin NOT NULL,
  `email` varchar(100) COLLATE utf8_bin NOT NULL,
  `type` int(10) unsigned NOT NULL,
  `street` varchar(50) COLLATE utf8_bin NOT NULL,
  `town` varchar(50) COLLATE utf8_bin NOT NULL,
  `zipcode` int(4) NOT NULL,
  `birthdate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `type` (`type`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=106 ;

--
-- Gegevens worden uitgevoerd voor tabel `user`
--

INSERT INTO `user` (`id`, `login`, `firstname`, `lastname`, `password`, `email`, `type`, `street`, `town`, `zipcode`, `birthdate`) VALUES
(88, 'e', 'e', 'e', 'e', 'e', 2, 'e', 'e', 11, '2015-08-06'),
(89, 'p', 'Pieter', 'Switten', 'p', 'pieterswitten@gmail.com', 1, 'Grote Hemmenweg', 'Zonhoven', 3520, '1994-06-29'),
(90, 'pp', 'Piet', 'Pienter', 'pp', 'Piet.Pienter@student.pxl.be', 1, 'pp', 'pp', 3000, '1990-03-01'),
(93, 'Dokter', 'Dokter', 'Dokter', 'Dokter', 'Dokter@Dokter.Dokter', 2, 'Dokter', 'Dokter', 1111, '1990-03-22'),
(94, 'Admin', 'Admin', 'Admin', 'Admin', 'Admin@Admin.Admin', 1, 'Admin', 'Admin', 1111, '1990-03-22');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `usertype`
--

DROP TABLE IF EXISTS `usertype`;
CREATE TABLE IF NOT EXISTS `usertype` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `screenname` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=5 ;

--
-- Gegevens worden uitgevoerd voor tabel `usertype`
--

INSERT INTO `usertype` (`id`, `screenname`, `description`) VALUES
(1, 'Administrator', 'Hoofd beheer van service '),
(2, 'Dokter', 'De geneesheer '),
(3, 'Mantelzorger', 'Persoon die de patient verzorgt'),
(4, 'patient', 'persoon die in revalidatie is');

--
-- Beperkingen voor gedumpte tabellen
--

--
-- Beperkingen voor tabel `answerlist`
--
ALTER TABLE `answerlist`
  ADD CONSTRAINT `fk_answerlist_question_question_id` FOREIGN KEY (`question`) REFERENCES `question` (`id`),
  ADD CONSTRAINT `fk_answerlist_answer_answer_id` FOREIGN KEY (`answer`) REFERENCES `answer` (`id`);

--
-- Beperkingen voor tabel `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `fk_question_theme_theme_id` FOREIGN KEY (`theme`) REFERENCES `theme` (`id`);

--
-- Beperkingen voor tabel `questionlist`
--
ALTER TABLE `questionlist`
  ADD CONSTRAINT `fk_questionlist_question_question_id` FOREIGN KEY (`question`) REFERENCES `question` (`id`);

--
-- Beperkingen voor tabel `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_usertypeid_type` FOREIGN KEY (`type`) REFERENCES `usertype` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
