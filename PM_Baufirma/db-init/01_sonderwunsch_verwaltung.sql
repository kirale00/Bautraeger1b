CREATE DATABASE IF NOT EXISTS SonderwunschVerwaltung;
USE SonderwunschVerwaltung;

CREATE TABLE Haus (
    Hausnummer INT NOT NULL PRIMARY KEY, -- 1 bis 24
    Typ VARCHAR(50) NOT NULL -- vielleicht anderer Typ?
);

-- vielleicht?
CREATE TABLE Kategorie (
    KategorieID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL
);

CREATE TABLE Sonderwunsch (
    SonderwunschID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Beschreibung TEXT NOT NULL,
    Preis DECIMAL(10, 2) NOT NULL, -- Preis unter 100 Millionen
    AnzahlVerfuegbar INT,
    KategorieID INT NOT NULL,
    FOREIGN KEY (KategorieID) REFERENCES Kategorie(KategorieID) -- siehe Kategorie
);

CREATE TABLE Kunde (
    Kundennummer INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Vorname VARCHAR(100) NOT NULL,
    Nachname VARCHAR(100) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Telefonnummer VARCHAR(20) NOT NULL,
    Hausnummer INT NOT NULL,
    FOREIGN KEY (Hausnummer) REFERENCES Haus(Hausnummer)
);

CREATE TABLE Kundenwunsch (
    KundenwunschID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Kundennummer INT NOT NULL,
    SonderwunschID INT NOT NULL,
    Anzahl INT NOT NULL,
    FOREIGN KEY (Kundennummer) REFERENCES Kunde(Kundennummer),
    FOREIGN KEY (SonderwunschID) REFERENCES Sonderwunsch(SonderwunschID)
);
