package codesource;

public class Movement extends Motor{
	
	// Attribut
	String couleur;
	ColorSensor couleurLigne=new ColorSensor(); // D�clare une variable de la class Color Sensor
	Motor motor=new Motor();                    // D�clare une variable de la class Motor
	
	// Constructeur
	public Movement() {
		couleur=couleurLigne.setColor();        // Assigne la chaine couleur a la valeur retourn�e dans setColor(), soit la couleur de la ligne
	}
	
	// M�thode
	public void Direction() {
		if(couleur=="JAUNE") {  // TEST : Lorsque le robot est sur une ligne jaune, il va tout droit
			motor.Straight();   // but : V�rifier que les class ColorSensor, Motor et Movement soient bien li�es
		}
	}

	public static void main(String[] args) {
		new Movement();
	}
}
