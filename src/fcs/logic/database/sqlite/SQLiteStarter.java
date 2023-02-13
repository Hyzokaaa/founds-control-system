package fcs.logic.database.sqlite;

import fcs.logic.core.Piece;
import fcs.logic.database.DBStarter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import org.sqlite.SQLiteConnection;

/**
 *
 * @author cdrobaina01
 */
public class SQLiteStarter implements DBStarter{
    
    private SQLiteConnection connection;
    
    public SQLiteStarter(Connection connection) {
        if(connection instanceof SQLiteConnection) {
            this.connection = (SQLiteConnection)connection;
        }
        else {
            throw new IllegalArgumentException("Mistaken SQL implementation");
        }
    }
    
    @Override
    public void Create(Connection connection) {
        createTables();
        populateTables();
        System.out.println("Database Succefully Created");
    }

    private void createTables() {
        System.out.println("Creating tables");
        try {
            //Create inventory Table
            String query = "CREATE TABLE IF NOT EXISTS inventory (\n"
                    + "  id integer PRIMARY KEY NOT NULL,\n"
                    + "  NoInv text NOT NULL,\n"
                    + "  OldInv text,\n"
                    + "  Date integer,\n"
                    + "  Entry text,\n"
                    + "  Description text NOT NULL,\n"
                    + "  Amount integer,\n"
                    + "  Material integer NOT NULL,\n"
                    + "  Dimensions text,\n"
                    + "  State integer, \n"
                    + "  Price integer,\n"
                    + "  Location text NOT NULL,\n"
                    + "  Extra text,\n"
                    + "  CONSTRAINT material_id FOREIGN KEY (Material) REFERENCES materials (id) ON DELETE NO ACTION ON UPDATE NO ACTION\n"
                    + ");";
            Statement stmt = connection.createStatement();
            stmt.execute(query);
            //Create materials Table
            query = "CREATE TABLE IF NOT EXISTS materials (\n"
                    + "  id integer PRIMARY KEY NOT NULL,\n"
                    + "  name text NOT NULL\n"
                    + ");";
            stmt = connection.createStatement();
            stmt.execute(query);
            System.out.println("Succefully created Tables");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void populateTables() {
        System.out.println("Populating Tables");
        SQLiteInserter inserter = new SQLiteInserter(connection);
        String [] materials = {"metal", "madera", "cuero", "textil", "varios"};
        for(String item : materials){
            inserter.insertMaterial(item);
        }
        Piece piece = new Piece();
        piece.setAmount(5);
        piece.setBook(1);
        piece.setDescription("Pieza museable muy pro");
        piece.setDimensions("15 cm x 20 cm");
        piece.setInventoryDate(Calendar.getInstance().getTime());
        piece.setLocation("E4 P2 AB");
        piece.setMaterial(2);
        piece.setNoInv(2);
        piece.setObservations("Pieza muy pequeña");
        piece.setOldInv("LIA 2/2");
        piece.setPeriod(2);
        piece.setPrice(25);
        piece.setRegisterData("Donado por Alvarez Campos el 1ro de Enero");
        piece.setState('B');
        inserter.insertPiece(piece);
    }
}
