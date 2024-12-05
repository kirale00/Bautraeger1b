CREATE DATABASE IF NOT EXISTS SonderwunschVerwaltung;
USE SonderwunschVerwaltung;

CREATE TABLE Haus (
    Hausnummer INT NOT NULL PRIMARY KEY, -- 1 bis 24
    HatDachgeschoss BOOLEAN NOT NULL
);

CREATE TABLE Sonderwunsch (
    SonderwunschID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Beschreibung TEXT CHARACTER SET utf8mb4 NOT NULL,
    Preis DECIMAL(10, 2), -- Unter 100 Millionen
    AnzahlVerfuegbar INT DEFAULT NULL
);

CREATE TABLE Kunde (
    Kundennummer INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Vorname VARCHAR(100) NOT NULL,
    Nachname VARCHAR(100) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Telefonnummer VARCHAR(20) NOT NULL,
    Hausnummer INT NOT NULL,
    FOREIGN KEY (Hausnummer) REFERENCES Haus(Hausnummer) ON DELETE CASCADE
);

CREATE TABLE Kundenwunsch (
    Kundennummer INT NOT NULL,
    SonderwunschID INT NOT NULL,
    Anzahl INT NOT NULL CHECK (Anzahl > 0),
    PRIMARY KEY (Kundennummer, SonderwunschID), -- Zusammengesetzter Primärschlüssel
    FOREIGN KEY (Kundennummer) REFERENCES Kunde(Kundennummer) ON DELETE CASCADE,
    FOREIGN KEY (SonderwunschID) REFERENCES Sonderwunsch(SonderwunschID) ON DELETE CASCADE
);
