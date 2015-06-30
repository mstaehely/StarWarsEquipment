import java.util.*;

/**
 * Provides a framework for weapons in the Star Wars game.
 * 
 * @author Matthew Staehely
 * @version 1
 */
public class Weapon extends Equipment{
    private int base_damage, crit_value;
    private String skill, range, types;
    private ArrayList<Trait> traits;
    
    /**
     * Constructor for objects of class Weapon.
     * 
     * @param reader the Scanner used to input weapon data.
     */
    public Weapon(Scanner reader){
        super(reader);
        setBaseDamage(reader.nextInt());
        setCritValue(reader.nextInt());
        setRange(reader.next());
        setSkill(reader.next());
        setTraits(reader);
        setTypes(reader.next());
    }
    
    private void setBaseDamage(int damage){
        this.base_damage = damage;
    }
    
    private void setCritValue(int crit){
        this.crit_value = crit;
    }
    
    private void setRange(String range){
        this.range = range;
    }
    
    private void setSkill(String skill){
        this.skill = skill;
    }
    
    private void setTraits(Scanner reader){
        int trait_level;
        traits = new ArrayList<Trait>();
        for(int i = 0; i < 31; i++){
            trait_level = reader.nextInt();
            traits.add(new Trait(i, trait_level));
        }
    }
    
    private void setTypes(String types){
        this.types = types;
    }
    
    /**
     * Returns the weapon's base damage.
     * 
     * @return the weapon's base damage.
     */
    public int getBaseDamage(){
        return base_damage;
    }
    
    /**
     * Returns the weapon's critical hit value.
     * 
     * @return the weapon's critical hit value.
     */
    public int getCritValue(){
        return crit_value;
    }
    
    /**
     * Returns the weapon's maximum range.
     * 
     * @return the weapon's maximum range.
     */
    public String getRange(){
        return range;
    }
    
    /**
     * Returns the weapon's required skill.
     * 
     * @return the weapon's required skill.
     */
    public String getSkill(){
        return skill;
    }
    
    /**
     * Returns an ArrayList of the weapon's traits.
     * 
     * @return an ArrayList of the weapon's active and passive traits.
     */
    public ArrayList<Trait> getTraits(){
        return traits;
    }
    
    /**
     * Returns a String of the item's types for matching to modules.
     * 
     * @return a String of the item's types.
     */
    public String getTypes(){
        return types;
    }
    
    public String getInfo(){
        String string = super.toString();
        string += "\nDamage Rating: " + getBaseDamage() + " Critical Value: ";
        string += getCritValue() + " Encumbrance: " + getEncumbrance();
        string += " Hard Points: " + getHardPoints();
        string += "\n" + getSkill() + " Range: " + getRange() + "\n";
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