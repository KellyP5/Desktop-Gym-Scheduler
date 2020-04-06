package main.java.memoranda.ui;

import javax.swing.JOptionPane;

import main.java.memoranda.util.Local;

/**
 * The type Import sticker.
 */
public class ImportSticker {

    /**
     * The Name.
     */
    String name;

    /**
     * Instantiates a new Import sticker.
     *
     * @param x the x
     */
    public ImportSticker(String x) {
                name = x;
        }

    /**
     * Import file boolean.
     *
     * @return the boolean
     */
    public boolean import_file(){
                /*
                 We are working on this =)
                  
                  
                  */
                
                JOptionPane.showMessageDialog(null,Local.getString("Failed to import document"));
                return true;
        }
        
        
}