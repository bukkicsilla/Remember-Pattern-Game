package org.education.rememberpattern;

public class PatternThread extends Thread {
   private final static int SLEEP = 1000;
   private boolean running = false;
   private PatternView canvas = null;

   public PatternThread(){
	   super();
	   this.canvas = canvas;
   }

   public void startThread(){
	   
	   running = true;
	   super.start();
   }
   
   public void stopThread(){
	   running = false;
   }
   
   
   public void run(){
	   //while(running){
		   try{
			   //canvas.invalidate();
			   //RememberPattern.bt.setClickable(false);
			   //RememberPattern.bt.setEnabled(false);
			   sleep(SLEEP);
		   }
		   catch(InterruptedException ie){}
	   //}
	   
   }
}
