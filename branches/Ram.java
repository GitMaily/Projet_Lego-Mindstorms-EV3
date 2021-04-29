package codesource;

import lejos.utility.Delay;


public class Ram {
	
	static Motor motor=new Motor();
	static ColorSensor colorsensor=new ColorSensor();
	TouchSensor touch=new TouchSensor();
	Collecting ram=new Collecting();
	Movement mov = new Movement();

	public int ramassDroit(String color,int nbp) {
		String couleur = colorsensor.getColor();
		
		
			mov.lookFor(color);
			motor.Right();
			while((couleur==color) && !touch.estActif()) {
				motor.Straight();
				couleur = colorsensor.getColor();
			}
			motor.Stop();
			if(couleur!=color) {
				return nbp;
			}
			else if(touch.estActif()) {
				ram.carry();
				nbp--;
				mov.retour(2);
				
				if(nbp>0) {
					
					mov.lookFor(color);
					motor.Right();
					while((couleur==color) && !touch.estActif()) {
						motor.Straight();
						couleur = colorsensor.getColor();
					}
					motor.Stop();
					if(couleur!=color) {
						return nbp;
					}
					else if(touch.estActif()) {
						ram.carry();
						nbp--;
						mov.retour(2);
						if(nbp>0) {
							mov.lookFor(color);
							motor.Right();
							while((couleur==color) && !touch.estActif()) {
								motor.Straight();
								couleur = colorsensor.getColor();
							}
							motor.Stop();
							if(couleur!=color) {
								return nbp;
							}
							else if(touch.estActif()) {
								ram.carry();
								nbp--;
								mov.retour(2);
								if(nbp>0) {
									mov.lookFor(color);
									motor.Right();
									while((couleur==color) && !touch.estActif()) {
										motor.Straight();
										couleur = colorsensor.getColor();
									}
									motor.Stop();
									if(couleur!=color) {
										return nbp;
									}
									else if(touch.estActif()) {
										ram.carry();
										nbp--;
										mov.retour(2);

										return 0;
									}
									else return -1;
								}
								return 0;
							}
							else return -1;
						}
						return 0;
					}
					else return -1;
				}
				return 0;
			}
			else return -1;
		
	}
	public int ramassGauch(String color,int nbp) {
		String couleur = colorsensor.getColor();
		
		
			mov.lookFor(color);
			motor.Left();
			while((couleur==color) && !touch.estActif()) {
				motor.Straight();
				couleur = colorsensor.getColor();
			}
			motor.Stop();
			if(couleur!=color) {
				return nbp;
			}
			else if(touch.estActif()) {
				ram.carry();
				nbp--;
				mov.retour(3);
				if(nbp>0) {
					mov.lookFor(color);
					motor.Left();
					while((couleur==color) && !touch.estActif()) {
						motor.Straight();
						couleur = colorsensor.getColor();
					}
					motor.Stop();
					if(couleur!=color) {
						return nbp;
					}
					else if(touch.estActif()) {
						ram.carry();
						nbp--;
						mov.retour(3);
						if(nbp>0) {
							mov.lookFor(color);
							motor.Left();
							while((couleur==color) && !touch.estActif()) {
								motor.Straight();
								couleur = colorsensor.getColor();
							}
							motor.Stop();
							if(couleur!=color) {
								return nbp;
							}
							else if(touch.estActif()) {
								ram.carry();
								nbp--;
								mov.retour(3);
								if(nbp>0) {
									mov.lookFor(color);
									motor.Left();
									while((couleur==color) && !touch.estActif()) {
										motor.Straight();
										couleur = colorsensor.getColor();
									}
									motor.Stop();
									if(couleur!=color) {
										return nbp;
									}
									else if(touch.estActif()) {
										ram.carry();
										nbp--;
										mov.retour(3);
										
										return 0;
									}
									else return -1;
								}
								return 0;
							}
							else return -1;
						}
						return 0;
					}
					else return -1;
				}
				return 0;
			}
			else return -1;
		
	}
	

	public void ramassLigne(String color,int nbp) {
		while( nbp>0) {
			mov.lookForP(color);
			mov.retour(3);
			nbp--;
		}
	}
	
