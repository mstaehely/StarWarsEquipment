import java.awt.event.*;

/**
 * Controls button clicks for the GUI window.
 * 
 * @author Matthew Staehely
 * @version 1
 */

class ButtonListener implements ActionListener{
    private ItemList item_list;
    
    /**
     * Constructor for objects of class ButtonListener
     */
    public ButtonListener(ItemList item_list){
        this.item_list = item_list;
    }
    
    /**
     * Process button clicks by telling the model to update the combined item.
     * 
     * @param e the event created by the click.
     */
    public void actionPerformed(ActionEvent e){
        if(item_list.getCombinedItem() != null){
            item_list.combineItems();
        }
    }
}