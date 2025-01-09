package controller;

import business.sonderwunsch.Sonderwunsch;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class SpeicherHelper {
    public static void save(ArrayList<Sonderwunsch> wuensche, ArrayList<CheckBox> checkBoxList) {
        Connection connection = null;
        PreparedStatement insertStatement = null;

        try {
            connection = new DatabaseHelper().getConnection();
            insertStatement = connection.prepareStatement("INSERT INTO Kundenwunsch (Kundennummer, Sonderwunschid, Anzahl) values (?,?,?)");

            for(int i=0; i<checkBoxList.size(); i++) {
                if (checkBoxList.get(i).isSelected()) {
                    insertStatement.setInt(1, gui.kunde.KundeView.cmbKundeDropdown.getValue());
                    insertStatement.setInt(2, wuensche.get(i).getSonderwunschId());
                    insertStatement.setInt(3, 1);
                    insertStatement.executeUpdate();
                }
            }
            System.out.println("Die Sonderwünsche wurden erfolgreich gespeichert.");
        }
        catch (SQLIntegrityConstraintViolationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText(null);  // Keine Kopfzeile
            alert.setContentText("Mindestens eins der ausgewählten Sonderwünschen ist bereits gespeichert!");
            alert.showAndWait();
        }
        catch (Exception e) {
            System.out.println("Fehler beim Speichern der Sonderwuensche");
            e.printStackTrace();
        }
    }
}
