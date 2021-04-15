package codesource;



import lejos.utility.Delay;

public class Line {
    
    Motor motor=new Motor();
    ColorSensor col=new ColorSensor();
    String couleur=col.getColor();
    
    public void line(String coul) { //assigner coul
        while(couleur.equals(coul) || couleur.equals("GRIS")) { //tant que coul ou gris
        	while(couleur.equals(coul)) { //Tant que le robot est sur la ligne coul
	        	motor.setMotorGSpeed(400);//Le robot dérive un peu plus à droite
	        	motor.setMotorDSpeed(352);
	        	
	            motor.Straight();
	            couleur=col.getColor();
            
            while(couleur.equals("GRIS")) { //Tant que le robot voit du GRIS (hors de la ligne)
	            motor.setMotorGSpeed(350);//Le robot dérive un peu plus à gauche
	            motor.setMotorDSpeed(400);
	            motor.Straight();
	            //motor.Left(0.5);
	            couleur=col.getColor();
	            
                
            	}
        	}
            
            couleur=col.getColor();
            
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