	/*la fonction ramassTotale prend en argument la couleur de gauche (colorLeft) la position de depart (G :gauche ,M :millieu,D:droit)
	le robot alors parcour les ligne une par une et ramasse les palets et les depose derriere la ligne de depart
	*/
	public void ramassTotale(String colorLeft,char position,int nbpG,int nbpM,int nbpD) {
		
		if(colorLeft =="JAUNE") {
			String colorRight = "ROUGE";
		}
		else {
			String colorRight = "JAUNE";
		}
		
		switch (position) {
		
		case "G":
			// on ramasse la ligne gauche
			ramassLigne(colorLeft,nbpG);			//le robot
			System.out.println("fin ligne 1");
			
			motor.Straight();
			Delay.msDelay(400);
			motor.Stop();
			
			//le robot ramasse la ligne noir du millieu
			motor.Right();        			//on cherche la ligne noir
			mov.lookFor("NOIR");
			motor.Left();					// il se place dessus.
			mov.lookFor("NOIR");
			
			ramassLigne("NOIR",nbpM);			// ramasse les palets
			
			System.out.println("fin ligne2 ");
			
			motor.Straight();
			Delay.msDelay(400);
			motor.Stop();
			
			//Le robot ramasse la troisieme ligne
			
			motor.Right();
			mov.lookFor(colorRight);
			
			motor.Left();
			mov.lookFor(colorRight);
			ramassLigne(colorRight,nbpD);
			
			System.out.println("fin");
			
		case "M":
			// le robot ramasse les palets sur la ligne noir
			ramasseLigne("Noir",nbpM);
			System.out.println("fin ligne 1");

			//la deusieme ligne serra la ligne gauche
			motor.Straight();
			Delay.msDelay(400);
			motor.Stop();
			
			motor.Left();
			mov.lookFor(colorLeft);  //il cherche la la ligne de couleur colorleft 
			motor.Right();				//il se positionne dessus
			mov.lookFor(colorLeft);
			
			
			ramassLigne(colorLeft,nbpG);	//ramasse le les palets 
			
			//la troisieme ligne est la ligne de droite.
			
			motor.Straight();
			Delay.msDelay(400);
			motor.Stop();
			
			//Le robot ramasse la troisieme ligne
			
			motor.Right();
			mov.lookFor(colorRight);
			
			motor.Left();
			mov.lookFor(colorRight);
			
			ramassLigne(colorRight,nbpD);  // ramasse les palets
			
			System.out.println("fin");
			
		case "D":
			// le robot commence par la ligne droite
			ramassLigne(colorRight,nbpD);
			System.out.println("fin ligne 1");
			
			// il passe a la ligne noir a ça gauche
			
			motor.Straight();
			Delay.msDelay(400);
			motor.Stop();
			
			motor.Left();
			mov.lookFor("NOIR");  //il cherche la la ligne noir
			motor.Right();				//il se positionne dessus
			mov.lookFor("NOIR");
			
			
			ramassLigne("NOIR",nbpM);	//ramasse le les palets 
			System.out.println("fin ligne noir");

			//troisieme Ligne
			
			motor.Straight();
			Delay.msDelay(400);
			motor.Stop();
			
			motor.Left();
			mov.lookFor(colorLeft);  //il cherche la la ligne de couleur colorleft 
			motor.Right();				//il se positionne dessus
			mov.lookFor(colorLeft);
			
			
			ramassLigne(colorLeft,nbpG);	//ramasse le les palets 
			System.out.println("fin ");
		
		
		}
		
	}

	public void noir() {
		
		
		String couleur = colorsensor.getColor();
		while(true) {
			while(!touch.estActif()) {						//tant qu'on a pas de palet
				
			couleur=mov.lookFor();	
															//on cherche la premiere ligne qui croise
			if(couleur =="VERT") {
				mov.lookFor("BLEU");
	
			motor.Stop();
			motor.Left();
			mov.lookFor("BLEU");
			couleur = colorsensor.getColor();

			while(couleur =="BLEU") {
				motor.Straight();
			}
			motor.Stop();	
			motor.Left(1125);	
			mov.lookFor("JAUNE");	
			motor.Stop();
			motor.Right();
			mov.lookFor("JAUNE");
			couleur = colorsensor.getColor();
			mov.lookFor("VERT");
			motor.Stop();
			motor.Left();
			mov.lookFor("VERT");
			couleur = colorsensor.getColor();
			
			while(couleur =="VERT") {
				motor.Straight();
			}
			motor.Stop();	
			motor.Left(1125);
			mov.lookFor("ROUGE");	
			motor.Stop();
			motor.Right();
			mov.lookFor("ROUGE");
			mov.lookFor("BLANC");
			break;

			}
			else if(couleur =="BLEU") {
				mov.lookFor("VERT");
	
			motor.Stop();
			motor.Left();
			mov.lookFor("VERT");
			couleur = colorsensor.getColor();

			while(couleur =="VERT") {
				motor.Straight();
			}
			motor.Stop();	
			motor.Left(1125);	
			mov.lookFor("ROUGE");	
			motor.Stop();
			motor.Right();
			mov.lookFor("ROUGE");
			couleur = colorsensor.getColor();
			mov.lookFor("BLEU");
			motor.Stop();
			motor.Left();
			mov.lookFor("BLEU");
			couleur = colorsensor.getColor();
			
			while(couleur =="BLEU") {
				motor.Straight();
			}
			motor.Stop();	
			motor.Left(1125);
			mov.lookFor("JAUNE");	
			motor.Stop();
			motor.Right();
			mov.lookFor("JAUNE");
			mov.lookFor("BLANC");
			break;

			}
			else {
				System.out.println("error");
			}
		  }
			
		}
	}
	public static void main(String[] args) {
		Ram ramas = new Ram();
		ramas.noir();
		
	}
}