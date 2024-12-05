-- Hier kommen die fundamentalen Sonderwuensche rein
USE SonderwunschVerwaltung;

INSERT INTO Sonderwunsch (Beschreibung, Preis, AnzahlVerfuegbar)
VALUES
    -- 2 Grundriss-Varianten / Einträge: 0-5
    ('Wand zur Abtrennung der Küche von dem Essbereich', 300.00, NULL),
    ('Tür in der Wand zwischen Küche und Essbereich', 300.00, NULL),
    ('Großes Zimmer im OG statt zwei kleinen Zimmern', 0.00, NULL),
    ('Abgetrennter Treppenraum im DG', 890.00, NULL),
    ('Vorrichtung eines Bades im DG', 990.00, NULL),
    ('Ausführung eines Bades im DG', 8990.00, NULL),

    -- 3 Fenster und Außentüren / Einträge: 6-14
    ('Schiebetüren im EG zur Terrasse', 590.00, NULL),
    ('Schiebetüren im DG zur Dachterrasse', 590.00, NULL),
    ('Erhöhter Einbruchschutz an der Haustür', 690.00, NULL),
    ('Vorbereitung für elektrische Antriebe Rolläden EG', 190.00, NULL),
    ('Vorbereitung für elektrische Antriebe Rolläden OG', 190.00, NULL),
    ('Vorbereitung für elektrische Antriebe Rolläden DG', 190.00, NULL),
    ('Elektrische Rolläden EG', 990.00, NULL),
    ('Elektrische Rolläden OG', 990.00, NULL),
    ('Elektrische Rolläden DG', 990.00, NULL),

    -- 4 Innentüren / Einträge: 15-17
    ('Mehrpreis für die Ausführung eines Glasausschnitts (Klarglas) in einer Innentür', 460.00, NULL),
    ('Mehrpreis für die Ausführung eines Glasausschnitts (Milchglas) in einer Innentür', 560.00, NULL),
    ('Innentür zur Garage als Holztür', 660.00, NULL),

    -- 5 Heizungen / Einträge: 18-22
    ('Mehrpreis für einen zusätzlichen Standard-Heizkörper', 660.00, NULL),
    ('Mehrpreis für einen Heizkörper mit glatter Oberfläche', 160.00, NULL),
    ('Handtuchheizkörper', 660.00, NULL),
    ('Fußbodenheizung ohne DG', 8990.00, NULL),
    ('Fußbodenheizung mit DG', 9990.00, NULL),

    -- 6 Sanitärinstallation / Einträge: 23-26
    ('Mehrpreis für ein größeres Waschbecken im OG', 160.00, NULL),
    ('Mehrpreis für ein größeres Waschbecken im DG', 160.00, NULL),
    ('Mehrpreis für eine bodentiefe Dusche im OG', 560.00, NULL),
    ('Mehrpreis für eine bodentiefe Dusche im DG', 560.00, NULL),

    -- 7 Fliesen / Einträge: 27-32
    ('Keine Fliesen im Küchenbereich des EG', -590.00, NULL),
    ('Keine Fliesen im Bad des OG', -1870.00, NULL),
    ('Mehrpreis bei großformatige Fliesen im Küchenbereich des EG', 170.00, NULL),
    ('Mehrpreis bei großformatige Fliesen im Bad des OG', 190.00, NULL),
    ('Fliesen im Bad des DG', 2190.00, NULL),
    ('Mehrpreis bei großformatige Fliesen im Bad des DG', 190.00, NULL),

    -- 8 Parkett / Einträge: 33-42
    ('Landhausdielen massiv im Essbereich des EG', 2890.00, NULL),
    ('Landhausdielen massiv im Küchenbereich des EG', 2090.00, NULL),
    ('Stäbchenparkett im Essbereich des EG', 2090.00, NULL),
    ('Stäbchenparkett im Küchenbereich des EG', 1790.00, NULL),
    ('Landhausdielen massiv im OG', 2490.00, NULL),
    ('Stäbchenparkett im OG', 1690.00, NULL),
    ('Landhausdielen massiv komplett im DG', 2490.00, NULL),
    ('Landhausdielen massiv ohne Badbereich im DG', 2090.00, NULL),
    ('Stäbchenparkett im DG komplett im DG', 1690.00, NULL),
    ('Stäbchenparkett ohne Badbereich im DG', 1690.00, NULL),

    -- 9 Außenanlagen / Einträge: 43-49
    ('Abstellraum auf der Terrasse des EG', 3590.00, NULL),
    ('Vorbereitung für elektrische Antriebe Markise EG', 170.00, NULL),
    ('Vorbereitung für elektrische Antriebe Markise DG', 170.00, NULL),
    ('Elektrische Markise EG', 890.00, NULL),
    ('Elektrische Markise DG', 890.00, NULL),
    ('Elektrischen Antrieb für das Garagentor', 990.00, NULL),
    ('Sektionaltor anstatt Schwingtor für die Garage', 790.00, NULL);