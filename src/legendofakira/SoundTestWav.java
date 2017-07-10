package legendofakira;

import java.applet.*;

public class SoundTestWav {
	 private AudioClip clip;
		
	    public SoundTestWav(String wav){
	        //音源の読み込み
	    		//clip = AudioClip
	        clip = Applet.newAudioClip(getClass().getResource(wav));
	    }
		
	    public void loop() {
	        clip.loop();
	    }
	    
	    public void stop() {
	    		clip.stop();
	    }
}
