package codesource;	

import lejos.utility.Delay;

public class Movement {

	

	static Motor motor=new Motor();

	static ColorSensor couleur=new ColorSensor();

	TouchSensor touch=new TouchSensor();

	Collecting ram=new Collecting();

	UltraSonicSensor ul=new UltraSonicSensor();

	

	private int NbPalet=2;  // CHOIX DU NOMBRE DU PALET SUR LE TERRAIN

	private boolean CampAdverse=true; // SI TRUE, ALORS RETOUR AU CAMP D'ORIGINE

	

	public void line(String coul) { //assigner coul
        String couleurLine=couleur.getColor();
        while(couleurLine.equals(coul) || couleurLine.equals("GRIS")) { //tant que coul ou gris
            while(couleurLine.equals(coul)) { //Tant que le robot est sur la ligne coul
                motor.setMotorGSpeed(400);//Le robot d?rive un peu plus ? droite
                motor.setMotorDSpeed(350);
                motor.Straight();
                couleurLine=couleur.getColor();

            while(couleurLine.equals("GRIS")) { //Tant que le robot voit du GRIS (hors de la ligne)
                motor.setMotorGSpeed(350);//Le robot d?rive un peu plus ? gauche
                motor.setMotorDSpeed(400);
                motor.Straight();
                couleurLine=couleur.getColor();
                }}
            couleurLine=couleur.getColor();
        }}

