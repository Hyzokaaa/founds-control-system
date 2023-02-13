package fcs.logic.core;

import java.util.Date;

/**
 *
 * @author cdrobaina01
 */
public class Piece {

    private String oldInv;
    private int book;
    private int period;
    private int noInv;
    private Date inventoryDate;
    private String registerData;
    private String description;
    private int amount;
    private int material;
    private String dimensions;
    private char state;
    private int price;
    private String location;
    private String observations;

    public void Piece() {
        
    }

    //Not validated Setters
    public void setOldInv(String oldInv) {this.oldInv = oldInv;}
    public void setInventoryDate(Date inventoryDate) {this.inventoryDate = inventoryDate;}
    public void setPrice(int price) {this.price = price;}
    public void setAmount(int amount) {this.amount = amount;}
    public void setLocation(String location) {this.location = location;}
    public void setObservations(String observations) {this.observations = observations;}
    public void setRegisterData(String registerData) {this.registerData = registerData;}
    public void setDescription(String description) {this.description = description;}
    public void setDimensions(String dimensions) {this.dimensions = dimensions;}
    
    //Valildated Setters
    public void setBook(int book) {
        if(book >= 0 && book <= 1) {
            this.book = book;
        } else {
            throw new IllegalArgumentException("Book id must be Principal(0) or Auxiliar(1)");
        }
        
    }
    public void setPeriod(int period) {
        if(period >= 1 && period <= 5) {
            this.period = period;
        } else {
            throw new IllegalArgumentException("Period must be in the range 1 -> 5: \n"
                                                + "1 - Colonia \n"
                                                + "2 - República \n"
                                                + "3 - Revolución \n"
                                                + "4 - Personalidades \n"
                                                + "5 - Palacio Museo \n");
        }
    }
    public void setNoInv(int noInv) {
        if(noInv >= 0) {
            this.noInv = noInv;
        } else {
            throw new IllegalArgumentException("No. Inv must be greater than 0");
        }
    }
    public void setMaterial(int material) {
        if(material >= 0 && material <= 4) {
            this.material = material;
        } else {
            throw new IllegalArgumentException("Material must be in the range 1 -> 5: \n"
                                                + "0 - Metal \n"
                                                + "1 - Madera \n"
                                                + "2 - Cuero \n"
                                                + "3 - Textil \n"
                                                + "4 - varios \n");
        }
    }
    public void setState(char state) {
        state = Character.toTitleCase(state);
        if(state == 'B' || state == 'R' || state == 'M') {
            this.state = state;
        } else {
            throw new IllegalArgumentException("State must be one of this three: \n"
                                                + "B - Bueno \n"
                                                + "R - Regular \n"
                                                + "M - Mal \n");
        }
    }

    //Getters
    public String getNoInv() {
        char book = (this.book == 0) ? 'P' : 'A';
        String result = "LI" + book + " " + period + "-" + noInv;
        return result;
    }
    public int getAmount() {return amount;}
    public String getOldInv() {return oldInv;}
    public Date getInventoryDate() {return (Date)inventoryDate.clone();}
    public String getRegisterData() {return registerData;}
    public String getDescription() {return description;}
    public int getMaterial() {return material;}
    public String getDimensions() {return dimensions;}
    public Character getState() {return state;}
    public int getPrice() {return price;}
    public String getLocation() {return location;}
    public String getObservations() {return observations;}
    
    @Override
    public String toString() {return getNoInv();}
}
