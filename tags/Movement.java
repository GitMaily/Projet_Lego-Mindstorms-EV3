package codesource;

public class Movement {
	
	// Attribut
	private String couleur;
	ColorSensor couleurLigne=new ColorSensor(); // Déclare une variable de la class Color Sensor
	Motor motor=new Motor();                    // Déclare une variable de la class Motor
	
	// Constructeur
	public Movement() {
		couleur=couleurLigne.getColor();        // Assigne la chaine couleur a la valeur retournée dans setColor(), soit la couleur de la ligne
	}
	
	// Méthode
	public void Direction() {
		/*if(couleur=="JAUNE") {  // TEST : Lorsque le robot est sur une ligne jaune, il va tout droit
			motor.Straight();   // but : Vérifier que les class ColorSensor, Motor et Movement soient bien liées
		}*/
		System.out.println(couleur);
	}

	public static void main(String[] args) {
		Movement test=new Movement();
		test.Direction();
	}
}
