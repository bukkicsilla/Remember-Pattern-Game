package org.education.rememberpattern;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;
import android.widget.Toast;
import android.content.Intent;

public class FindPattern extends Activity {
	
	PatternView pattern_view;
	PatternData data;
	boolean back = false;
	
	private void initShape(int a ){
		int grid = PatternData.shapes[a].get(1);
	    pattern_view = new PatternView(this, grid);
	    PatternView.shapeSize = ( PatternData.shapes[a].size() - 2 ) / 2;
	    int i = 2;
	    int j, k;
	    while (i < PatternData.shapes[a].size()){
	    	j = PatternData.shapes[a].get(i);
	    	k = PatternData.shapes[a].get(i+1);
	    	PatternView.inShape[k][j] = true;
	    	i = i + 2;
	    }
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//pattern_view = new PatternView(this);
		PatternView.isStarted = 0;
		data = new PatternData();
	   
		
	    int c = RememberPattern.count;
	    
	       initShape(c);
	    
		
		
		PatternView.isStarted = 0;
		PatternView.tiles.clear();
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(pattern_view);
		//setContentView(R.layout.find_pattern);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (!back){
			if ((keyCode == KeyEvent.KEYCODE_BACK)) { //Back key pressed
				//Toast.makeText(getBaseContext(), "Push again to go back", Toast.LENGTH_SHORT).show();
				//back = true;
				
				return true;
			}
		}
		/*if(back){
				if ((keyCode == KeyEvent.KEYCODE_BACK)) { //Back key pressed
					//System.out.println("eljut ide?");
					RememberPattern.count--;
				startActivity(new Intent("org.education.rememberpattern.RememberPattern"));
				}
			}*/
	    
	    return super.onKeyDown(keyCode, event);
	}
}
