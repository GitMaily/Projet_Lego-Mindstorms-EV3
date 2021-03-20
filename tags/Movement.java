package codesource;

public class Movement {
	
	// Attributs
	ColorSensor couleurLigne=new ColorSensor(); // Déclare une variable de la class Color Sensor
	Motor motor=new Motor();                    // Déclare une variable de la class Motor
	TouchSensor touch=new TouchSensor();
	String couleur=couleurLigne.getColor(); // Assigne la couleur de la ligne de la class ColorSensor à couleur
	boolean pres=touch.estActif();
	
	
	// Méthode
	public void DirectionJaune() {
		
		if(pres && couleur.equals("NOIR")) {
			motor.TurnAround();
			while(!couleur.equals("BLANC")) {
				motor.Straight(3000);
			}
		}
		
		while(couleur.equals("ROUGE")) {        // Tant que la couleur est rouge, le robot va tout droit 
			motor.Straight(3000);
			if(couleur.equals("NOIR")) {
				motor.Right();
				break;
			}	
		}
		while(couleur.equals("NOIR")) {
			motor.Straight(1500);
		}
	}

	public static void main(String[] args) {
		ColorSensor col=new ColorSensor();
		Movement mov=new Movement();
		col.NewColor();                         // Appelle la méthode NewColor() pour obtenir la couleur
		mov.DirectionJaune();
	}
}
