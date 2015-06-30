import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class EquipmentView extends JPanel implements EquipmentListener{
	private static final long serialVersionUID = 1L;
    private ItemList item_list;
    private JTextPane jtp, jtp2;
    private StyledDocument doc, doc2;
    private SimpleAttributeSet center, left;
    private Equipment item;
    
    /**
     * Constructs a viewer for the selected item's information.
     * 
     * @param item_list the ItemList object this is to be registered with.
     */
    public EquipmentView(ItemList item_list){
        
        // Assigns the object this item list, and registers it as a text listener.
        this.item_list = item_list;
        this.item_list.addTextListener(this);
        
        // Creates two text panes, one for 'selected equipment' and the other
        // to display the item's statistics. Set up with border layout to get around
        // flow issues.
        jtp = new JTextPane();
        jtp.setEditable(false);
        jtp2 = new JTextPane();
        jtp2.setEditable(false);
        this.setLayout(new BorderLayout());
        
        
        // Creates a styled document to allow for consistent alignment of text.
        doc = jtp.getStyledDocument();
        doc2 = jtp2.getStyledDocument();
        center = new SimpleAttributeSet();
        left = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
        try{
            int length = doc.getLength();
            doc.remove(0, doc.getLength());
            doc.setParagraphAttributes(length+1, 1, center, false);
            doc.insertString(doc.getLength(), "--Selected Equipment--", null);
            
            length = doc2.getLength();
            doc2.remove(0, doc2.getLength());
            doc2.setParagraphAttributes(length+1, 1, center, false);
            doc2.insertString(doc2.getLength(), "Select an item to begin", null);
        } catch(Exception e) {
            System.out.println(e);
        }
        
        this.add(jtp, BorderLayout.NORTH);
        this.add(jtp2, BorderLayout.CENTER);
    }
    
    /**
     * Updates the panel.
     */
    public void update(){
        item = item_list.getItem();
        int length = doc2.getLength();
        if(item_list.getItem() == null){
            try{
                doc2.remove(0, doc2.getLength());
                doc2.setParagraphAttributes(length+1, 1, center, false);
                doc2.insertString(doc2.getLength(), "----", null);
            } catch(Exception e) {
                System.out.println(e);
            }
        } else {
            try{
                doc2.remove(0, doc2.getLength());
                // Updates the item info whenever a new one is selected.
                doc2.setParagraphAttributes(length+1, 1, left, false);
                doc2.insertString(doc2.getLength(), item.getInfo(), null);
            } catch(Exception e) {
                System.out.println(e);
            }
            
        }
    }
}