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
					ramassDroit(color,nbp);
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
					ramassGauch(color,nbp);
				}
				return 0;
			}
			else return -1;
		
	}
	

	public void ramassLigne(String color) {
		int nbp =3;
		while( nbp>0) {
			mov.lookFor(1);
			mov.retour(3);
			nbp--;
		}
	}
	public void ramassEntier(String color1,String color2,String a) {
		ramassLigne(color1);
		System.out.println("fin ligne 1");

		motor.Straight();
		Delay.msDelay(400);
		
		if(a == "GAUCHE" ) {
			
			motor.Right();
			mov.lookFor("NOIR");
			motor.Left();
			mov.lookFor("NOIR");
			ramassLigne("NOIR");
			
			System.out.println("fin ligne2 ");

			motor.Straight();
			Delay.msDelay(400);
			
			motor.Right();
			mov.lookFor(color2);
			
			motor.Left();
			mov.lookFor(color2);
			ramassLigne(color2);
			
			System.out.println("fin");
		}
		else {
			motor.Left();
			mov.lookFor("NOIR");
			motor.Right();
			mov.lookFor("NOIR");
			ramassLigne("NOIR");
			
			System.out.println("fin ligne2 ");

			motor.Straight();
			Delay.msDelay(400);
			
			motor.Left();
			mov.lookFor(color2);
			
			motor.Right();
			mov.lookFor(color2);
			ramassLigne(color2);
			
			System.out.println("fin");
			
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
