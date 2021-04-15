package codesource;



import lejos.utility.Delay;

public class Line {
    
    Motor motor=new Motor();
    ColorSensor couleur=new ColorSensor();
    String couleurLine=couleur.getColor();
    
    public void line(String coul) { //assigner coul
        while(couleurLine.equals(coul) || couleurLine.equals("GRIS")) { //tant que coul ou gris
        	while(couleurLine.equals(coul)) { //Tant que le robot est sur la ligne coul
	        	motor.setMotorGSpeed(400);//Le robot dérive un peu plus à droite
	        	motor.setMotorDSpeed(352);
	        	
	            motor.Straight();
	            couleurLine=couleur.getColor();
            
            while(couleurLine.equals("GRIS")) { //Tant que le robot voit du GRIS (hors de la ligne)
	            motor.setMotorGSpeed(350);//Le robot dérive un peu plus à gauche
	            motor.setMotorDSpeed(400);
	            motor.Straight();
	            //motor.Left(0.5);
	            couleurLine=couleur.getColor();
	            
                
            	}
        	}
            
            couleurLine=couleur.getColor();
            
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