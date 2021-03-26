package codesource;

import lejos.utility.Delay;

public class Movement {
	
	Motor motor=new Motor();
	ColorSensor col=new ColorSensor();
	TouchSensor touch=new TouchSensor();
	Ramassage ram=new Ramassage();
	UltraSonicSensor us=new UltraSonicSensor();
	private int NbPalet=1;  // Nombre de palet sur le terrain
	
	public void DirectionJaune() { // Retour au camp d'origine
		
		col.NewColor();
		String couleur=col.getColor();
		boolean pal=false;
		
		while(NbPalet!=0) {
		
			while(!touch.estActif() && (couleur.equals("JAUNE") || couleur.equals("BLEU") || couleur.equals("NOIR")) && !couleur.equals("VERT")) {
				couleur=col.getColor();
				motor.Straight();
			}
			if(couleur.equals("VERT")) {
				//motor.Stop();
				motor.Left();
				//motor.Stop();
				motor.Straight();
				Delay.msDelay(2000); // Mettre le temps nécessaire pour aller jusqu'au croisement vert/noir
			}
			if(couleur.equals("VERT") && !touch.estActif()) {
				motor.Left();
			}
			while(couleur.equals("NOIR") && !couleur.equals("BLEU") && !touch.estActif()) {
				couleur=col.getColor();
				motor.Straight();
			}
			if(!touch.estActif() && (couleur.equals("BLEU"))){
				motor.Stop();
				motor.Right();
				motor.Straight();
				Delay.msDelay(2000);
			}
			if(!touch.estActif() && (couleur.equals("BLEU"))){
				motor.Right();
			}
			if(touch.estActif() && (couleur.equals("JAUNE") || couleur.equals("ROUGE"))) {  // S'il détecte un palet, il s'arrete, ferme ses bras et se retourne
				motor.Stop();
				ram.carry();
				Delay.msDelay(700);
				motor.TurnAround();
				motor.Stop();
				pal=true;
			}
			if(touch.estActif() && couleur.equals("NOIR")) {  // S'il détecte un palet, il s'arrete et ferme ses bras
				motor.Stop();
				ram.carry();
				Delay.msDelay(700);
				pal=true;
			}
			if(touch.estActif() && (couleur.equals("VERT") || couleur.equals("BLEU"))) {  
				motor.Stop();
				ram.carry();
				Delay.msDelay(700);
				motor.Left();
				motor.Stop();
				pal=true;
			}
			while(pal && !couleur.equals("BLANC")) {
				couleur=col.getColor();
				motor.Straight();
				if(couleur.equals("BLANC")) {
					motor.Stop();
					ram.drop();
					motor.Back();
					motor.Stop();
					motor.TurnAround();
					NbPalet-=1;
					break;
				}
			}
			couleur=col.getColor();
			if(couleur.equals("BLANC")) {
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		Movement test=new Movement();
		test.DirectionJaune();
	}
}