package codesource;

import lejos.utility.Delay;

public class Movement {
	
	Motor motor=new Motor();
	ColorSensor col=new ColorSensor();
	TouchSensor touch=new TouchSensor();
	Ramassage ram=new Ramassage();
	UltraSonicSensor ul=new UltraSonicSensor();
	private int NbPalet=1;  // CHOIX DU NOMBRE DU PALET SUR LE TERRAIN
	private boolean CampOrigine=true; // SI TRUE, ALORS RETOUR AU CAMP D'ORIGINE
	
	public void DirectionJaune() { 
		
		String couleur=col.getColor();
		boolean pal=false;
		
		while(NbPalet!=0) {
		
			while(!touch.estActif() && (couleur.equals("JAUNE") || couleur.equals("BLEU") || couleur.equals("NOIR")) && !couleur.equals("VERT")) {
				couleur=col.getColor();
				motor.Straight();
			}
			couleur=col.getColor();
			if(couleur.equals("VERT")) {
				//motor.Stop();
				motor.Left();
		 		//motor.Stop();
				motor.Straight();
				Delay.msDelay(2000); // TEMPS NECESSAIRE POUR ALLER JUSQU'AU PROCHAIN CROISEMENT
			}
			couleur=col.getColor();
			if(couleur.equals("VERT") && !touch.estActif()) {
				motor.Left();
			}
			couleur=col.getColor();
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
			couleur=col.getColor();
			if(!touch.estActif() && (couleur.equals("BLEU"))){
				motor.Right();
			}
			couleur=col.getColor();
			if(!touch.estActif() && (!couleur.equals("BLANC"))){
				motor.Straight();
			}
			couleur=col.getColor();
			if(touch.estActif() && (couleur.equals("JAUNE") || couleur.equals("ROUGE"))) {  
				motor.Stop();
				ram.carry();
				Delay.msDelay(700);
				if(CampOrigine) { // RETOUR CAMP ORIGINE
					motor.TurnAround();
					motor.Stop();
				}
				pal=true;
			}
			couleur=col.getColor();
			if(touch.estActif() && couleur.equals("NOIR")) {  // S'il détecte un palet, il s'arrete et ferme ses bras
				motor.Stop();
				ram.carry();
				Delay.msDelay(700);
				if(!CampOrigine) {  // 	RETOUR CAMP ADVERSE
					motor.TurnAround();
					motor.Stop();
				}
				pal=true;
			}
			couleur=col.getColor();
			if(touch.estActif() && (couleur.equals("VERT") || couleur.equals("BLEU"))) {  
				motor.Stop();
				ram.carry();
				Delay.msDelay(700);
				if(CampOrigine) {
					motor.Left();
					motor.Stop();
				}
				if(!CampOrigine) {
					motor.Right();
					motor.Stop();
				}
				pal=true;
			}
			couleur=col.getColor(); // SANS DOUTE INUTILE
			while(pal && !couleur.equals("BLANC")) {  // DEPOSER PALET 
				couleur=col.getColor();
				motor.Straight();
			}
			if(couleur.equals("BLANC")) {
				motor.Stop();
				ram.drop();
				motor.Back();
				motor.Stop();
				motor.TurnAround();
				NbPalet-=1;
			}
			couleur=col.getColor();
			if(couleur.equals("BLANC")) {
				motor.Stop();
				break;
			}
		}
	}
	
	public void EvitementDroit() {  // MODE COMPETITION
		double dist=ul.distance();
		while(dist>0.15) {
			dist=ul.distance();
			motor.Straight();
		}
		motor.Stop();
		dist=ul.distance();
		if(dist<=0.15) {
			motor.Back();
			motor.Stop();
			motor.Right();
			motor.Straight();
			Delay.msDelay(1800);
			motor.Left();
			motor.Straight();
			Delay.msDelay(3600);
			motor.Left();
			motor.Straight();
			Delay.msDelay(1400);
			motor.Right();
			motor.Straight();
			Delay.msDelay(400);
		}
	}
	
	public static void main(String[] args) {
		Movement test=new Movement();
		test.DirectionJaune();
	}
}