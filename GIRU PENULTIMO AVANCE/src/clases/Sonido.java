package clases;

import javax.sound.sampled.Clip;
//import javax.sound.sampled.FloatControl;

import clases.CargadorRecursos;

public class Sonido {

	private static Clip sonido;

	public Sonido(final String ruta) {
		sonido = CargadorRecursos.cargarSonido(ruta);
	}
	
	public void reproducir() {
		sonido.stop();
		sonido.flush();
		sonido.setMicrosecondPosition(0);
		sonido.start();
	}
	
	public static void repetir() {
		sonido.stop();
		sonido.flush();
		sonido.setMicrosecondPosition(0);
		
		sonido.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
//	public long obtenerDuracion() {
//		return sonido.getMicrosecondLength();
//	}
}