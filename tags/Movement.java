package codesource;

public class Movement {
	
	// Attributs
	ColorSensor couleurLigne=new ColorSensor(); // Déclare une variable de la class Color Sensor
	Motor motor=new Motor();                    // Déclare une variable de la class Motor
	TouchSensor touch=new TouchSensor();
	String couleur=couleurLigne.getColor(); // Assigne la couleur de la ligne de la class ColorSensor à couleur
	boolean pres=touch.estActif();
	
	
	// Méthode
	public void Direction() {
		
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

	public void DirectionJaune() {
		int pal=0;
		while(pal==0) {
			// Retour dans le camp d'origine
			if(pres && ((couleur.equals("JAUNE")) || (couleur.equals("ROUGE")))) {
				motor.TurnAround();
				while(!couleur.equals("BLANC")) {
					motor.Straight(3000);
					break;}}
			
			if(pres && couleur.equals("NOIR")) {
				motor.Straight(5000);
				while(!couleur.equals("BLANC")) {
					motor.Straight(3000);
					break;}}
			
			while(couleur.equals("JAUNE")) {        // Tant que la couleur est jaune, le robot va tout droit 
				motor.Straight(3000);
				if(couleur.equals("VERT")) {
					motor.Left();
					motor.Straight(3000);
					motor.Left();
					break;}}
			
			while(couleur.equals("NOIR")) {        // Tant que la couleur est noire, le robot va tout droit 
				motor.Straight(3000);
				if(couleur.equals("BLEU")) {
					motor.Right();
					motor.Straight(3000);
					motor.Right();
					break;}}
			
			while(couleur.equals("ROUGE")) {        // Tant que la couleur est rouge, le robot va tout droit 
				motor.Straight(6000);
				break;}
			
			if(couleur.equals("BLANC")) {
				pal=1;
			}
	
		}
		motor.Stop();
	}		
	
	public static void main(String[] args) {
		ColorSensor col=new ColorSensor();
		Movement mov=new Movement();
		col.NewColor();                         // Appelle la méthode NewColor() pour obtenir la couleur
		mov.DirectionJaune();
	}
}
