package com.example.loubna.mgeomap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class UserAccount extends AppCompatActivity {

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";

    private Button parks ;
    private  Button direction;
    private SharedPreferences mPreferences;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        parks = (Button)findViewById(R.id.parks);
        direction = (Button)findViewById(R.id.direction);
        text = (TextView)findViewById(R.id.name);
        mPreferences = getPreferences(MODE_PRIVATE);
        String name = mPreferences.getString("name",null);

       // String name = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
        text.setText(name);

        parks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserAccount.this," Welcome ",Toast.LENGTH_SHORT).show();

                //
                // mPreferences.edit().putString("name",name.getText().toString()).apply();

                Intent secondActivity = new Intent(UserAccount.this,MainActivity.class);
                startActivity(secondActivity);


            }
        });
        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserAccount.this," Welcome ",Toast.LENGTH_SHORT).show();
                Intent secondActivity = new Intent(UserAccount.this,MapsActivity.class);
                startActivity(secondActivity);


            }
        });

    }
}
