package legendofakira;

import java.applet.*;

public class SoundTestWav {
	 private AudioClip clip;
		
	    public SoundTestWav(){
	        //音源の読み込み
	    		//clip = AudioClip
	        clip = Applet.newAudioClip(getClass().getResource("bgm.wav"));
			//clip.loop();	//音源の再生
	    }
		
	    public void loop() {
	        clip.loop();
	    }
	    
	    public void stop() {
	    		clip.stop();
	    }
}
