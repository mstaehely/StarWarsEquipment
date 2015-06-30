import java.util.*;

public class CombinedItem extends Equipment{
    private int base_damage, crit_value, soak_value, defense;
    private String skill, range, special, types;
    private ArrayList<Trait> traits;
    private ArrayList<Attachment> attachments;
    private boolean weapon, full_body;
    
    /**
     * Constructor for weapons with attachments added.
     * 
     * @param weapon the weapon being used for a basis.
     */
    public CombinedItem(Weapon weapon){
        super(weapon);
        this.weapon = true;
        this.soak_value= 0;
        this.defense = 0;
        this.full_body = false;
        setBaseDamage(weapon.getBaseDamage());
        setCriticalValue(weapon.getCritValue());
        setRange(weapon.getRange());
        setSkill(weapon.getSkill());
        setTypes(weapon.getTypes());
        sharedValues(weapon);
        setTraits(weapon.getTraits());
    }
    
    /**
     * Constructor for armor with attachments added.
     * 
     * @param armor the armor being used for a basis.
     */
    public CombinedItem(Armor armor){
        super(armor);
        this.weapon = false;
        this.base_damage = 0;
        this.crit_value = 0;
        setFullBody(armor.getFullBody());
        setSoakValue(armor.getSoakValue());
        setDefense(armor.getDefense());
        sharedValues(armor);
        traits = new ArrayList<Trait>(31);
        for(int i = 0; i < 31; i++){
            traits.add(new Trait(i, 0));
        }
    }
    
    /**
     * Sets shared values.
     * 
     * @param item being used.
     */
    public void sharedValues(Equipment equipment){
        setHardPoints(equipment.getHardPoints());
        setSpecial(equipment.getSpecial());
        attachments = new ArrayList<Attachment>();
    }
    
    private void setBaseDamage(int damage){
        this.base_damage = damage;
    }
    
    private void changeBaseDamage(int damage){
        this.base_damage += damage;
    }
    
    private void setCriticalValue(int crit_value){
        this.crit_value = crit_value;
    }
    
    private void changeCritValue(int crit_value){
        this.crit_value += crit_value;
        if(this.crit_value < 1){
            this.crit_value = 1;
        }
    }
    
    private void setRange(String range){
        this.range = range;
    }
    
    private void changeHardPoints(int hard_points){
        setHardPoints(this.getHardPoints() - hard_points);
    }
    
    private void setSkill(String skill){
        this.skill = skill;
    }
    
    private void setSpecial(String special){
        this.special = special;
    }
    
    private void setTraits(ArrayList<Trait> traits){
        this.traits = new ArrayList<Trait>();
        for(int i = 0; i < 31; i++){
            this.traits.add(new Trait(i, traits.get(i).getLevel()));
        }
    }
    
    private void setTypes(String types){
        this.types = types;
    }
    
    private void setSoakValue(int soak_value){
        this.soak_value = soak_value;
    }
    
    private void changeSoakValue(int soak_value){
        this.soak_value += soak_value;
    }
    
    private void setDefense(int defense){
        this.defense = defense;
    }
    
    private void setFullBody(boolean full_body){
        this.full_body = full_body;
    }
    
    private void addSpecial(Attachment attachment){
        this.special += attachment.getSpecial();
        if(!this.thisSpecial().equals("")){
            this.special += "\n";
        }
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
    
    public String thisSpecial(){
        return this.special;
    }
    
    /**
     * Adds the selected attachment to this item.
     * 
     * @param the attachment to be added.
     */
    public void addAttachment(Attachment attachment){
        boolean new_attachment = true;
        if(attachment != null){
            for(Attachment a: attachments){
                if(a.equals(attachment)){
                    new_attachment = false;
                }
            }
            if(new_attachment){
                if(this.getHardPoints() >= attachment.getHardPoints()){
                    attachments.add(attachment);
                    this.changeHardPoints(attachment.getHardPoints());
                    this.addSpecial(attachment);
                    ArrayList<Trait> new_traits = attachment.getTraits();
                    for(int i = 0; i < 28; i++){
                        if(traits.get(i).getLevel() == 0 && 
                        new_traits.get(i).getLevel() > 0){
                            traits.add(new_traits.get(i));
                        } else {
                            traits.get(i).setLevel(new_traits.get(i).getLevel());
                            if(traits.get(i).getLevel() < 0){
                                traits.get(i).setLevel(traits.get(i).getLevel()*-1);
                            }
                        }
                    }
                    if(new_traits.get(28).getLevel() > 0){
                        changeCritValue(new_traits.get(28).getLevel());
                    }
                    if(new_traits.get(29).getLevel() > 0){
                        changeBaseDamage(new_traits.get(29).getLevel());
                    }
                    if(new_traits.get(30).getLevel() != 0){
                        this.changeEncumbrance(new_traits.get(30).getLevel());
                    }
                    if(traits.get(24).getLevel() != 0){
                        if(weapon){
                            this.changeBaseDamage(1);
                        } else {
                            this.changeSoakValue(1);
                            this.changeEncumbrance(-1);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Returns a String representation of the combined item.
     * 
     * @return a String representation of the combined item.
     */
    public String getInfo(){
        String string = super.toString();
        if(weapon){
            string += "\nDamage Rating: " + getBaseDamage() + " Critical Value: ";
            string += getCritValue() + " Encumbrance: " + getEncumbrance();
            string += " Hard Points: " + getHardPoints();
            string += "\n" + getSkill() + " Range: " + getRange() + "\n";
            if(getRestricted()){
                string += "(R)";
            }
            string += getCost() + " credits " + "Rarity: " + getRarity();
            if(thisSpecial() != null){
                string += "\n" + thisSpecial();
            }
            for(int i = 0; i < getTraits().size(); i++){
                Trait trait = getTraits().get(i);
                if(trait.getLevel() != 0){
                    string += trait;
                }
            }
        } else {
            string += "\nSoak Value: " + getSoakValue() + " Defense: " + getDefense();
            string += " Encumbrance: " + getEncumbrance() + " Hard Points: ";
            string += getHardPoints() + "\n";
            if(getRestricted()){
                string += "(R)";
            }
            string += getCost() + " credits " + "Rarity: " + getRarity();
            if(getFullBody()){
                string += "\nFull body armor\n";
            }
            if(thisSpecial() != null){
                string += thisSpecial();
            }
            for(int i = 0; i < getTraits().size(); i++){
                Trait trait = getTraits().get(i);
                if(trait.getLevel() != 0){
                    string += trait;
                }
            }
        }
        return string;
    }
}