	public void DirectionRougeSud() {  

		String coul=couleur.getColor();
		boolean pal=false;
		
		while(!touch.estActif() && !coul.equals("VERT")) {
			line(coul);
			coul=couleur.getColor(); // A EFFECTUER SEULEMENT AU DEBUT, POUR EVITER ERREUR APRES
		}
		
		while(NbPalet!=0) {
			coul=couleur.getColor();
			if(coul.equals("VERT") && !touch.estActif()) {
				motor.Right(30);
				coul=couleur.getColor();
				while(!coul.equals("VERT")){
					motor.Right(10);
					coul=couleur.getColor();
				}
				motor.Right(70);
			}
			while(coul.equals("VERT") || coul.equals("GRIS")) {
				line(coul);
				coul=couleur.getColor();
			}
			coul=couleur.getColor();
			if(coul.equals("NOIR") && !touch.estActif()) {
				motor.Right(30);
				coul=couleur.getColor();
				while(!coul.equals("NOIR")){
					motor.Right(10);
					coul=couleur.getColor();
				}
				motor.Right(30);
			}
			coul=couleur.getColor();
			while((coul.equals("NOIR") || coul.equals("GRIS")) && !touch.estActif()) {
				line(coul);
				coul=couleur.getColor();
				if(coul.equals("BLEU")) {
					motor.Stop();
				}
			}
			coul=couleur.getColor();
			if(!touch.estActif() && (!coul.equals("NOIR") || coul.equals("GRIS") || coul.equals("BLEU"))){
				motor.Stop();
				motor.Left();
				line(coul);
				Delay.msDelay(2000);
			}
			coul=couleur.getColor();
			if(!touch.estActif() && (coul.equals("BLEU")|| coul.equals("GRIS"))){
				motor.Left(30);
				coul=couleur.getColor();
				while(!coul.equals("VERT")){
					motor.Left(10);
					coul=couleur.getColor();
				}
				motor.Left(70);
			}
			coul=couleur.getColor();
			while(!touch.estActif() && (!coul.equals("BLANC"))){
				line(coul);
				coul=couleur.getColor();
			}
			coul=couleur.getColor();
			if(touch.estActif() && (coul.equals("JAUNE") || coul.equals("ROUGE"))) {  
				motor.Stop();
				ram.carry();
				//Delay.msDelay(700);
				if(!CampAdverse) { // RETOUR CAMP ORIGINE
					motor.TurnAround();
					motor.Stop();
				}
				if(CampAdverse && NbPalet>1) {	// SE DECALE DE LA LIGNE POUR EVITER AUTRES PALETS
					motor.Left(400); 
					motor.Straight();
					Delay.msDelay(1500);
					motor.Right(400);
				}
				pal=true;
			}
			coul=couleur.getColor();
			if(touch.estActif() && coul.equals("NOIR")) {  // S'il détecte un palet, il s'arrete et ferme ses bras
				motor.Stop();
				ram.carry();
				Delay.msDelay(700);
				if(CampAdverse) {  
					motor.TurnAround();
					motor.Stop();
				}
				if(!CampAdverse && NbPalet>1) {
					motor.Left(400); 
					motor.Straight();
					Delay.msDelay(1500);
					motor.Right(400);
				}
				pal=true;
			}
			coul=couleur.getColor();
			if(touch.estActif() && (coul.equals("VERT") || coul.equals("BLEU"))) {  
				motor.Stop();
				ram.carry();
				//motor.Back();
				//Delay.msDelay(1000);
				if(!CampAdverse) {
					motor.Left();
					motor.Stop();
				}
				if(CampAdverse) {
					motor.Right();
					motor.Stop();
				}
				pal=true;
			}
			while(pal && !coul.equals("BLANC")) {  // DEPOSER PALET 
				motor.Straight(); 
				coul=couleur.getColor();
			}
			if(coul.equals("BLANC") && NbPalet==1) { // DERNIER DEPOT
				motor.Stop();
				ram.drop();
				motor.Back();
				NbPalet-=1;
				break;
			}
			if(coul.equals("BLANC") && NbPalet>1) { // DEPOT & REPLACEMENT POUR FINIR LA LIGNE EN COURS
				motor.Stop();
				ram.drop();
				motor.Back();
				Delay.msDelay(500);
				NbPalet-=1;
				pal=false;
				coul=couleur.getColor();
				if(coul.equals("NOIR")) { // FAIT UN DEMI TOUR POUR SE REPLACER SUR LA LIGNE NOIRE
					motor.TurnAround();
				}
				if(!coul.equals("NOIR")) {	// PERMET DE TROUVER LA LIGNE JAUNE OU ROUGE
					motor.Right();
					motor.Stop();
					coul=couleur.getColor();
					while(!coul.equals("JAUNE") || !coul.equals("ROUGE")|| !coul.equals("NOIR")) {
						motor.Straight();
						coul=couleur.getColor();
					}
					if(coul.equals("JAUNE") || coul.equals("ROUGE") || coul.equals("NOIR")) {
						motor.Stop();
						motor.Straight();
						Delay.msDelay(200);
						motor.Right();
					}
				}
				coul=couleur.getColor();
				while((coul.equals("JAUNE") || coul.equals("VERT")|| coul.equals("ROUGE")) && !touch.estActif()) {
					motor.Straight();
					coul=couleur.getColor();
					if(touch.estActif() && CampAdverse && coul.equals("JAUNE")) {
						motor.Stop();
						ram.carry();
						Delay.msDelay(700);
						motor.TurnAround();
						pal=true;
						break;
					}
					else if(touch.estActif() && !CampAdverse && coul.equals("JAUNE")) {
						motor.Stop();
						ram.carry();
						pal=true;
						break;
					}
					if(!touch.estActif() && coul.equals("NOIR")) {
						motor.Stop();
						motor.TurnAround();
						pal=false; // SANS DOUTE INUTILE, A VERIFIER
					}
				}
				if(coul.equals("NOIR")) {
					while(!coul.equals("VERT") && !touch.estActif()) {
						motor.Straight();
						coul=couleur.getColor();
					}
					if(coul.equals("VERT")) {
						motor.Stop();
						motor.TurnAround();
					}
					if(touch.estActif()) {
						motor.Stop();
						ram.carry();
						if(!CampAdverse) {
							motor.TurnAround();
							pal=true;
						}
					}
				}
			}
		}
	}

