package com.example.a1794001.travel;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main2Activity extends AppCompatActivity {
      TextView tv1,tv2;
      TableLayout tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

         tv1 = (TextView) findViewById(R.id.textView) ;
         tv2 = (TextView) findViewById(R.id.textView5) ;
         tb=(TableLayout) findViewById(R.id.table) ;

         new myTask().execute();

         TableLayout tb =(TableLayout) findViewById(R.id.table);
        TableRow tr1=new TableRow(this);

        TextView lon = new TextView(this);
        lon.setText("origin_addresses");
        tr1.addView(lon);

        TextView lat = new TextView(this);
        lon.setText("destination_addresses");
        tr1.addView(lat);

        tb.addView(tr1);

         }


        private class myTask extends AsyncTask<Void,Void,Void> {
            JSONArray origArray,destArray,rowArray;
            String origData="";
            String destData="";
            String rowData="";


            protected  Void doInBackground(Void... voids) {
                URL url = null;

                Intent myNewIntent=getIntent();

                Double OLog=myNewIntent.getDoubleExtra("OLON",99);
                Double OLat=myNewIntent.getDoubleExtra("OLAT", 99);
                Double DLon=myNewIntent.getDoubleExtra("DLON",99);
                Double DLat=myNewIntent.getDoubleExtra("DLAT", 99);

                try {

                    url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=Vancouver+BC|Seattle&destinations=San+Francisco|Victoria+BC&mode=bicycling&language=fr-FR&key=AIzaSyARiki0HBLlyR7xH0K3e4eifaSLzx8b-7E");

                    HttpURLConnection address = null;

                     address = (HttpURLConnection) url.openConnection();

                    address.setRequestMethod("GET");

                    int responseCode = address.getResponseCode();

                    System.out.println("\n Sending 'GET' request to URL : " + url);

                    System.out.println("Response Code : " + responseCode);

                    InputStreamReader myInput= new InputStreamReader(address.getInputStream());

                    BufferedReader in = new BufferedReader(myInput);
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    //print result
                    System.out.println(response.toString());

                    JSONObject obj =new JSONObject(response.toString());
                    origArray = obj.getJSONArray("origin_addresses");
                    destArray = obj.getJSONArray("destination_addresses");
                    for(int i=0;i<origArray.length();i++)
                    {
                        origData =origData + origArray.getString(i)+"\n";
                        destData = destData + destArray.getString(i)+"\n";
                    }




                    for(int j=0;j<rowArray.length();j++) {
                        rowData = rowData + rowArray.getString(j) + "\n";
                    }




                }
                catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;

            }

            @Override
            protected void onPostExecute(Void result) {
                tv1.setText(origData);
                tv2.setText(destData);

                super.onPostExecute(result);
            }
        }
}
