import java.util.*;

/**
 * Provides a framework to create attachments for the Star Wars game.
 * 
 * @author Matthew Staehely
 * @version 1
 */
public class Attachment extends Equipment{
    private String type, useable_by;
    private ArrayList<Trait> traits;
    
    /**
     * Constructor for objects of class Attachment.
     * 
     * @param reader the Scanner used to pass data.
     */
    public Attachment(Scanner reader){
        super(reader);
        setType(reader.next());
        setTraits(reader);
        setUsable(reader.next());
    }
    
    private void setType(String type){
        this.type = type;
    }
    
    private void setTraits(Scanner reader){
        int trait_level;
        traits = new ArrayList<Trait>();
        for(int i = 0; i < 31; i++){
            trait_level = reader.nextInt();
            traits.add(new Trait(i, trait_level));
        }
    }
    
    private void setUsable(String useable_by){
        this.useable_by = useable_by;
    }
    
    /**
     * Returns the type of equipment the attachment may be added to.
     * 
     * @return the type of equipment the attachment may be added to.
     */
    public String getType(){
        return this.type;
    }
    
    /**
     * Returns a numerical representation of the attachment's Traits.
     * 
     * @return a numerical array representing the attachment's Traits.
     */
    public ArrayList<Trait> getTraits(){
        return this.traits;
    }
    
    /**
     * Returns the specfic armor or weapon classes the attachment may be used by.
     * 
     * @return the specific armor or weapon classes the attachment may be used by.
     */
    public String getUseable(){
        return this.useable_by;
    }
    
    
    /**
     * Returns a String representation of this object.
     * 
     * @return a String representation of this object.
     */
    public String getInfo(){
        String string = super.toString();
        string += "\nEncumbrance: " + getEncumbrance() + "\tHard Points Needed: ";
        string += getHardPoints() + "\n";
        if(getRestricted()){
            string += "(R)";
        }
        string += getCost() + " credits " + "Rarity: " + getRarity();
        if(!getSpecial().equals("")){
            string += "\n" + getSpecial();
        }
        for(int i = 0; i < getTraits().size(); i++){
            Trait trait = getTraits().get(i);
            if(trait.getLevel() != 0){
                string += "\n" + trait;
            }
        }
        return string;
    }
}