	public void DirectionRougeNord() {
		String coul=couleur.getColor();
		boolean pal=false;
		
		while(!touch.estActif() && !coul.equals("BLEU")) {
			line(coul);
			coul=couleur.getColor();
		}
		
		while(NbPalet!=0) {
			coul=couleur.getColor();
			if(coul.equals("BLEU") && !touch.estActif()) {
				motor.Left(30);
				coul=couleur.getColor();
				while(!coul.equals("BLEU")){
					motor.Left(30);
					coul=couleur.getColor();
				}
				motor.Left(70);
			}
			coul=couleur.getColor();
			while(!touch.estActif() && coul.equals("BLEU")) {
				line(coul);
				coul=couleur.getColor();
			}
			coul=couleur.getColor();
			if(coul.equals("NOIR") && !touch.estActif()) {
				motor.Left(30);
				coul=couleur.getColor();
				while(!coul.equals("NOIR")){
					motor.Left(30);
					coul=couleur.getColor();
				}
				motor.Left(70);
			}
			coul=couleur.getColor();
			while((coul.equals("NOIR") || coul.equals("GRIS")) && !touch.estActif()) {
				line(coul);
				coul=couleur.getColor();
			}
			coul=couleur.getColor();
			if(coul.equals("VERT") && !touch.estActif()) {
				motor.Right(30);
				coul=couleur.getColor();
				while(!coul.equals("VERT")){
					motor.Right(30);
					coul=couleur.getColor();
				}
				motor.Right(70);
			}
			coul=couleur.getColor();
			while(coul.equals("VERT") && !touch.estActif()) {
				line(coul);
				coul=couleur.getColor();
			}
			coul=couleur.getColor();
			if(coul.equals("JAUNE") && !touch.estActif()) {
				motor.Right(30);
				coul=couleur.getColor();
				while(!coul.equals("JAUNE")){
					motor.Right(30);
					coul=couleur.getColor();
				}
				motor.Right(30);
			}
			coul=couleur.getColor();
			while(!touch.estActif() && (!coul.equals("BLANC"))){
				line(coul);
				coul=couleur.getColor();
			}
			coul=couleur.getColor();
			if(touch.estActif() && (coul.equals("JAUNE") || coul.equals("ROUGE"))) {  
				motor.Stop();
				ram.carry();
				//Delay.msDelay(700);
				if(!CampAdverse) { // RETOUR CAMP ORIGINE
					motor.TurnAround();
					motor.Stop();
				}
				if(CampAdverse && NbPalet>1) {	// SE DECALE DE LA LIGNE POUR EVITER AUTRES PALETS
					motor.Left(400); 
					motor.Straight();
					Delay.msDelay(1500);
					motor.Right(400);
				}
				pal=true;
			}
			coul=couleur.getColor();
			if(touch.estActif() && coul.equals("NOIR")) {  // S'il détecte un palet, il s'arrete et ferme ses bras
				motor.Stop();
				ram.carry();
				Delay.msDelay(700);
				if(CampAdverse) {  
					motor.TurnAround();
					motor.Stop();
				}
				if(!CampAdverse && NbPalet>1) {
					motor.Left(400); 
					motor.Straight();
					Delay.msDelay(1500);
					motor.Right(400);
				}
				pal=true;
			}
			coul=couleur.getColor();
			if(touch.estActif() && (coul.equals("VERT") || coul.equals("BLEU"))) {  
				motor.Stop();
				ram.carry();
				motor.Back();
				Delay.msDelay(1000);
				if(!CampAdverse) {
					motor.Left();
					motor.Stop();
				}
				if(CampAdverse) {
					motor.Right();
					motor.Stop();
				}
				pal=true;
			}
			while(pal && !coul.equals("BLANC")) {  // DEPOSER PALET 
				motor.Straight(); 
				coul=couleur.getColor();
			}
			if(coul.equals("BLANC") && NbPalet==1) { // DERNIER DEPOT
				motor.Stop();
				ram.drop();
				motor.Back();
				Delay.msDelay(1000);
				NbPalet-=1;
				break;
			}
			if(coul.equals("BLANC") && NbPalet>1) { // DEPOT & REPLACEMENT POUR FINIR LA LIGNE EN COURS
				motor.Stop();
				ram.drop();
				Delay.msDelay(500);
				motor.Back();
				//Delay.msDelay(900);
				NbPalet-=1;
				pal=false;
				coul=couleur.getColor();
				if(coul.equals("NOIR")) { // FAIT UN DEMI TOUR POUR SE REPLACER SUR LA LIGNE NOIRE
					motor.TurnAround();
				}
				if(!coul.equals("NOIR")) {	// PERMET DE TROUVER LA LIGNE JAUNE OU ROUGE
					motor.Right();
					motor.Stop();
					coul=couleur.getColor();
					while(!coul.equals("JAUNE") || !coul.equals("ROUGE")|| !coul.equals("NOIR")) {
						motor.Straight();
						coul=couleur.getColor();
					}
					if(coul.equals("JAUNE") || coul.equals("ROUGE") || coul.equals("NOIR")) {
						motor.Stop();
						motor.Straight();
						Delay.msDelay(200);
						motor.Right();
					}
				}
				coul=couleur.getColor();
				while((coul.equals("JAUNE") || coul.equals("VERT")|| coul.equals("ROUGE")) && !touch.estActif()) {
					motor.Straight();
					coul=couleur.getColor();
					if(touch.estActif() && CampAdverse && coul.equals("JAUNE")) {
						motor.Stop();
						ram.carry();
						Delay.msDelay(700);
						motor.TurnAround();
						pal=true;
						break;
					}
					else if(touch.estActif() && !CampAdverse && coul.equals("JAUNE")) {
						motor.Stop();
						ram.carry();
						pal=true;
						break;
					}
					if(!touch.estActif() && coul.equals("NOIR")) {
						motor.Stop();
						motor.TurnAround();
					}
				}
				if(coul.equals("NOIR")) {
					while(!coul.equals("VERT") && !touch.estActif()) {
						motor.Straight();
						coul=couleur.getColor();
					}
					if(coul.equals("VERT")) {
						motor.Stop();
						motor.TurnAround();
					}
					if(touch.estActif()) {
						motor.Stop();
						ram.carry();
						if(!CampAdverse) {
							motor.TurnAround();
							pal=true;
						}
					}
				}
			}
		}
	}
	
	

