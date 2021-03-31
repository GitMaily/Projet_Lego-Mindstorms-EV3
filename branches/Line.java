package codesource;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Line {
	//Attributs
	private String couleur;
	private int ligne;
	
	//Constructeurs
	public Line(String couleur, int ligne) {
		this.couleur = couleur;
		this.ligne = ligne;
		ligne = 1;
	}
	
	public void Follower(couleur) {
		if (couleur=="JAUNE") {
			Motor.Straight();
		}
		else {
			Motor.Left();
			Motor.Right();
		}
		
		ligne += 1;
	}

