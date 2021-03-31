package codesource;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.Button;


public class Mode {
	//Attributs
	/*private String solo;
	private String competition;*/
	EV3UltrasonicSensor ultraSonicSensor=new EV3UltrasonicSensor(SensorPort.S2);//Besoin ou non du capteur ultrason
	
	//Constructeurs
	public Mode(String solo, String competition) {
		/*this.solo = solo;
		this.competition = competition;*/
		
	}
	//Pas besoin du capteur ultrason en mode solo, on désactive
    public void setSolo() {
    	ultraSonicSensor.disable();
	}
    
    //Activer le mode competition. Sera utilisée pour chercher les palets ainsi que d'éviter le robot adverse
    public void setCompetition() {
    	ultraSonicSensor.enable();
    }
    
    //Pour déterminer si ultrason est actif ou non
    //True si actif, False sinon
    public boolean isEnabled(){
		return ultraSonicSensor.isEnabled();
	}
    
    
    //Vrai si le bouton Entrée (le milieu) est cliqué
    public boolean Enter() {
    	return (Button.ENTER.isDown());
    }
    
    public selection() {
    	if (Enter()== True) {//à compléter et corriger, ou changer notre méthode
    		System.out.println("Activation du mode Solo");
    		setSolo();
    		
    	}
    	else {
    		setCompetition();
    		System.out.println(competition.isEnabled());
    	}
    }
    

    
    public static void main(String[] args) {
    	// à tester!
    }

		

	
}

