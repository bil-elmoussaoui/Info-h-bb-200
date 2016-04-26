package Model;
public class Prisonner extends Person{
	
	public Prisonner(int positionX, int positionY) {
        super(positionX, positionY, 12);
	}

	public Boolean getIsAlive(){
		return (getHealth() <= 0);
	}
}
