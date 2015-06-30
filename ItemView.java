import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Text listener.
 * 
 * @author Matthew Staehely
 * @version 1
 */

public class ItemView extends JPanel implements EquipmentListener{
	private static final long serialVersionUID = 1L;
    private ItemList item_list;
    private JComboBox itemBox;
    private JComboBox<String>categoryBox;
    private String[] selections = {"Select Item", "Armor", "Weapons"};

    /**
     * Constructor for objects of class ItemView.
     */
    public ItemView(ItemList item_list){
        this.item_list = item_list;
        this.item_list.addMenuListener(this);
        this.setLayout(new BorderLayout());
        
        categoryBox = new JComboBox<String>(selections);
        categoryBox.addItemListener(new CategoryBoxListener(item_list));
        this.add(categoryBox, BorderLayout.WEST);
        
        itemBox = new JComboBox();
        itemBox.addItemListener(new EquipmentBoxListener(item_list));
        this.add(itemBox, BorderLayout.EAST);
    }
    
    
    @Override
	@SuppressWarnings("unchecked")
    /**
     * Notifies the output that textual information has changed and needs to be 
     * updated.
     */
    public void update(){
        String selected = item_list.getSelected();
        item_list.selectItem(null);
        itemBox.removeAllItems();
        itemBox.addItem("Select Item");
        if(selected.equals("Armor")){
            ArrayList<Armor> armor_list = item_list.getArmorList();
//             itemBox.removeAllItems();
            for(Armor a : armor_list){
                itemBox.addItem(a);
            }
        } else if(selected.equals("Weapons")){
            ArrayList<Weapon> weapon_list = item_list.getWeaponList();
//             itemBox.removeAllItems();
            for(Weapon w : weapon_list){
                itemBox.addItem(w);
            }
        }
    }
}