import java.awt.event.*;

import javax.swing.*;

/**
 * Controls the combo boxes for the GUI window.
 * 
 * @author Matthew Staehely
 * @version 1
 */
public class AttachmentBoxListener implements ItemListener{
    private ItemList item_list;
    private JComboBox<Attachment> cb;
    private Attachment attachment;
    
    /**
     * Constructor for objects of class EquipmentBoxListener.
     * 
     * @param item_list the model object of the program.
     */
    public AttachmentBoxListener(ItemList item_list){
        this.item_list = item_list;
        cb = null;
        attachment = item_list.getAttachment();
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
            cb = (JComboBox<Attachment>)e.getSource();
            if(!cb.getSelectedItem().equals("Select Attachment")){
                attachment = (Attachment)cb.getSelectedItem();
                item_list.selectAttachment(attachment);
            }
        }
    }
}