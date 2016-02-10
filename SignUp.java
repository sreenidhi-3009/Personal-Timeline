package com.spark.android.personaltimeline;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import java.sql.SQLException;


public class SignUp extends ActionBarActivity {

//activity for sign up into the application

    EditText editTextPassword, editTextConfirmPassword;
    Button btnCreateAccount;
    //Hint Button to show dialog box
    private Button buttonHint;
    // textview result to show input hint in the dialog
    private TextView resultText;

    LoginDataBaseAdapter loginDataBaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        final Context context = this;
        //on creation of this activity create an instance
        //of database class to create the user password an confirm password
        // fields by getting the data from the text field converted them as text.

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        try {
            loginDataBaseAdapter = loginDataBaseAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // get reference of the views
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPasscode);
        buttonHint = (Button) findViewById(R.id.buttonHint);
        resultText = (TextView) findViewById(R.id.resultText);

        buttonHint.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });


        btnCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // TODO Auto-generated method stub

                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();
                String resultHint= resultText.getText().toString();

                // check if any of the fields are vacant
                if (password.equals("") || confirmPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field Vacant", Toast.LENGTH_LONG).show();
                    return;
                }
                // check if both password matches
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    // Save the Data in Database
                    loginDataBaseAdapter.insertEntry(password,resultHint);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, Login.class);
                    startActivity(intent);

                }
            }

        });
    }

    //show dialog box to set Hint
    protected void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(SignUp.this);
        View promptView = layoutInflater.inflate(R.layout.activity_input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SignUp.this);
        alertDialogBuilder.setView(promptView);

        final EditText editTextHint = (EditText) promptView.findViewById(R.id.editTextSetHint);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        resultText.setText("Hello, " + editTextHint.getText());
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void onDestroy() {
        super.onDestroy();
        loginDataBaseAdapter.close();
    }
}
