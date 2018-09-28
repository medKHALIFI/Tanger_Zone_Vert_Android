package com.example.loubna.mgeomap;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Accueil extends AppCompatActivity {

    private Button bouton1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        bouton1 = (Button)findViewById(R.id.button5);

        bouton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Accueil.this," Welcome ",Toast.LENGTH_SHORT).show();

                //
                // mPreferences.edit().putString("name",name.getText().toString()).apply();

                Intent secondActivity = new Intent(Accueil.this,Main2Activity.class);
                startActivity(secondActivity);


            }
        });
}
}




