package com.spark.android.personaltimeline;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;


public class Note extends ActionBarActivity {

//the activity that helps in creating a note on a particular chosen day
    Button showText;
    Button reset;
    EditText editText;
    Button save;
    LoginDataBaseAdapter loginDataBaseAdapter;

    /*SQLiteDatabase db;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        showText = (Button) findViewById(R.id.datebutton);
        editText = (EditText) findViewById(R.id.body);
        // used to get the date from previous activity
        Bundle bundle = getIntent().getExtras();
        final String date = bundle.getString("date");
        final String body= bundle.getString("note");
        //final String body = bundle.getString("body");
        showText.setText(date);
        editText.setText(body);
        int position =editText.length();
        Editable text=editText.getText();
        Selection.setSelection(text,position);
      loginDataBaseAdapter = new LoginDataBaseAdapter(getApplicationContext());
        try {
            loginDataBaseAdapter = loginDataBaseAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //implementing save
/*
       mDbHelper = new NotesDbAdapter(this);
            mDbHelper = mDbHelper.open();
       String date=mDbHelper.getSingleEntry(stuff);

        if(date.equals(stuff)){
           final String metabody=mDbHelper.getBody(date);
            editText.setText(metabody);
        }*/
        /*final Context mCtx=getApplicationContext();
        final NotesDbAdapter note=new NotesDbAdapter(mCtx);*/
            save = (Button) findViewById(R.id.savebutton);
            save.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                  String body = editText.getText().toString();
                  /*  loginDataBaseAdapter. updateNote(showText.getText().toString(),editText.getText().toString());*/
                    /*loginDataBaseAdapter.insertNote(date, body);
                    loginDataBaseAdapter.close();*/
                    loginDataBaseAdapter.insertNote(date,body);
                    Toast.makeText(Note.this, "SAVED", Toast.LENGTH_LONG).show();



                    //*Intent intent = new Intent(getApplicationContext(), MyCalendarActivity.class);
                    /*startActivity(intent);*//**//**/


                }
            });

            reset = (Button) findViewById(R.id.resetbutton);
            reset.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    editText.setText(" ");
}});}}