	public void DirectionJauneSud() {  
		String coul=couleur.getColor();
		boolean pal=false;
		while(!touch.estActif() && !coul.equals("VERT")) {
			line(coul); // (coul.equals("JAUNE") || coul.equals("BLEU") || coul.equals("NOIR") || coul.equals("GRIS"))
			coul=couleur.getColor(); // A EFFECTUER SEULEMENT AU DEBUT, POUR EVITER ERREUR APRES
		}
		while(NbPalet!=0) {
			coul=couleur.getColor();
			if(coul.equals("VERT") && !touch.estActif()) {
				line(coul);
				Delay.msDelay(200);
				motor.Left();
				line(coul);
				Delay.msDelay(2000); // TEMPS NECESSAIRE POUR ALLER JUSQU'AU PROCHAIN CROISEMENT
			}
			coul=couleur.getColor();
			if(coul.equals("VERT") && !touch.estActif()) {
				motor.Left();
			}
			coul=couleur.getColor();
			while((coul.equals("NOIR") || coul.equals("GRIS")) && !coul.equals("BLEU") && !touch.estActif()) {
				line(coul);
				coul=couleur.getColor();
				if(coul.equals("BLEU")) {
					motor.Stop();
				}
			}
			coul=couleur.getColor();
			if(!touch.estActif() && (!coul.equals("NOIR") || coul.equals("GRIS") || coul.equals("BLEU"))){

				motor.Stop();

				motor.Right();

				line(coul);

				Delay.msDelay(2000);

			}

			coul=couleur.getColor();

			if(!touch.estActif() && (coul.equals("BLEU")|| coul.equals("GRIS"))){

				motor.Right();

			}

			coul=couleur.getColor();

			while(!touch.estActif() && (!coul.equals("BLANC"))){

				line(coul);

				coul=couleur.getColor();

			}

			coul=couleur.getColor();

			if(touch.estActif() && (coul.equals("JAUNE") || coul.equals("ROUGE"))) {  

				motor.Stop();

				ram.carry();

				Delay.msDelay(700);

				if(!CampAdverse) { // RETOUR CAMP ORIGINE

					motor.TurnAround();

					motor.Stop();

				}

				if(CampAdverse && NbPalet>1) {	// SE DECALE DE LA LIGNE POUR EVITER AUTRES PALETS

					motor.Left(400);

					line(coul);

					Delay.msDelay(1500);

					motor.Right(400);

				}

				pal=true;

			}

			coul=couleur.getColor();

			if(touch.estActif() && coul.equals("NOIR")) {  // S'il d?tecte un palet, il s'arrete et ferme ses bras

				motor.Stop();

				ram.carry();

				Delay.msDelay(700);

				if(CampAdverse) {  

					motor.TurnAround();

					motor.Stop();

				}

				pal=true;

			}

			coul=couleur.getColor();

			if(touch.estActif() && (coul.equals("VERT") || coul.equals("BLEU"))) {  

				motor.Stop();

				ram.carry();

				Delay.msDelay(700);

				line(coul);

				Delay.msDelay(300);

				if(!CampAdverse) {

					motor.Left();

					motor.Stop();

				}

				if(CampAdverse) {

					motor.Right();

					motor.Stop();

				}

				pal=true;

			}

			while(pal && !coul.equals("BLANC")) {  // DEPOSER PALET 

				line(coul);

				coul=couleur.getColor();

			}

			if(coul.equals("BLANC") && NbPalet==1) { // DERNIER DEPOT

				motor.Stop();

				ram.drop();

				NbPalet-=1;

				break;

			}

			if(coul.equals("BLANC") && NbPalet>1) { // DEPOT & REPLACEMENT POUR FINIR LA LIGNE EN COURS

				NbPalet-=1;

				pal=false;

				motor.Stop();

				ram.drop();

				motor.Back();

				motor.Stop();

				coul=couleur.getColor();

				if(!coul.equals("NOIR")) {	// PERMET DE TROUVER LA LIGNE JAUNE OU ROUGE

					motor.Right();

					coul=couleur.getColor();

					while(!coul.equals("JAUNE") || !coul.equals("ROUGE")) {

						line(coul);

						coul=couleur.getColor();

					}

				}

				else if(coul.equals("NOIR")) { // FAIT UN DEMI TOUR POUR SE REPLACER SUR LA LIGNE NOIRE

					motor.TurnAround();

				}

				coul=couleur.getColor();

				if(coul.equals("JAUNE") || coul.equals("ROUGE")) {

					motor.Stop();

					motor.Right();

				}

				coul=couleur.getColor();

				while((coul.equals("JAUNE") || coul.equals("VERT")|| coul.equals("ROUGE")) && !coul.equals("NOIR") && !touch.estActif()) {

					line(coul);

					coul=couleur.getColor();

					if(touch.estActif() && CampAdverse && coul.equals("JAUNE")) {

						motor.Stop();

						ram.carry();

						Delay.msDelay(700);

						motor.TurnAround();

						pal=true;

					}

					if(!touch.estActif() && coul.equals("NOIR")) {

						motor.Stop();

						motor.TurnAround();

						pal=false; // SANS DOUTE INUTILE, A VERIFIER
					}
				}
			}
		}
	}

	
	public void DirectionJauneNord() {
		String coul=couleur.getColor();
		boolean pal=false;
		while(!touch.estActif() && !coul.equals("BLEU")) {
			line(coul); 
			coul=couleur.getColor(); // A EFFECTUER SEULEMENT AU DEBUT, POUR EVITER ERREUR APRES
		}
		while(NbPalet!=0) {

			coul=couleur.getColor();
			if(coul.equals("BLEU") && !touch.estActif()) {
				line(coul);
				Delay.msDelay(200);
				motor.Right();
				line(coul);
				Delay.msDelay(2000); // TEMPS NECESSAIRE POUR ALLER JUSQU'AU PROCHAIN CROISEMENT
			}
			coul=couleur.getColor();
			if(coul.equals("BLEU") && !touch.estActif()) {
				motor.Right();
			}
			coul=couleur.getColor();
			while((coul.equals("NOIR") || coul.equals("GRIS")) && !touch.estActif()) {
				line(coul);
				coul=couleur.getColor();
				if(coul.equals("VERT")) {
					motor.Stop();
				}

			}

			coul=couleur.getColor();

			if(!touch.estActif() && (/*!coul.equals("NOIR") || coul.equals("GRIS") ||*/ coul.equals("VERT"))){

				line(coul);

				Delay.msDelay(200);

				motor.Left();

				line(coul);

				Delay.msDelay(2000);

			}

			coul=couleur.getColor();

			if(!touch.estActif() && (coul.equals("VERT"))){

				motor.Left();

			}

			coul=couleur.getColor();

			while(!touch.estActif() && (!coul.equals("BLANC"))){

				line(coul);

				coul=couleur.getColor();

			}

			coul=couleur.getColor();

			if(touch.estActif() && (coul.equals("JAUNE") || coul.equals("ROUGE"))) {  

				motor.Stop();

				ram.carry();

				//Delay.msDelay(700);

				if(!CampAdverse) { // RETOUR CAMP ORIGINE

					motor.TurnAround();

					motor.Stop();

				}

				if(CampAdverse && NbPalet>1) {	// SE DECALE DE LA LIGNE POUR EVITER AUTRES PALETS

					motor.Left(400); 

					line(coul);

					Delay.msDelay(1500);

					motor.Right(400);

				}

				pal=true;

			}

			coul=couleur.getColor();

			if(touch.estActif() && coul.equals("NOIR")) {  // S'il d?tecte un palet, il s'arrete et ferme ses bras

				motor.Stop();

				ram.carry();

				Delay.msDelay(700);

				if(CampAdverse) {  

					motor.TurnAround();

					motor.Stop();

				}

				if(!CampAdverse && NbPalet>1) {

					motor.Left(400); 

					line(coul);

					Delay.msDelay(1500);

					motor.Right(400);

				}

				pal=true;

			}

			coul=couleur.getColor();

			if(touch.estActif() && (coul.equals("VERT") || coul.equals("BLEU"))) {  

				motor.Stop();

				ram.carry();

				motor.Back();

				if(!CampAdverse) {

					motor.Left();

					motor.Stop();

				}

				if(CampAdverse) {

					motor.Right();

					motor.Stop();

				}

				pal=true;

			}

			while(pal && !coul.equals("BLANC")) {  // DEPOSER PALET 

				line(coul); 

				coul=couleur.getColor();

			}

			if(coul.equals("BLANC") && NbPalet==1) { // DERNIER DEPOT

				motor.Stop();

				ram.drop();

				motor.Back();

				NbPalet-=1;

				break;

			}

			if(coul.equals("BLANC") && NbPalet>1) { // DEPOT & REPLACEMENT POUR FINIR LA LIGNE EN COURS

				motor.Stop();

				ram.drop();

				Delay.msDelay(500);

				motor.Back();

				//Delay.msDelay(900);

				//motor.Stop();

				NbPalet-=1;

				pal=false;

				coul=couleur.getColor();

				if(coul.equals("NOIR")) { // FAIT UN DEMI TOUR POUR SE REPLACER SUR LA LIGNE NOIRE

					motor.TurnAround();

				}

				if(!coul.equals("NOIR")) {	// PERMET DE TROUVER LA LIGNE JAUNE OU ROUGE

					motor.Right();

					motor.Stop();

					coul=couleur.getColor();

					while(!coul.equals("JAUNE") || !coul.equals("ROUGE")|| !coul.equals("NOIR")) {

						line(coul);

						coul=couleur.getColor();

					}

					if(coul.equals("JAUNE") || coul.equals("ROUGE") || coul.equals("NOIR")) {

						motor.Stop();

						line(coul);

						Delay.msDelay(200);

						motor.Right();

					}

				}

				coul=couleur.getColor();

				while((coul.equals("JAUNE") || coul.equals("VERT")|| coul.equals("ROUGE")) && !touch.estActif()) {

					line(coul);

					coul=couleur.getColor();

					if(touch.estActif() && CampAdverse && coul.equals("JAUNE")) {

						motor.Stop();

						ram.carry();

						Delay.msDelay(700);

						motor.TurnAround();

						pal=true;

						break;

					}

					else if(touch.estActif() && !CampAdverse && coul.equals("JAUNE")) {

						motor.Stop();

						ram.carry();

						pal=true;

						break;

					}

					if(!touch.estActif() && coul.equals("NOIR")) {

						motor.Stop();

						motor.TurnAround();

					}

				}

				if(coul.equals("NOIR")) {

					while(!coul.equals("VERT") && !touch.estActif()) {

						line(coul);

						coul=couleur.getColor();

					}

					if(coul.equals("VERT")) {

						motor.Stop();

						motor.TurnAround();

					}

					if(touch.estActif()) {

						motor.Stop();

						ram.carry();

						if(!CampAdverse) {

							motor.TurnAround();

							pal=true;

						}

					}

				}

			}

		}

	}

	

