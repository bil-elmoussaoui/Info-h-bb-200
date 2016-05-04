package Model;

public class Trap extends Tile{
    private double damage;
    public boolean animationStopped;

    public Trap(int positionX, int positionY){
        super(positionX, positionY);
        setIsDangerous(true);
    }

    public void setDamage(double damage){
        this.damage = damage;
    }

    public double getDamage(){
        return damage;
    }

    public void stopAnimation(){
        this.animationStopped = true;
    }

    public void attack(Person person) {
        double armorDiff = person.getArmor() - getDamage();
        if(armorDiff > 0){
            person.setArmor(armorDiff);
        } else {
            person.setArmor(0);
            double healthDiff = person.getHealth() - Math.abs(armorDiff);
            if(healthDiff > 0){
                person.setHealth(healthDiff);
            } else {
                person.setHealth(0);
            }
        }
    }

}
