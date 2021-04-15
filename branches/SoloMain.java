package codesource;

import lejos.utility.Delay;

public class SoloMain {

	public static void main(String[] args) {
		
		Collecting	collect = new Collecting();
		Motor motor = new Motor();
		ColorSensor colorsensor = new ColorSensor();
		TouchSensor touchsensor = new TouchSensor();
		Ram	ramass	= new Ram();
		Movement mov = new Movement();
		
		int nbf = 3;
		int nbs	= 3;
		int nbt	= 2;
		
		String firstColor ;
		String lastColor;
		
		String couleur;
		
		while(!touchsensor.estActif()) {
			motor.Straight();
		}
		collect.carry();
		couleur = colorsensor.getColor();
		while(couleur!="VERT" || couleur!="BLEU") {
			motor.Straight();
		}
		motor.Right(); 
		lastColor = colorsensor.getColor();
		motor.Straight();
		Delay.msDelay(700);
		
		mov.retour(3);
		
		if(lastColor =="VERT") {
			firstColor ="BLEU";
		}
		else {
			firstColor = "VERT";
		}
		
		mov.lookFor(firstColor);
		ramass.ramassDroit(firstColor,nbf);
		if(nbf>0){
		ramass.ramassGauch(firstColor,nbf);
		}
		
		mov.lookFor("Noir");
		ramass.ramassDroit("Noir",nbs);
		if(nbs>0) {
			ramass.ramassGauch("Noir",nbs);
		}
		
		mov.lookFor(lastColor);
		ramass.ramassDroit(lastColor,nbt);
		if(nbt>0) {
			ramass.ramassGauch(lastColor,nbt);
		}
		motor.Stop();
		
		
		
		
		
		
		// TODO Auto-generated method stub

	}
}

