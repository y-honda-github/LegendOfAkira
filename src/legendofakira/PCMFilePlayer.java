package legendofakira;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class PCMFilePlayer implements Runnable { 
    private File file;
	private AudioInputStream in;
	private SourceDataLine line;
	private int frameSize;
	private byte[] buffer = new byte [32 * 1024]; // 32k is arbitrary
	private Thread playThread;
	private boolean playing;
	private boolean notYetEOF;
	int readPoint = 0;
	int bytesRead = 0;
	private AudioInputStream din = null;
	AudioFormat decodedFormat;

	public PCMFilePlayer (File f) throws IOException,
						UnsupportedAudioFileException,
						LineUnavailableException {
		file = f; // openするファイル
		in = AudioSystem.getAudioInputStream (f);
		AudioFormat format = in.getFormat();
		decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                				format.getSampleRate(),
                				16,
                				format.getChannels(),
                				format.getChannels()*2,
                				format.getSampleRate(),
                				false); // PCMフォーマットを指定
		// 指定された形式へ変換
		din = AudioSystem.getAudioInputStream(decodedFormat, in); 
		format = din.getFormat();
		frameSize = format.getFrameSize(); 
		DataLine.Info info = new DataLine.Info (SourceDataLine.class,
							decodedFormat); 
		// ラインを取得
		line = (SourceDataLine) AudioSystem.getLine (info); 
		line.open(); 
		playThread = new Thread (this); 
		playing = false; 
		notYetEOF = true;        
		playThread.start();
	}
	public void run() {
		readPoint = 0;
		bytesRead = 0;
		while(true){
			try {
				while (notYetEOF) {
					if (playing) {
						bytesRead = din.read (buffer, 
	                                                       readPoint, 
							       buffer.length - readPoint);
	                                        if (bytesRead == -1) { 
	                	   	                notYetEOF = false; 
	                	   	                break;
						}
						int leftover = bytesRead % frameSize;
						line.write (buffer, readPoint, bytesRead-leftover);
						System.arraycopy (buffer, bytesRead,
								  buffer, 0, 
								  leftover);
		                                readPoint = leftover;
					} else { 
						try { Thread.sleep (10);} 
						catch (InterruptedException ie) {}
					}
				}
				line.drain();
				line.stop();
				playing=false;
				notYetEOF=true;
				lineReset();
				// スレッドを休止
				suspend();
			} catch (IOException ioe) {
				//ioe.printStackTrace();
			} catch (InterruptedException e){
				
			} finally {
				//line.close();
			}
		}
	} // run

	public void start() {
		playing = true;
		line.start();
		try {
			// スレッド復帰
			resume();
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	public void contStart(){
    	if(playing){
			line.stop();
			line.flush();
			lineReset();
			line.start();
    	}else{
    		start();
    	}
	}

	public void stop(){
		playing = false;
		line.stop();
		line.flush();
		lineReset();
	}
   
	public SourceDataLine getLine() {
		return line;
	}

	public File getFile() {		
		return file; 
	}
	
    /**
     *　Lineの音量を調整する
     * @param linearScalar 0-1までの小数
     */
	public void volume(double linearScalar){
    	try{
    		FloatControl control = 
    				(FloatControl)line.getControl(FloatControl.Type.MASTER_GAIN);
    		control.setValue((float)Math.log10(linearScalar) * 20);
    	}catch(IllegalArgumentException e){
    		e.printStackTrace();
    	}
	}
	
    /**
     *　Lineをミュートする
     * @param state true or false
     */
	public void mute(boolean state){
    	BooleanControl control = 
    			(BooleanControl)line.getControl(BooleanControl.Type.MUTE);
    	if(control.getValue()!=state){
    		control.setValue(state);
    	}
	}
	
	public boolean isUsed(){
		return playing;
	}
	
    /**
     *　Lineを再取得する。
     * 
     */
	public void lineReset(){
		try {
			in = AudioSystem.getAudioInputStream (file);
			din = AudioSystem.getAudioInputStream(decodedFormat, in);
		} catch (UnsupportedAudioFileException e) {
		} catch (IOException e) {
		}
	}
	
    /**
     *　スレッドを休止。
     * 
     */
	public synchronized void suspend() throws InterruptedException {
	    this.wait();
	}
	
    /**
     *　スレッドを復帰。
     * 
     */
	public synchronized void resume() throws InterruptedException {
	    this.notify();
	}
	
	public void exit(){
		line.stop();
		line.close();
	}
}