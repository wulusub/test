import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class test{
    Connection connection = null;
    public void datenbankErstellen() {

        try {
            // Pfad zur SQLite-Datenbankdatei angeben
            String dbFile = "H:/Informatik/sqlite test/test.db";

            // Verbindung zur Datenbank herstellen
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
            
            clearTable(connection);    
            System.out.println("SQLite-Datenbank erfolgreich erstellt.");

        } catch (SQLException e) {
            e.printStackTrace();
           
        }
    }

    Statement statement = null;
    ResultSet resultSet = null;
    public void tabelleErstellen(String eingabe){
        try {
            statement = connection.createStatement();

            // Tabelle erstellen
            String createTableQuery = "CREATE TABLE IF NOT EXISTS testTable (Zugnummer INT, Zug TEXT)";
            statement.executeUpdate(createTableQuery);
            System.out.println("Tabelle erfolgreich erstellt.");
            insertDataIntoTable(connection, eingabe);

        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    
    public static void insertDataIntoTable(Connection connection, String text) throws SQLException {
        // SQL-Abfrage zum Einfügen von Daten
        String insertQuery = "INSERT INTO testTable (Zugnummer, Zug) VALUES (?, ?)";

        // Prepared Statement vorbereiten
        PreparedStatement statement = connection.prepareStatement(insertQuery);

        // Werte für die Parameter festlegen
        statement.setInt(1, 1);
        statement.setString(2, text);

        // Einfügen der Daten ausführen
        statement.executeUpdate();
        
        System.out.println("Daten eingefügt.");
        
        // Prepared Statement schließen
        statement.close();
    }
    
    public static void clearTable(Connection connection) throws SQLException {
        //leert die komplette Tabelle
        String deleteQuery = "DELETE FROM testTable";
        Statement statement = connection.createStatement();
        statement.executeUpdate(deleteQuery);
        statement.close();
    }
    
}