import java.util.Scanner;

/**
 * Template for equipment items in EotE tabletop game.
 * 
 * @author Matthew Staehely
 * @version 1.0
 * 
 */

public abstract class Equipment{
    private String name, special;
    private int encumbrance, cost, rarity, hard_points;
    private boolean restricted;
    
    /**
     * Superclass constructor for Equipment objects. Abstract, cannot be instantiated.
     * 
     * @param reader the Scanner used to pass data to this object.
     */
    public Equipment(Scanner reader){
        setName(reader.next());
        setEncumbrance(reader.nextInt());
        setCost(reader.nextInt());
        setRarity(reader.nextInt());
        setRestricted(reader.nextInt());
        setHardPoints(reader.nextInt());
        setSpecial(reader.next());
    }
    
    public Equipment(Equipment item){
        setName(item.getName());
        setEncumbrance(item.getEncumbrance());
        setCost(item.getCost());
        setRarity(item.getRarity());
        setRestricted(item.getRestricted());
        setHardPoints(item.getHardPoints());
    }

    private void setName(String name){
        this.name = name;
    }
    
    private void setEncumbrance(int enc)
    {
        this.encumbrance = enc;
    }
    
    private void setCost(int cost){
        this.cost = cost;
    }
    
    private void setRarity(int rarity){
        this.rarity = rarity;
    }
    
    private void setRestricted(int restricted){
        if(restricted == 0){
            this.restricted = false;
        } else {
            this.restricted = true;
        }
    }
    
    private void setRestricted(boolean restricted){
        if(restricted){
            this.restricted = true;
        } else {
            this.restricted = false;
        }
    }
    
    protected void setHardPoints(int hard_points){
        this.hard_points = hard_points;
    }
    
    private void setSpecial(String special){
        Scanner reader = new Scanner(special);
        special = "";
        reader.useDelimiter("\\.");
        while(reader.hasNext()){
            special += reader.next().trim() + ".";
            if(reader.hasNext()){
                special += "\n";
            }
        }
        this.special = special;
        reader.close();
    }
    
    public void changeEncumbrance(int encumbrance){
        this.encumbrance += encumbrance;
        if(this.encumbrance < 1){
            this.encumbrance = 1;
        }
    }
    
    /**
     * Returns the name of the item.
     * 
     * @return the name of the item.
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Returns the cost of the item.
     * 
     * @return the cost of the item.
     */
    public int getCost(){
        return this.cost;
    }
    
    /**
     * Returns the rarity of the item.
     * 
     * @return the rarity of the item.
     */
    public int getRarity(){
        return this.rarity;
    }
    
    public int getEncumbrance(){
        return this.encumbrance;
    }
    
    /**
     * Returns whether the item is restricted for purchase.
     * 
     * @return the restriction on the item.
     */
    public boolean getRestricted(){
        return this.restricted;
    }
    
    /**
     * Returns the hard points. For armor and weapons, this value is capacity. For attachments
     * it represents the cost to attach.
     * 
     * @return the number of hard points.
     */
    public int getHardPoints(){
        return this.hard_points;
    }
    
    /**
     * Returns any special rules.
     * 
     * @return any special rules.
     */
    public String getSpecial(){
        return this.special;
    }
    
    /**
     * Returns a string representation of the item.
     * 
     * @return the toString of the Equipment Object.
     */
    
    public String toString(){
        return getName();
    }
    
    public abstract String getInfo();
}