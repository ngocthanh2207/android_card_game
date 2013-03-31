package fr.cgcorp.cardgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void onClickJoinGame(View v) {
    	Toast.makeText(this, "Join Game not implemented yet", Toast.LENGTH_SHORT).show();
    }
    
    public void onClickNewGame(View v) {
    	Intent intent = new Intent(this, fr.cgcorp.cardgame.GameActivity.class);
    	startActivity(intent);
    }
    
    public void onClickSettings(View v) {
    	Toast.makeText(this, "Settings not implemented yet", Toast.LENGTH_SHORT).show();
    }
}
