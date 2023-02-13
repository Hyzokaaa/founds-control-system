package fcs.logic.database.sqlite;

import fcs.logic.core.Piece;
import fcs.logic.database.DBInserter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.sqlite.SQLiteConnection;

/**
 *
 * @author cdrobaina01
 */
public class SQLiteInserter implements DBInserter{
    private SQLiteConnection connection;
    private String materialsQuery = "INSERT INTO materials(name) VALUES(?)";
    private String inventoryQuery = "INSERT INTO inventory("
            + "NoInv, \n"
            + "OldInv, \n"
            + "Date, \n"
            + "Entry, \n"
            + "Description, \n"
            + "Amount, \n"
            + "Material, \n"
            + "Dimensions, \n"
            + "State, \n"
            + "Price, \n"
            + "Location, \n"
            + "Extra) \n"
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    
    public SQLiteInserter(Connection connection) {
        if(connection instanceof SQLiteConnection) {
            this.connection = (SQLiteConnection)connection;
        }
        else {
            throw new IllegalArgumentException("Mistaken SQL implementation");
        }
    }

    @Override
    public void insertMaterial(String name) {
        try (PreparedStatement pstmt = connection.prepareStatement(materialsQuery)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insertPiece(Piece piece) {
        try (PreparedStatement pstmt = connection.prepareStatement(inventoryQuery)) {
            pstmt.setString(1, piece.getNoInv());
            pstmt.setString(2, piece.getOldInv());
            pstmt.setInt(3, (int) piece.getInventoryDate().getTime());
            pstmt.setString(4, piece.getRegisterData());
            pstmt.setString(5, piece.getDescription());
            pstmt.setInt(6, piece.getAmount());
            pstmt.setInt(7, piece.getMaterial());
            pstmt.setString(8, piece.getDimensions());
            pstmt.setString(9, piece.getState().toString());
            pstmt.setInt(10, piece.getPrice());
            pstmt.setString(11, piece.getLocation());
            pstmt.setString(12, piece.getObservations());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
