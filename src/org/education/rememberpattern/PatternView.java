package org.education.rememberpattern;


	import android.view.View;
	import android.content.Context;
	import android.graphics.Rect;
	import android.util.Log;
	import android.graphics.Canvas;
	import android.graphics.Paint;
	import android.view.KeyEvent;
	import android.view.MotionEvent;
	import android.widget.TextView;
    import android.widget.Toast;
	import android.os.Bundle;
	import android.content.Intent;
    import android.app.Activity;
    import java.io.FileInputStream;
    import java.io.FileOutputStream;
    import java.io.InputStreamReader;
    import java.io.OutputStreamWriter;
    import java.io.IOException;

    import java.util.Set;
    import java.util.TreeSet;
    
    import android.media.MediaPlayer;
    import android.media.AudioManager;
    

	public class PatternView  extends View {
	    private static final String TAG = "Pattern" ;
	    public int N = 15;
		
	    private float width;
	    private float height;
	    private int siteX;
	    private int siteY;
	    private Rect matrix [][];
	    public static boolean inShape [] [];
	    public static int isStarted;
	    public static int shapeSize;
	    public static int score;
	    public Toast toast;
	    public static Set<Integer> tiles;

	    private Context con;
	    
	    PatternData data;
	
	    private MediaPlayer mp;
	    
		public PatternView(Context context, int N){
			
			super(context);
			con = getContext();
			((Activity) con).setVolumeControlStream(AudioManager.STREAM_MUSIC);
			this.N = N;
			
			score = 0;
		
			
			
	        matrix = new Rect[N][N];
			for (int i = 0; i < N; i++){
				for (int j = 0; j < N; j++){
					matrix[i][j] = new Rect();
				}
			}
			inShape = new boolean[N][N];
			tiles = new TreeSet<Integer>();
			//con = getContext();
			//toast =  Toast.makeText((( Activity) con ).getBaseContext(), "Let's start!", Toast.LENGTH_SHORT);
			//toast.show();
			data = new PatternData();
			setFocusable(true);
			setFocusableInTouchMode(true);
		}
		
		
		@Override
		protected void onSizeChanged(int w, int h, int prevw, int prevh) {
			width = w / (float)N;
			height = h / (float)N;
			//getRect(siteX, siteY, rectangle.get(count));
	        
			//Log.d(TAG, "onSizeChanged: width " + width + ", height "+ height);
		
			super.onSizeChanged(w, h, prevw, prevh);
		}
		
		
		private void getRect(int x, int y, Rect rect) {
		rect.set((int) (x * width), (int) (y * height), (int) (x
		* width + width), (int) (y * height + height));
		}

		private void setShape(int a, Canvas c, Paint p){
			int i = 2;
		    int j, k;
		    while (i < PatternData.shapes[a].size()){
		    	j = PatternData.shapes[a].get(i);
		    	k = PatternData.shapes[a].get(i+1);
		    	getRect(k,j, matrix[j][k]);
				c.drawRect(matrix[j][k], p);
		    	i = i + 2;
		    }
		}
		
		private void playSound(){
			
			int music = R.raw.maya;
			/*int num =  RememberPattern.count % 3;
			System.out.println("music num  "  + num);
			switch(num){
			case 0: music = R.raw.completed;
			case 1: music = R.raw.chain;
			case 2: music = R.raw.hajokurt;
			 
			
			}*/
			
		if (mp != null){
			mp.release();
		}
		mp = MediaPlayer.create(con, music);
		mp.start();
		}
		
		@Override
		protected void onDraw(Canvas canvas)  {
			
			
		
			Paint background = new Paint();
			background.setColor(getResources().getColor(R.color.background));
			canvas.drawRect(0, 0, getWidth(), getHeight(), background);
			
		
		
			Paint highlight = new Paint();
			highlight.setColor(getResources().getColor(R.color.highlight));
			
			Paint [] tile = new Paint[10];
			for (int i = 0; i < 10; i++){
				tile[i]= new Paint();
			}
			
			tile[0].setColor(getResources().getColor(R.color.tile0));
			tile[1].setColor(getResources().getColor(R.color.tile1));
			tile[2].setColor(getResources().getColor(R.color.tile2));
			tile[3].setColor(getResources().getColor(R.color.tile3));
			tile[4].setColor(getResources().getColor(R.color.tile4));
			tile[5].setColor(getResources().getColor(R.color.tile5));
			tile[6].setColor(getResources().getColor(R.color.tile6));
			tile[7].setColor(getResources().getColor(R.color.tile7));
			tile[8].setColor(getResources().getColor(R.color.tile8));
			tile[9].setColor(getResources().getColor(R.color.tile9));
			
			
			
			Paint wrong = new Paint();
			wrong.setColor(getResources().getColor(R.color.wrong));
			
			Paint close = new Paint();
			close.setColor(getResources().getColor(R.color.close));
			
			if (isStarted != 0){
				setShape(isStarted, canvas, tile[RememberPattern.count % 10]);
			}
			
        	
          
          if(isStarted != 0){
        	  canvas.drawLine(0, getHeight()- 1, getWidth(), getHeight() -1, highlight);
        	  canvas.drawLine(0, 0, getWidth(), 0, highlight);
        	  canvas.drawLine(getWidth() -1, 0, getWidth() -1, getHeight(), highlight);
        	  canvas.drawLine(0, 0, 0, getHeight(), highlight);
          }
          // Draw the selection...
			if (isStarted == 0){
			
				System.out.println("count " + RememberPattern.count);
				
				for (int row = 1; row <= N; row++) {
		            for (int col = 1; col <= N; col++) {
		            	//int item = (row -1)*N + (col -1);
		            	//Log.d(TAG, "is in the shape " + inShape[col-1][row-1]);
		               if (inShape[col-1][row-1]) {
		                	//Log.d(TAG, "full item " + item);
		                	//Log.d(TAG, "full " + (row - 1) + " , " + (col - 1));
		                    
		                    canvas.drawRect(matrix[col-1][row-1], tile[RememberPattern.count % 10]);
		                    
		                    
		                    //Log.d(TAG, "size" + tiles.size());
		                    if ( shapeSize  == tiles.size()){
		                    	
		                    
		                    	//playSound();
	                        	
		                    	con = getContext();
		               //toast =  Toast.makeText((( Activity) con ).getBaseContext(), "You won!", Toast.LENGTH_SHORT);
		               //toast.show();
		               
		                             	
		        				((Activity) con ).startActivity(new Intent("org.education.rememberpattern.RememberPattern"));
		        				
		        				
		                    }
		                    
		                }//if
		               else {
		            	   canvas.drawRect(matrix[col-1][row-1], wrong);
		            	   
		               }//else
		                
		                
		            }//for
		            	
		        }//for

			//}
			

			// Draw the minor grid lines
			for (int i = 0; i < N; i++) {
				canvas.drawLine(0, i * height, getWidth(), i * height, highlight);
				
				canvas.drawLine(0, getHeight()- 1, getWidth(), getHeight() -1, highlight);
				canvas.drawLine(i * width, 0, i * width, getHeight(), highlight);
				
				canvas.drawLine(getWidth() -1, 0, getWidth() -1, getHeight(), highlight);
			}
			}
		}
		
		

		@Override
		public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_DOWN)
		return super.onTouchEvent(event);
		
		select((int) (event.getX() / width), (int) (event.getY() / height));
		
		//Log.d(TAG, "coordinates: "+ event.getX() + " , " + event.getY());
		//Log.d(TAG, "onTouchEvent: x " + siteX + ", y " + siteY);
		
		for (int row = 0; row < N; row++) {
	        for (int col = 0; col < N; col++) {
	     invalidate(matrix[row][col]);
	        }
		}
		
		
		return true;
		}

		private void select(int x, int y) {
			
			
			siteX = x;
			siteY = y;
			if (inShape[x][y]){
			int	tile = x*N + y;
			tiles.add(tile);
				
			}
			else
			{
			if (! (RememberPattern.count == 100) ){
				//scoring, not for the 100th level
				score++;
			}
			
			}
			getRect(siteX, siteY, matrix[x][y]);
			invalidate(matrix[x][y]);
			}

	}


