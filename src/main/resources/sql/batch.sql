CREATE TABLE `batch` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LIST_NAME` varchar(255) DEFAULT NULL,
  `ORDER_ID` int(11) DEFAULT NULL,
  `VENTURE_ID` int(11) DEFAULT NULL,
  `batchName` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `batch` (`batchName`),
  CONSTRAINT `batch` FOREIGN KEY (`batchName`) REFERENCES `ventures` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;