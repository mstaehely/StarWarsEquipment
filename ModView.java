import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ModView extends JPanel implements EquipmentListener{
	private static final long serialVersionUID = 1L;
    private ItemList item_list;
    private JComboBox attachmentBox;
    private ArrayList<Attachment> mods;
    private Equipment item;
    
    /**
     * Constructor for objects of class ModView.
     */
    public ModView(ItemList item_list){
        this.item_list = item_list;
        this.item_list.addAttachmentListener(this);
        this.setLayout(new BorderLayout());
        
        attachmentBox = new JComboBox();
        attachmentBox.addItemListener(new AttachmentBoxListener(item_list));
        
        mods = item_list.getAttachmentList();
        this.add(attachmentBox, BorderLayout.NORTH);
    }
    
    @SuppressWarnings("unchecked")
    public void update(){
        item = item_list.getItem();
        String useable;
        boolean add = false;
        attachmentBox.removeAllItems();
        attachmentBox.addItem("Select Attachment");
        if(item instanceof Weapon){
            if(item_list.getItem() == null){
            } else {
                Scanner reader;
                Weapon weapon = (Weapon)item;
                String types = weapon.getTypes();
                reader = new Scanner(types);
                ArrayList<String> type = new ArrayList<String>();
                while(reader.hasNext()){
                    type.add(reader.next());
                }
                reader.close();
                for(Attachment a : mods){
                    if(a.getType().equals("weapon")){
                        useable = a.getUseable();
                        for(String s : type){
                            if(useable.contains(s)){
                                add = true;
                            }
                            if(weapon.getHardPoints() < a.getHardPoints()){
                                add = false;
                            }
                        }
                    }
                    if(add || a.getType().equals("any")){
                        attachmentBox.addItem(a);
                        add = false;
                    }
                }
            }
        } else if (item instanceof Armor){
            if(item_list.getItem() == null){
            } else {
                Armor armor = (Armor)item;
                boolean full = armor.getFullBody();
                for(Attachment a : mods){
                    if(a.getType().equals("armor")){
                        add = true;
                        if(a.getUseable().equals("full-body") && !full){
                            add = false;
                        }
                        if(armor.getHardPoints() < a.getHardPoints()){
                            add = false;
                        }
                        if(add || a.getType().equals("any")){
                            attachmentBox.addItem(a);
                            add = true;
                        }
                    }
                }
            }
        }
    }
}