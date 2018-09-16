package com.example.a1794001.travel;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText text,text2,text5,text6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

        text=(EditText) findViewById(R.id.editText);
        text2=(EditText)findViewById(R.id.editText2);
        text5=(EditText)findViewById(R.id.editText5);
        text6=(EditText)findViewById(R.id.editText6);


        final Spinner dropDown = (Spinner) findViewById(R.id.spinner);
        String[] mode = new  String[]{"driving" , "walking" , "bicycling"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,mode);
        dropDown.setAdapter(adapter);



    }
    public void nextPage(View myView){




        Intent myIntent = new Intent(this,Main2Activity.class);
        myIntent.putExtra("OLON",Double.parseDouble(text.getText().toString()));
        myIntent.putExtra("OLAT",Double.parseDouble(text2.getText().toString()));
        myIntent.putExtra("DLON",Double.parseDouble(text5.getText().toString()));
        myIntent.putExtra("DLAT",Double.parseDouble(text6.getText().toString()));

        startActivity(myIntent);





    }


        }


