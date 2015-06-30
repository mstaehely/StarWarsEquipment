import java.util.*;
import java.io.*;

/**
 * Constructs an Item List to store Equipment objects.
 * 
 * @author Matthew Staehely
 * @version 1
 */
public class ItemList{
    private ArrayList<Weapon> weapon_list;
    private ArrayList<Armor> armor_list;
    private ArrayList<Attachment> attachment_list;
    private CombinedItem combined_item;
    private Scanner reader, input;
    private EquipmentListener listener, textListener, attachmentListener,
            attachmentTextListener, combinedItemListener;
    private String selected;
    private Equipment item;
    private Attachment attachment;
    private File file;
    
    /**
     * Constructs the ItemList.
     * 
     * @throw IOException if the data file cannot be read.
     */
    public ItemList() throws IOException{
        weapon_list = new ArrayList<Weapon>();
        armor_list = new ArrayList<Armor>();
        attachment_list = new ArrayList<Attachment>();
        //trait_list = new ArrayList<Trait>();
        reader = new Scanner(System.in);
        listener = textListener = attachmentListener =
        attachmentTextListener = null;
        file = new File("equipment.csv");
        readIn(file);
//         selected = "Armor";
//         item = armor_list.get(0);
//         combined_item = new CombinedItem((Armor)item);
        selected = "";
        item = null;
        attachment = null;
        combined_item = null;
    }
    
    /**
     * Reads in the data file provided.
     * 
     * @throw IOException if the data file cannot be read.
     */
    public void readIn(File f) throws IOException{
        input = new Scanner(f);
        //Scanner reader;
        String type;
        reader = new Scanner(input.nextLine());
        while(reader.hasNext()){
            reader.useDelimiter(",");
            type = reader.next();
            switch(type){
                case "a": Armor armor = new Armor(reader);
                        armor_list.add(armor);
                        break;
                case "w": Weapon weapon = new Weapon(reader);
                        weapon_list.add(weapon);
                        break;
                case "m": Attachment attachment = new Attachment(reader);
                        attachment_list.add(attachment);
                        break;
            }
            if(input.hasNext()){
                reader = new Scanner(input.nextLine());
            }
        }
    }
    
    /**
     * Selects which of the three equipment categories the user wishes to examine.
     * 
     * @param string which category (armor, attachment, weapon) to be examined.
     */
    public void selectCategory(String string){
        this.selected = string;
        notifyMenuListeners();
    }
    
    /**
     * Selects which item will be displayed.
     * 
     * @param item which item is being displayed.
     */
    public void selectItem(Equipment item){
        this.item = item;
        notifyTextListeners();
        notifyAttachmentListeners();
    }
    
    /**
     * Selects which attachment will be displayed.
     * 
     * @param attachment which attachment is being displayed.
     */
    public void selectAttachment(Attachment attachment){
        this.attachment = attachment;
        notifyAttachmentTextListeners();
    }
    
    public void selectCombinedItem(){
        if(this.getItem() instanceof Weapon){
            combined_item = new CombinedItem((Weapon)this.getItem());
        } else if(this.getItem() instanceof Armor){
            combined_item = new CombinedItem((Armor)this.getItem());
        }
        notifyCombinedItemListeners();
    }
    
    /**
     * Registers a menu listener.
     * 
     * @param listener EquipmentListener object.
     * @throw IllegalStateException if a null listener is added.
     */
    public void addMenuListener(EquipmentListener listener){
        if(listener == null) throw new IllegalStateException();
        this.listener = listener;
    }
    
    /**
     * Registers a text listener.
     * 
     * @param listener EquipmentListener object.
     * @throw IllegalStateException if a null listener is added.
     */
    public void addTextListener(EquipmentListener listener){
        if(listener == null) throw new IllegalStateException();
        this.textListener = listener;
    }
    
    /**
     * Registers an attachment listener.
     * These are used to update the attachment selection box.
     * 
     * @param listener EquipmentListener object.
     * @throw IllegalStateException if a null listener is added.
     */
    public void addAttachmentListener(EquipmentListener listener){
        if(listener == null) throw new IllegalStateException();
        this.attachmentListener = listener;
    }
    
    /**
     * Registers an attachment info listener.
     * This is used to update the attachment textual information.
     * 
     * @param listener EquipmentListener object.
     * @throw IllegalStateException if a null listener is added.
     */
    public void addAttachmentTextListener(EquipmentListener listener){
        if(listener == null) throw new IllegalStateException();
        this.attachmentTextListener = listener;
    }
    
    /**
     * Registers a combined item listener.
     * This is used to update the combined item's textual information.
     * 
     * @param listener EquipmentListener object.
     * @throw IllegalStateException if a null listener is added.
     */
    public void addCombinedItemListener(EquipmentListener listener){
        if(listener == null) throw new IllegalStateException();
        this.combinedItemListener = listener;
    }
    
    /**
     * Notifies any registered text listeners of changes to the model.
     */
    public void notifyTextListeners(){
        textListener.update();
    }
        
    
    /**
     * Notifies any registered menu listeners of changes to the model.
     */
    public void notifyMenuListeners(){
        listener.update();
    }
    
    /**
     * Notifies any registered attachment listeners of relevant changes to the model.
     */
    public void notifyAttachmentListeners(){
        attachmentListener.update();
    }
    
    /**
     * Notifies any registered attachment text listeners of relevant changes.
     */
    public void notifyAttachmentTextListeners(){
        attachmentTextListener.update();
    }
    
     /**
     * Notifies any registered combined item text listeners of relevant changes.
     */
    public void notifyCombinedItemListeners(){
        combinedItemListener.update();
    }
    
    /**
     * Returns the specific weapon or armor selected.
     * 
     * @return which specific item has been selected.
     */
    public Equipment getItem(){
        return item;
    }
    
    /**
     * Returns the category (armor or weapon) selected.
     * 
     * @return which category has been selected.
     */
    public String getSelected(){
        return selected;
    }
    
    /**
     * Returns the selected attachment.
     * 
     * @return the selected attachment.
     */
    public Attachment getAttachment(){
        return attachment;
    }
    
    /**
     * Passes the weapon ArrayList to a viewer.
     * 
     * @return the weapon ArrayList.
     */
    public ArrayList<Weapon> getWeaponList(){
        return weapon_list;
    }
    
    /**
     * Passes the armor ArrayList to a viewer.
     * 
     * @return the armor ArrayList.
     */
    public ArrayList<Armor> getArmorList(){
        return armor_list;
    }
    
    /**
     * Passes the attachment ArrayList to a viewer.
     * 
     * @return the attachment ArrayList.
     */
    public ArrayList<Attachment> getAttachmentList(){
        return attachment_list;
    }
    
    /**
     * Creates a combined item.
     */
    public void combineItems(){
        this.combined_item.addAttachment(this.getAttachment());
        notifyCombinedItemListeners();
    }
    
    /**
     * Returns the combined item to display to the user.
     */
    public Equipment getCombinedItem(){
        return this.combined_item;
    }
}