package org.education.rememberpattern;



import android.os.Bundle;
import android.app.Activity;
import android.widget.LinearLayout;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;
import android.view.View;
import android.view.KeyEvent;
import android.widget.Button;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import android.view.View.OnLongClickListener;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class RememberPattern extends Activity {

	PatternView pattern_view;
	static int count;
	static boolean hasStarted = false;
	static final int BLOCKSIZE = 10;
	private PatternThread thread;
	private Button bt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//bt = (Button) findViewById(R.id.start_button);
		//bt.setClickable(false);
		//bt.setEnabled(true);
		/*if (hasStarted){
		 thread = new PatternThread();
    	 thread.run();
		}*/
		//pattern_view = new PatternView(this);
		int tens = ((count -1 )/10) * 10;
		System.out.println("tens " + tens);
		
		System.out.println("score " + PatternView.score);
    	
    	count -= PatternView.score;
    	
    	
    	if ( count < tens){
    		count = tens;
    	}
    	count++;
    	if (!hasStarted){
    		loadCount();
    	}
    	System.out.println("count will be " + count);
    	hasStarted = true;
    	
		//TEST ****************************
		//count = 60;
		/*if (count == 1){
			count = 82;
			
		}*/
		
		if (count == 83){
			count = 101;
			
		}
		saveCount();
		if (count == 102 ){
			count = 1;
			saveCount();
			count = 0;
			hasStarted = false;
			startActivity(new Intent("org.education.rememberpattern.PlayPattern"));
            finish();
		}
		
		
		if (count < 11){
			pattern_view = new PatternView(this, 5);
		}
		
		

		else if  (count < 21){
			pattern_view = new PatternView(this, 6);
		}
		
		else if  (count < 31){
			pattern_view = new PatternView(this, 7);
		}
		
		else if  (count < 41){
			pattern_view = new PatternView(this, 8);
		}
		
		else if  (count < 51){
			pattern_view = new PatternView(this, 9);
		}
		
		else if  (count < 61){
			pattern_view = new PatternView(this, 10);
		}
		
		else if  (count < 71){
			pattern_view = new PatternView(this, 11);
		}
		
		else if  (count < 81){
			pattern_view = new PatternView(this, 12);
		}
		
		else if  (count == 81){
			pattern_view = new PatternView(this, 13);
		}
		else if  (count == 82){
			pattern_view = new PatternView(this, 14);
		}
		
		else if  (count < 102){
			pattern_view = new PatternView(this, 15);
		}
		
		PatternView.isStarted = count;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.remember_pattern);
		bt = (Button) findViewById(R.id.start_button);
		//bt.setClickable(false);
		//bt.setEnabled(false);
		//setContentView(pattern_view);
		bt.setOnLongClickListener(new OnLongClickListener() { 
	        @Override
	        public boolean onLongClick(View v) {
	        	startActivity(new Intent("org.education.rememberpattern.FindPattern"));
	            return true;
	        }
	    });

		//thread = new PatternThread();
		//thread.run();
		
		((LinearLayout) findViewById (R.id.layout_root)).addView(pattern_view, 3);
	
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) { //Back key pressed
	    	//System.out.println("eljut ide?");
	    	//count--;
		    //startActivity(new Intent("org.education.rememberpattern.PlayPattern"));
			
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	
  
	public void onClick(View view){
		//bt.setClickable(false);
		//bt.setEnabled(false);
		//thread = new PatternThread();
		//thread.run();
		//bt.setClickable(true);
		//bt.setEnabled(true);
		//startActivity(new Intent("org.education.rememberpattern.FindPattern"));
		Toast toast =  Toast.makeText(( this ).getBaseContext(), "Long Press Please!", Toast.LENGTH_SHORT);
		toast.show();
	}
	
	public void saveCount(){
		
		String str = count+"";
		try
		{
			FileOutputStream fileout = openFileOutput("count.txt", MODE_WORLD_READABLE);
			OutputStreamWriter osw = new OutputStreamWriter(fileout);
			osw.write(str);
			osw.flush();
			osw.close();
		}
	
		catch (IOException ex){
			ex.printStackTrace();		
		}
	}
	
	public void loadCount(){
		try{
			
			FileInputStream fileinput = openFileInput("count.txt");
			InputStreamReader isr = new InputStreamReader(fileinput);
			char [] inputBuffer = new char[BLOCKSIZE];
			String str = "";
			int charNum;
		while ( (charNum = isr.read(inputBuffer)) > 0){
			System.out.println("charNum " + charNum);
			String str1 = String.copyValueOf(inputBuffer, 0, charNum);
			str += str1;
			inputBuffer = new char[BLOCKSIZE];
		}//while
		count = Integer.parseInt(str);
		
		
		}
		catch (IOException ex){
			ex.printStackTrace();		
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.remember_pattern, menu);
		Toast toast =  Toast.makeText(( this ).getBaseContext(), "Long Press Please!", Toast.LENGTH_SHORT);
		toast.show();
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.menu_settings:
			//startActivity(new Intent(this, PreferencePattern.class));
			startActivity(new Intent("org.education.rememberpattern.PreferencePattern"));
			return true;
		}
		return false;
	}
}
