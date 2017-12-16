package pl.ppm.gitfitscrub;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.content.SharedPreferences;

public class SettingsActivity extends Activity {
    int night = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Switch switch1 = findViewById(R.id.nightmode);
        SharedPreferences prefs = getSharedPreferences("database", MODE_PRIVATE);
        night = prefs.getInt("night", 1);
        if (night == 1) {
            switch1.setChecked ( false );
        } else {
            switch1.setChecked ( true );
        }
        switch1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(), "Włączono tryb nocny", Toast.LENGTH_LONG).show();
                    SharedPreferences prefs = getSharedPreferences("database", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("night", 2);
                    editor.commit();

                } else {
                    Toast.makeText(getApplicationContext(), "Wyłączono tryb nocny", Toast.LENGTH_LONG).show();
                    SharedPreferences prefs = getSharedPreferences("database", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("night", 1);
                    editor.commit();

                }
            }
        });
    }
}