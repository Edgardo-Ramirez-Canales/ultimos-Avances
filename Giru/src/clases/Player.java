package clases;

import java.io.Serializable;

public class Player implements Serializable{

	private static final long serialVersionUID = 1L;
	private static String nombrePlayer;
	private static int puntuacion;
	
	public Player(String nombrePlayer, int puntuacion) {
		Player.nombrePlayer = nombrePlayer;
		Player.puntuacion = puntuacion;
	}

	public Player() {}
	
	public static String getNombrePlayer() {
		return nombrePlayer;
	}
	public void setNombre(String nombrePlayer) {
		Player.nombrePlayer = nombrePlayer;
	}
	public static int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		Player.puntuacion = puntuacion;
	}
	
	public static String toCSV() {
		return getNombrePlayer()+","+getPuntuacion()+"\n";
	}

	
}
