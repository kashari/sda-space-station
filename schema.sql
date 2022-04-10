CREATE DATABASE IF NOT EXISTS `space_station_project`;
USE `space_station_project`;
DROP TABLE IF EXISTS `satelite`;
CREATE TABLE `satelite`(
`satelite_id` int NOT NULL PRIMARY KEY,
`name`VARCHAR(55)
);
DROP TABLE IF EXISTS `astronaut`;
CREATE TABLE `astronaut` (
`astronaut_id` int NOT NULL PRIMARY KEY,
`astronaut_name` VARCHAR (100) NOT NULL,
`craft` VARCHAR(50) NOT NULL
);
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report`(
`report_id` int NOT NULL PRIMARY KEY,
`timestamp` LONG,
`longitude` VARCHAR(15),
`latitude` VARCHAR(15)
);
