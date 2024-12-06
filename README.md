## Datenbank

### Installierte Software
1. **Docker**  
   Lade Docker von [docker.com](https://www.docker.com/) herunter und installiere es.
2. **Docker Compose**  
   Docker Compose ist bei neueren Docker-Versionen standardmäßig enthalten. Prüfe die Installation mit:
   ```
   docker-compose --version
   ```


---


### Einrichtung 
 
1. **.env-Datei beschaffen** 
Hole dir die `.env`-Datei aus dem Discord und lege sie im Projektverzeichnis ab (dort, wo sich `docker-compose.yml` befindet).

3. **Container starten** 
Führe im Projektverzeichnis den folgenden Befehl aus:

```
docker-compose up -d
```

  - Der Container wird gestartet und die MySQL-Datenbank eingerichtet.
 
  - Die Datenbankstruktur und Standarddaten werden automatisch aus dem `db-init`-Ordner erstellt.
 
3. **Prüfen, ob der Container läuft** 
Überprüfe den Status des Containers:

```
docker ps
```


---


### Container-Management 

#### Container stoppen 

Um die Datenbank zu stoppen, führe aus:


```
docker-compose down
```

#### Datenbank und Daten komplett löschen 

Falls du die Datenbank zurücksetzen möchtest (inklusive aller Daten), führe aus:


```
docker-compose down -v
```
 
- Das `-v` löscht auch das Volume, in dem die Daten gespeichert sind.


---


### Zugriff auf die Datenbank 
#### Über die Kommandozeile 
 
1. Öffne die MySQL-Shell im Container:

```
docker exec -it projekt-datenbank mysql -u root -p
```

Zwischen `-p` und dem Passwort kommt **kein Leerzeichen!** Bei uns muss das also so aussehen:
```
docker exec -it projektmanagement-datenbank mysql -u root -prootpassword
```
 
2. Passwörter und Benutzer sind eigentlich in der `.env`-Datei zu finden.

3. Beispieldatei:

```
# MySQL-Konfiguration
MYSQL_ROOT_PASSWORD=rootpassword
MYSQL_DATABASE=projekt_db
MYSQL_USER=user
MYSQL_PASSWORD=password
```
**Achtung:**  Diese Datei sollte nicht ins Repository hochgeladen werden. Die Daten sind aber dennoch richtig, weil isso.

**INFO**: Man sollte die `.env` ins Verzeichnis `PM_Baufirma` tun, nicht ins Unterverzeichnis wie `src` o.ä.,
sonst wirtet nicht vom DatabaseHelper erkannt. (Die Datei dient sowohl als Config für Docker, als auch für Java zum
aufbauen der DB Connection).

---


### Fehlerbehebung 
 
- **Container startet nicht:** 
Überprüfe die Logs mit:

```
docker-compose logs
```
