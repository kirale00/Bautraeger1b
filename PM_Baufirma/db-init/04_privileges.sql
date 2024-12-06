CREATE USER 'java'@'%' IDENTIFIED BY 'supersecure';
GRANT SELECT, INSERT, UPDATE, DELETE ON SonderwunschVerwaltung.Kunde TO 'java'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE ON SonderwunschVerwaltung.Kundenwunsch TO 'java'@'%';
GRANT SELECT ON SonderwunschVerwaltung.Sonderwunsch TO 'java'@'%';
FLUSH PRIVILEGES;