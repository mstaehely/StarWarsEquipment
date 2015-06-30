import java.util.Scanner;

/**
 * Provides framework for construction armor objects in the Star Wars game.
 * 
 * @author Matthew Staehely
 * @version 1
 */
public class Armor extends Equipment{
    private int soak_value, defense;
    private boolean full_body;
    
    /**
     * Constructor for armor objects
     * 
     * @param reader the Scanner used to input data.
     */
    public Armor(Scanner reader){
        super(reader);
        setSoakValue(reader.nextInt());
        setDefense(reader.nextInt());
        setFullBody(reader.nextInt());
    }
    
    private void setSoakValue(int soak_value){
        this.soak_value = soak_value;
    }
    
    private void setDefense(int defense){
        this.defense = defense;
    }
    
    private void setFullBody(int full_body){
        if(full_body == 0){
            this.full_body = false;
        } else {
            this.full_body = true;
        }
    }
    
    /**
     * Returns the armor's soak value.
     * 
     * @return the armor's soak value.
     */
    public int getSoakValue(){
        return soak_value;
    }
    
    /**
     * Returns the armor's defense rating.
     * 
     * @return the armor's defense rating.
     */
    public int getDefense(){
        return defense;
    }
    
    /**
     * Checks to see if the armor is full body.
     * 
     * @return true if the armor is full body.
     */
    public boolean getFullBody(){
        return full_body;
    }
    
    public String getInfo(){
        String string = super.toString();
        string += "\nSoak Value: " + getSoakValue() + " Defense: " + getDefense();
        string += " Encumbrance: " + getEncumbrance() + " Hard Points: ";
        string += getHardPoints() + "\n";
        if(getRestricted()){
            string += "(R)";
        }
        string += getCost() + " credits " + "Rarity: " + getRarity();
        if(getFullBody()){
            string += "\nFull body armor";
        }
        if(!getSpecial().equals("")){
            string += "\n" + getSpecial();
        }
        return string;
    }
}