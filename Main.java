package com.spark.android.personaltimeline;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.app.Dialog;
import java.sql.SQLException;

public class Main extends ActionBarActivity {
//main class where the android application begins
        Button btnSignIn,btnSignUp;
        LoginDataBaseAdapter loginDataBaseAdapter;

@Override
protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            //code to launch one time signup
            // Get The Reference Of Buttons
        btnSignIn=(Button)findViewById(R.id.buttonSignIN);
        btnSignUp=(Button)findViewById(R.id.buttonSignUp);
        // Set OnClick Listener on SignUp button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
public void onClick(View v) {
        // TODO Auto-generated method stub
        /// Create Intent for SignUpActivity  and Start The Activity
        Intent intentSignUP=new Intent(getApplicationContext(),SignUp.class);
        startActivity(intentSignUP);
        }
        });
        /*}
        // Method to handleClick Event of Sign In Button
        public void signIn(View V)
        {*/

        // Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener() {

public void onClick(View v) {

         Intent intentSignIn=new Intent(getApplicationContext(),Login.class);
        startActivity(intentSignIn);
        }}); }}
