package codesource;

import lejos.utility.Delay;
import lejos.utility.Stopwatch;
public class Line {
    
    Motor motor=new Motor();
    ColorSensor couleur=new ColorSensor();
    String couleurLine=couleur.getColor();
    private Stopwatch timer = new Stopwatch();
    
    //Cette classe va servir � faire des tests sur le suivi de ligne sur une couleur assign�e.
    //M�thode de zigzag sur le bord d'une ligne : d�rive � gauche si il voit du gris, � droite sinon.
    //Elle doit �tre impl�ment�e en tant que m�thode dans movement.java
    //Problemes rencontres : boucle infinie (r�solue), les roues se d�synchronisent (tester la ligne 70), le robot d�rive trop � droite.
    
    
    public void line(String coul) { //assigner coul
    	couleurLine = couleur.getColor();
        while(couleurLine.equals(coul) || couleurLine.equals("GRIS")) { //tant que coul ou gris
        	int time = 0; 
        	timer.reset(); 
        	while(couleurLine.equals(coul)) { //Tant que le robot est sur la ligne coul
        		timer.reset();
	        	motor.setMotorGSpeed(400);//Le robot d?rive un peu plus ? droite
	        	motor.setMotorDSpeed(352);
	        	
	            motor.Straight();
	            couleurLine=couleur.getColor();
            
            while(couleurLine.equals("GRIS")) { //Tant que le robot voit du GRIS (hors de la ligne)
            	
	            time = timer.elapsed();
	            motor.setMotorGSpeed(350);//Le robot d?rive un peu plus ? gauche
	            motor.setMotorDSpeed(400);
	            motor.Straight();
	            //motor.Left(0.5);
	            couleurLine=couleur.getColor();
	            //System.out.println(time);
	            
	            if( time > 2000) { //si le temps �coul� est > � 2 secondes
	            	
	            	motor.Stop(); //s'arr�ter pendant 1 sec
	            	Delay.msDelay(1000);
	            	
	            	while (!couleurLine.equals(coul)) { // Tant que le robot n'est pas sur une ligne
	            		
	            		//motor.Stop();
	            		couleurLine = couleur.getColor();
	            		motor.Back(); //Retourne jusqu'� l'avoir trouv�e
	            		couleurLine = couleur.getColor();
	            		
	            		if (couleurLine.equals(coul)) { //D�s qu'il l'a trouv�e, s'arr�te pendant 1 sec
	            			motor.Stop();
	            			Delay.msDelay(1000);
	            			motor.Right(50); //se d�cale un peu vers la droite (� varier)
	            		}
	            	}
	            	
	            	//motor.Stop();
	            	couleurLine = couleur.getColor();
	            	//motor.Stop();
	            	timer.reset();
	            	
	            }
                
            }
        	}
            
            couleurLine=couleur.getColor();
            //motor.StraightLine(); //resynchroniser les moteurs
        }
    }
    
    public static void main(String[]args){
    	
    	//Line test1=new Line();
    	
    	//test1.testColor();
    	System.out.println("1");
        Delay.msDelay(500);
        Line test=new Line();
        System.out.println("2");
        Delay.msDelay(500);
        test.line("JAUNE");
        System.out.println("5");
        Delay.msDelay(500);
    }
}    