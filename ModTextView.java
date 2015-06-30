import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class ModTextView extends JPanel implements EquipmentListener{
	private static final long serialVersionUID = 1L;
    private ItemList item_list;
    private JTextPane jtp, jtp2;
    private StyledDocument doc, doc2;
    private SimpleAttributeSet center, left;
    private Attachment attachment;
    public ModTextView(ItemList item_list){
        this.item_list = item_list;
        this.item_list.addAttachmentTextListener(this);
        
        jtp = new JTextPane();
        jtp.setEditable(false);
        jtp2 = new JTextPane();
        jtp2.setEditable(false);
        this.setLayout(new BorderLayout());
        
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
            doc.insertString(doc.getLength(), "--Selected Attachment--", null);
            
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
    
    public void update(){
        attachment = item_list.getAttachment();
        int length = doc2.getLength();
        if(item_list.getAttachment() == null){
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
                doc2.setParagraphAttributes(length+1, 1, left, false);
                doc2.insertString(doc2.getLength(), attachment.getInfo(), null);
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }
}