	public void Evitement() {  // MODE COMPETITION

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

	

	public void lookFor(String color) {

		String coul = couleur.getColor();

		while(coul != color ) {

			motor.Straight();		

			coul = couleur.getColor();

		}

		motor.Stop();

	}

	

	public String lookFor() {

		String coul = couleur.getColor();

		while((coul == "NOIR") || (coul == "GRIS")) {

			motor.Straight();		

			coul = couleur.getColor();

		}

		motor.Stop();

		return coul;

	}

	public void lookForP(String color) {

		String coul = couleur.getColor();

		while(!touch.estActif() && coul == color ) {

			motor.Straight();

			coul = couleur.getColor();

		}

		motor.Stop();

		ram.carry();

	}

	public void retour(int r) {

		

		String coul = couleur.getColor();

		switch (r) {

		

		case 1 : 

			while(coul != "BLANC" ) {

				motor.Straight();

			}

			motor.Stop();

			ram.drop();	

			motor.TurnAround();

			

		case 2 :                                                 // 2 :il faut tourner ? droite

			motor.Right();

			while(coul != "BLANC" ) {

				motor.Straight();

			}

			motor.Stop();

			ram.drop();	

			motor.TurnAround();

			

		case 3 :												// il faut tourner ? gauche

			motor.Left();

			while(coul != "BLANC" ) {

				motor.Straight();

			}

			motor.Stop();

			ram.drop();

			motor.TurnAround();

				

		case 4 :											 // il faut faire demi-tour

			motor.TurnAround();

			while(coul != "BLANC" ) {

				motor.Straight();

			}

			motor.Stop();

			ram.drop();

			motor.TurnAround();

			

		}

	}

	

	public static void main(String[] args) {

		Movement test=new Movement();

		test.DirectionRougeNord();

	}

}
