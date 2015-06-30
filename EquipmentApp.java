import javax.swing.*;
import java.io.*;

/**
 * EquipmentApp class
 * 
 * @author Matthew Staehely
 * @version 1
 */
public class EquipmentApp{
    private ItemList item_list;
    private EquipmentController control;
    private JFrame win;
    /**
     * Main method for using this program.
     * 
     * @param args not used in this context.
     */
    public static void main(String[] args){
        new EquipmentApp();
    }
    
    /**
     * Constructs a new EquipmentApp object.
     */
    public EquipmentApp(){
        try{
            item_list = new ItemList();
        } catch (IOException e){}
        
        win = new JFrame("Equipment Viewer");
        win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       
        control = new EquipmentController(item_list);
        win.getContentPane().add(control);
        
        win.pack();
        win.setVisible(true);
    }
}