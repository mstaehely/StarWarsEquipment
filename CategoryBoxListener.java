import java.awt.event.*;
import javax.swing.*;

/**
 * Controls the combo boxes for the GUI window.
 * 
 * @author Matthew Staehely
 * @version 1
 */
public class CategoryBoxListener implements ItemListener{
    private ItemList item_list;
    private JComboBox<Equipment> cb;
    private String selection;
    
    /**
     * Constructor for objects of class CategorytBoxListener.
     * 
     * @param item_list the model object of the program.
     */
    public CategoryBoxListener(ItemList item_list){
        this.item_list = item_list;
        cb = null;
    }
    
    @SuppressWarnings("unchecked")
    /**
     * Processes item selections by tracking which category has been picked by the user.
     * 
     * @param e the event created by the selection.
     */
    public void itemStateChanged(ItemEvent e){
        // Item Listener object - selected == 1, deselected == 2
        if(e.getStateChange() == 1){
        	cb = (JComboBox<Equipment>)e.getSource();
        	selection = (String)cb.getSelectedItem();
        	item_list.selectCategory(selection);
        }
    }
}