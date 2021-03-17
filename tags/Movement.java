package codesource;

public class Movement {
	
	// Attributs
	ColorSensor couleurLigne=new ColorSensor(); // Déclare une variable de la class Color Sensor
	Motor motor=new Motor();                    // Déclare une variable de la class Motor
	
	// Méthode
	public void Direction() {
		String couleur=couleurLigne.getColor(); // Assigne la couleur de la ligne de la class ColorSensor à couleur
		while(couleur.equals("ROUGE")) {        // Tant que la couleur est rouge, le robot va tout droit 
			motor.Straight(3000);
		}
		if(couleur.equals("BLEU")) {
			motor.TurnAround();
		}
	}

	public static void main(String[] args) {
		ColorSensor col=new ColorSensor();
		Movement mov=new Movement();
		col.NewColor();                         // Appelle la méthode NewColor() pour obtenir la couleur
		mov.Direction();
	}
}
