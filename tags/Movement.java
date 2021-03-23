package codesource;

public class Movement {
	
	Motor motor=new Motor();
	ColorSensor col=new ColorSensor();
	TouchSensor touch=new TouchSensor();
	Ramassage ram=new Ramassage();
	UltraSonicSensor us=new UltraSonicSensor();
	
	public void Direction() {
		col.NewColor();
		String couleur=col.getColor();
		
		while(!touch.estActif() && couleur.equals("JAUNE")) { // le robot avance tant que la couleur de la ligne est jaune et qu'il n'y a pas de palet
			motor.Straight();
		}
		while(touch.estActif() && couleur.equals("JAUNE")) {  // S'il détecte un palet, il s'arrete, ferme puis ouvre ses bras
			motor.Stop();
			ram.carry();
			ram.drop();
		}
	}
	
	public static void main(String[] args) {
		Movement test=new Movement();
		test.Direction();
	}
}
