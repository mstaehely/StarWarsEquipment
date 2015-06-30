import java.awt.event.*;

import javax.swing.*;

/**
 * Controls the combo boxes for the GUI window.
 * 
 * @author Matthew Staehely
 * @version 1
 */
public class EquipmentBoxListener implements ItemListener{
    private ItemList item_list;
    private JComboBox<Equipment> cb;
    private Equipment item;
    
    /**
     * Constructor for objects of class EquipmentBoxListener.
     * 
     * @param item_list the model object of the program.
     */
    public EquipmentBoxListener(ItemList item_list){
        this.item_list = item_list;
        cb = null;
    }
    
    @SuppressWarnings("unchecked")
    /**
     * Processes specific item selections by user.
     * 
     * @param e the event created by the selection.
     */
    public void itemStateChanged(ItemEvent e){
        // Item Listener object - selected == 1, deselected == 2
        if(e.getStateChange() == 1){
            cb = (JComboBox<Equipment>)e.getSource();
            if(!cb.getSelectedItem().equals("Select Item")){
                item = (Equipment)cb.getSelectedItem();
                item_list.selectItem(item);
                item_list.selectCombinedItem();
                item_list.selectAttachment(null);
            }
        }
    }
}