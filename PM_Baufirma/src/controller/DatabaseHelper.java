package controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    /* Verbindungsdaten hier ergänzen, ggf. durch zB config ersetzen statt hardcoden */
    private static String db_host = "localhost";
    private static String db_user = "";
    private static String db_password = "";
    private static String db_database = "";
    private static String db_port = "3306";


    public DatabaseHelper() {
        try {
            Files.lines(Path.of("PM_Baufirma/.env"))
                    .filter(line -> line.contains("="))
                    .forEach(this::load);
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Laden der .env", e);
        }
    }

    private void load(String line) {
        String[] parts = line.split("=", 2);
        if (parts.length == 2) {
            String key = parts[0].trim();
            String value = parts[1].trim();

            switch (key) {
                case "MYSQL_USER":
                    db_user = value;
                    break;
                case "MYSQL_ROOT_PASSWORD", "MYSQL_PASSWORD":
                    db_password = value;
                    break;
                case "MYSQL_DATABASE":
                    db_database = value;
                    break;
                default:
                    System.out.println("Unbekannter Schlüssel: " + key);
            }
        }
    }

    public static String getDbHost() {
        return db_host;
    }

    public static String getDbUser() {
        return db_user;
    }

    public static String getDbPassword() {
        return db_password;
    }

    public static String getDbDatabase() {
        return db_database;
    }

    public static String getDbPort() {
        return db_port;
    }

    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://" + db_host + ":" + db_port + "/" + db_database;
        return DriverManager.getConnection(url, getDbUser(), getDbPassword());
    }
}
