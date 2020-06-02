package clases;


import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

public class CargadorRecursos {
		
	public static Clip cargarSonido(final String ruta) {
		Clip clip = null;
		
		try {
			InputStream is = ClassLoader.class.getResourceAsStream(ruta);
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clip;
	}
	
	public static Clip cargarSonidoCambiarVolumen(final String ruta, final float reduccionVolumenDecibelios) {
		Clip clip = null;
		
		try {
			InputStream is = ClassLoader.class.getResourceAsStream(ruta);
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			FloatControl gainControl = 
				    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-reduccionVolumenDecibelios);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clip;
	}
}