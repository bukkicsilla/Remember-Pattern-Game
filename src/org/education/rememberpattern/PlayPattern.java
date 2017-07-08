package org.education.rememberpattern;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

public class PlayPattern  extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.play_pattern);
		
	}
	public void onClickAbout(View view){
		//Intent i = new Intent(this, AboutPattern.class);
		Intent i = new Intent("org.education.rememberpattern.AboutPattern");
		startActivity(i);
		
	}
	
	public void onClickPlay(View view){
		startActivity(new Intent("org.education.rememberpattern.RememberPattern"));
		
	}
	public void onClickFinish(View view){
		
		moveTaskToBack(true);
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
			if ((keyCode == KeyEvent.KEYCODE_BACK)) { //Back key pressed
				//Toast.makeText(getBaseContext(), "Push again to go back", Toast.LENGTH_SHORT).show();
				//back = true;
				//finish();
				 moveTaskToBack(true);
				//return true;
			}
		
	    
	    return super.onKeyDown(keyCode, event);
	}
	
	 @Override
	    public void onBackPressed() {
	            super.onBackPressed();
	            
	            this.finish();
	    }

}
