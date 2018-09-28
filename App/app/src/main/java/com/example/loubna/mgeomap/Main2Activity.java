package com.example.loubna.mgeomap;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    USERDATABASE myDb;
    private SharedPreferences mPreferences;

    private  Button bouton1 ;
    private  Button bouton3 ;
    private  Button bouton4 ;
    private EditText pass ;
    private EditText name ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mPreferences = getPreferences(MODE_PRIVATE);

        bouton1 = (Button)findViewById(R.id.button1);
        name = (EditText)findViewById(R.id.editText);
        pass = (EditText) findViewById(R.id.editText2);
        bouton3 = (Button)findViewById(R.id.button3);
        bouton4 = (Button)findViewById(R.id.button4);
        myDb = new USERDATABASE(this);

        // lauthentification
        bouton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = myDb.getReadableDatabase();

                Cursor res = db.rawQuery("SELECT * FROM student_table WHERE NAME = '"+name.getText().toString()+"' and PASS= '"+pass.getText().toString()+"'", null);

                    if(res.getCount() == 0) {
                        // show message
                        showMessage("Not found","username or password incorrect");
                        return;
                    }
                    else {

                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("");
                       // buffer.append(" " + res.getString(1) + " ");
                        buffer.append(" " + res.getString(2) + "\n");
                       // buffer.append("Marks :" + res.getString(3) + "\n\n");
                        mPreferences.edit().putString("name",res.getString(2)).apply();
                        // End the activity
                        Intent intent = new Intent();
                        //pour envoi  les parametre au mainActivity
                        intent.putExtra("BUNDLE EXTRA SCORE",res.getString(2));
                        setResult(RESULT_OK, intent);
                    }

                    showMessage("Welcome", buffer.toString());
                    Intent secondActivity = new Intent(Main2Activity.this, UserAccount.class);
                        startActivityForResult(secondActivity, 42);
                }}

        });

        //créer votre compte
        bouton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this," Create your account ",Toast.LENGTH_LONG).show();

                Intent secondActivity = new Intent(Main2Activity.this,InscriptionUSER.class);
                startActivity(secondActivity);


            }
        });

        //pour ladministrateur
        bouton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // mPreferences.edit().putString("name",name.getText().toString()).apply();
                if (name.getText().toString().equals("admin") && pass.getText().toString().equals("pass")) {
                    Toast.makeText(Main2Activity.this," Welcome Admin ",Toast.LENGTH_SHORT).show();
                    Intent secondActivity = new Intent(Main2Activity.this, Admin.class);
                    startActivity(secondActivity);
                }else{
                    Toast.makeText(Main2Activity.this," You're not the admin",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
