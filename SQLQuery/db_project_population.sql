--
-- Database: `db_project`
--

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`id`, `title`, `number`, `choice`) VALUES
(1, 'Verloopt naar wens', 1, 0),
(2, 'Probleem niet hinderlijk', 1, 0),
(3, 'Probleem hinderlijk voor cliënt.', 3, 0),
(4, 'Probleem hinderlijk voor mantelzorger.', 4, 0),
(5, 'Probleem hinderlijk voor beide.', 5, 0);

--
-- Dumping data for table `answerlist`
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

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `title`, `description`, `theme`, `choice`) VALUES
(1, 'Iets nieuw leren.', 'Het leren omgaan met bijv. een nieuwe GSM, vaatwasmachine of afstandsbediening. Een nieuwe hobby leren.', 1, 0),
(2, 'Zich kunnen concentreren zonder afgeleid te worden.', 'Het goed kunnen volgen van een gesprek in een drukke omgeving of het volgen van een tv-programma.', 1, 0),
(3, 'Nadenken en psychologie.', 'Goed kunnen fantaseren, een mening vormen, met ideeën spelen, nadenken.', 1, 0),
(4, 'Lezen', 'boeken lezen, instructies, kranten. Text of braille', 1, 0),
(5, 'Rekenen', 'zoals gepast betalen in een winkel.\r\nrekemsommen kunnen maken.', 1, 0),
(6, 'Oplossen van problemen.', 'zoals een afspraak bij de dokter\r\nverzetten, of weten wat je moet\r\ndoen als er iets stuk gaat in huis.', 1, 0),
(7, 'Keuzes maken', 'zoals kiezen wat je wil eten, welk TV\r\nprogramma je wil zien.', 1, 0);

--
-- Dumping data for table `questionlist`
--

INSERT INTO `questionlist` (`id`, `list`, `question`, `user`) VALUES
(15, 1, 1, 1),
(16, 1, 2, 1),
(17, 1, 3, 1),
(18, 1, 4, 1),
(19, 1, 5, 1),
(20, 1, 6, 1),
(21, 1, 7, 1);

--
-- Dumping data for table `theme`
--

INSERT INTO `theme` (`id`, `title`, `description`) VALUES
(1, 'Leren en toepassen van kennis', 'Dit thema gaat over het leren en toepassen van kennis.');

--
-- Dumping data for table `time`
--

INSERT INTO `time` (`id`, `datetime`, `list`, `time`, `user`) VALUES
(1, '2015-03-22 07:26:35', 1, 360, 3),
(2, '2015-03-22 13:30:22', 1, 720, 4);

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `login`, `firstname`, `lastname`, `password`, `email`, `type`) VALUES
(1, 'SvenV', 'Sven', 'Vranken', '7777777', 'sven_v45@hotmail.com', 1),
(2, 'LieveG', 'Lieve', 'Geneesheer', '7777777', 'lieve.geneesheer@gmail.com', 2),
(3, 'eenmantelzorger', 'Lara', 'Verzorgster', '7777777', '777@777.com', 3),
(4, 'eenpatient', 'hersen', 'patiënt', '7777777', 'patient@zeker.com', 4);

--
-- Dumping data for table `usertype`
--

INSERT INTO `usertype` (`id`, `screenname`, `description`) VALUES
(1, 'Administrator', 'Hoofd beheer van service '),
(2, 'Dokter', 'De geneesheer '),
(3, 'Mantelzorger', 'Persoon die de patient verzorgt'),
(4, 'patient', 'persoon die in revalidatie is');
