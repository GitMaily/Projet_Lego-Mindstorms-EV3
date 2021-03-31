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
	
	public void Midel () {
		
		String couleur = col.getColor();
		int r = 1;   // un indice sur direction du robot , pour pouvoir aller au camp adverse 		
		while(!touch.estActif()) {
			
		
				while(couleur.equals("Noir")) {                  //avancer jusqu'à  la ligne d'une autre couleur 
						motor.Straight();							
					
							}
						if(couleur.equals("Vert")) {						// * si la ligne qu'il croise et vert , il continus jusqu'a la ligne bleu						
								do {
										motor.Straight(); 
									}
								while(!couleur.equals("Bleu"));			
								
								motor.Left();                         // le robot tourne à gauche	
								r = 2;								// on ajuste l'indice de retour
								
								while(couleur.equals("Bleu")) {				//  il continus jusqu'à la fin de la ligne bleu
										motor.Straight();
											}			
								motor.Left(1125);							//   il tourne à gauche de 135°			
								while(!couleur.equals("Rouge")) {			//   il cherche la ligne rouge
										motor.Straight();
												}			
								motor.Right(375);							// quand il la trouve il ce tourne de 45° pour la suivre	
								r = 4;
								
								while(!couleur.equals("Vert")) {				// le robot avance jusqu'à la ligne vert
									motor.Straight();
								}			
								motor.Left();								// il se tourne pour suivre la ligne vert 
								r = 3;
								while(couleur.equals("Vert")) {				// il avance sur la ligne vert jusqu'au bout 
									motor.Straight();
								}
								motor.Left(1125);							// il se retoune de 135°			
								while(!couleur.equals("Jaune")) {			// le robot cherche la ligne jaune 
									motor.Staright();
								}
								
								motor.Right(375);                             // il se tourne pour la suivre	
								r = 1 ;
								
								while(!couleur.equals("Blanc")) {			// le robot suit la ligne jaune jusqu'à la ligne blanche 
									motor.Straight();
								}			
								motor.stop();								// le robot s'arrete quand il trouve la ligne blache
						}
						else {
							do {
								motor.Straight(); 
							}
							while(!couleur.equals("Vert"));	
							
							motor.Left();
							r = 2;
							
							while(couleur.equals("Vert")) {
								motor.Straight();
							}				
							motor.Left(1125);				
							while(!couleur.equals("Jaune")) {
								motor.Straight();
							}	
							
							motor.Right(375);
							r = 4;
							
							while(!couleur.equals("Bleu")) {
								motor.Straight();
							}	
							
							motor.Left();
							r = 3;
							
							while(couleur.equals("Bleu")) {
							motor.Straight();
							}
							
							motor.Left(1125);	
							
							while(!couleur.equals("Rouge")) {
								motor.Staright();
							}
							
							motor.Right(375);
							r = 1;
							
							while(!couleur.equals("Blanc")) {
								motor.Straight();
							}			
							motor.stop();	
				}
		}
		ram.carry();           // ramasser la palet 			
		retour(r);
	}
	
	public void retour(int r) {
		String couleur = col.getColor();
		switch (r) {
		
		case 1 : 
			while(!couleur.equals("Blanc")) {
				motor.straight();
			}
			motor.stop();
			ram.drop();			
		case 2 :                                                 // 2 :il faut tourner à droite
			motor.Left();
			while(!couleur.equals("Blanc")) {
				motor.straight();
			}
			motor.stop();
			ram.drop();			
		case 3 :												// il faut tourner à gauche
			motor.Right();
			while(!couleur.equals("Blanc")) {
				motor.straight();
			}
			motor.stop();
			ram.drop();			
		case 4 :											 // il faut faire demi-tour
			motor.TournAround();
			while(!couleur.equals("Blanc")) {
				motor.straight();
			}
			motor.stop();
			ram.drop();
			
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