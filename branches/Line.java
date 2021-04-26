package codesource;

public class Line {
    
	Motor motor=new Motor();
    ColorSensor col=new ColorSensor();
    String couleur=col.getColor();
    
    public void line(boolean r) {
    	
    	String couleur=col.getColor();
        
    	while(r) {
            if(!couleur.equals("GRIS")) {
                motor.setMotorGSpeed(550);
                motor.setMotorDSpeed(350);
                motor.Straight();
                couleur=col.getColor();
            
            if(couleur.equals("GRIS")) {
                motor.setMotorGSpeed(350);
                motor.setMotorDSpeed(550);
                motor.Straight();
                couleur=col.getColor();
                
            }
            }
            couleur=col.getColor();
        }
    }
}    