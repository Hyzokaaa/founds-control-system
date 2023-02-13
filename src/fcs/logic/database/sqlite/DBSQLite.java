package fcs.logic.database.sqlite;

import fcs.logic.database.DB;
import java.io.File;
import java.sql.SQLException;
import java.util.Properties;
import org.sqlite.JDBC;
import org.sqlite.SQLiteConnection;

/**
 *
 * @author cdrobaina01
 */
public class DBSQLite implements DB{
    private SQLiteConnection connection;
    private String path = "C:/Sistema de Control de Fondos/db/fcs.db";
    private String url = "jdbc:sqlite:" + path;
    
    private void debug() {
        File dbFile = new File(path);
        dbFile.delete();
    }
    
    public DBSQLite() {
        System.out.println("Deleting Database for debugging purpose");
        debug();
        System.out.println("Creating connection with Database");
        boolean exist = checkExistency();
        try {
            connection = JDBC.createConnection(url, new Properties());
            System.out.println("Connection to SQLite has been established.");
            if(!exist) {
                System.out.println("Empty Database dectected.");
                new SQLiteStarter(connection).Create(connection);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private boolean checkExistency() {
        File dbFile = new File(path);
        return dbFile.exists();
    }
}
