package fr.cgcorp.cardgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
    
    public void onClickSave(View v) {
    	finish();
    }
}
