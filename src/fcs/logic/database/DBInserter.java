package fcs.logic.database;

import fcs.logic.core.Piece;

/**
 *
 * @author cdrobaina01
 */
public interface DBInserter {
    public void insertMaterial(String name);
    public void insertPiece(Piece piece);
}
