/**
 * Used to create Trait objects in the SW equipment program.
 * 
 * @author Matthew Staehely
 * @version 1
 */
public class Trait{
    private int level;
    private String name;
    public Trait(int trait, int level){
        switch(trait){
            case 0: this.name = "Accurate";
                    this.level = level;
                    break;
            case 1: this.name = "Auto-fire";
                    this.level = level;
                    break;
            case 2: this.name = "Breach";
                    this.level = level;
                    break;
            case 3: this.name = "Blast";
                    this.level = level;
                    break;
            case 4: this.name = "Burn";
                    this.level = level;
                    break;
            case 5: this.name = "Concussive";
                    this.level = level;
                    break;
            case 6: this.name = "Cumbersome";
                    this.level = level;
                    break;
            case 7: this.name = "Defensive";
                    this.level = level;
                    break;
            case 8: this.name = "Deflection";
                    this.level = level;
                    break;
            case 9: this.name = "Disorient";
                     this.level = level;
                     break;
            case 10: this.name = "Ensnare";
                     this.level = level;
                     break;
            case 11: this.name = "Guided";
                     this.level = level;
                     break;
            case 12: this.name = "Knockdown";
                     this.level = level;
                     break;
            case 13: this.name = "Inaccurate";
                     this.level = level;
                     break;
            case 14: this.name = "Inferior";
                     this.level = level;
                     break;
            case 15: this.name = "Ion";
                     this.level = level;
                     break;
            case 16: this.name = "Limited Ammo";
                     this.level = level;
                     break;
            case 17: this.name = "Linked";
                     this.level = level;
                     break;
            case 18: this.name = "Pierce";
                     this.level = level;
                     break;
            case 19: this.name = "Prepare";
                     this.level = level;
                     break;
            case 20: this.name = "Slow-Firing";
                     this.level = level;
                     break;
            case 21: this.name = "Stun";
                     this.level = level;
                     break;
            case 22: this.name = "Stun damage";
                     this.level = level;
                     break;
            case 23: this.name = "Sunder";
                     this.level = level;
                     break;
            case 24: this.name = "Superior";
                     this.level = level;
                     break;
            case 25: this.name = "Tractor";
                     this.level = level;
                     break;
            case 26: this.name = "Vicious";
                     this.level = level;
                     break;
            case 27: this.name = "Cortosis";
                     this.level = level;
                     break;
            case 28: this.name = "Critical";
                     this.level = level;
                     break;
            case 29: this.name = "Damage";
                     this.level = level;
                     break;
            case 30: this.name = "Encumbrance";
                     this.level = level;
                     break;
        }
    }
    
    
    /**
     * Returns the level of this trait.
     * 
     * @return the level of this trait.
     */
    public int getLevel(){
        return this.level;
    }
    
    /**
     * Changes the level of a trait.
     */
    public void setLevel(int level){
        this.level += level;
    }
    
    /**
     * Returns a String representation of the Trait.
     */
    public String toString(){
        String string = this.name;
        if(!string.equals("Auto-fire") && !string.equals("Cortosis") &&
           !string.equals("Superior")){
            string += " " + level;
            string += "\n";
        }
        return string;
    }
}