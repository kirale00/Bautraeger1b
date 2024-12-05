package controller;

public class DatabaseHelper {
    /* Verbindungsdaten hier ergänzen, ggf. durch zB config ersetzen statt hardcoden */
    private static String db_host = "localhost";
    private static String db_user = "user";
    private static String db_password = "password123";
    private static String db_database = "Bautraeger";
    private static String db_port = "3306";

    public void getConnection() {
        /*
            todo: Void durch die MysqlClient klasse ersetzen.
            ------------------------------------------------------------
            diese methode soll eine Datenbank Client verbindung returnen,
            die wir dann im Controller wiederverwenden können.
         */
    }
}
