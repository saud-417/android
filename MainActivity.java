package com.codingelab.tutorial;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    private Button onSyn;
    private Button insert;
    private Button showData;
    private Button btnUpdate;

    private Button btnSqlLite;

    private Syn syn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //sync db

        this.onSyn=(Button)findViewById(R.id.onSyn);
        this.syn=new Syn();


        this.onSyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
            //Delete all data in the database on the server
                  TruncateServerTable();
           //Enter all data in the local database into the server
                    SyncData();
                    Toast.makeText(MainActivity.this, "All Data Synchronized", Toast.LENGTH_SHORT).show();

                }
                catch(Exception ex)
                {
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });

        //Input button
        this.insert=(Button)findViewById(R.id.insertdata);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(MainActivity.this, phpdbActivity.class);
                startActivity(updateStudent);
            }
        });

        this.showData=(Button)findViewById(R.id.showData);
        showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent show = new Intent(MainActivity.this, ListviewActivity.class);
                startActivity(show);
            }
        });

     //Modify button, opens a list of data, after clicking on any row, opens the modification screen
        this.btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent update = new Intent(MainActivity.this, Listviewupdate.class);
                startActivity(update);
            }
        });

      //Open the local database
        btnSqlLite = (Button) findViewById(R.id.btnLocalDb);
        final Intent sqlLiteIntent = new Intent(this, SQLitedb.class);
        btnSqlLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(sqlLiteIntent);
            }
        });

    }

    private void getJSON(final String urlWebService) {
        /*the json string is a network operation
         * And we cannot perform a network operation in main thread
         * so we need an AsyncTask
         * The constrains defined here are
         * Void -> We are not passing anything
         * Void -> Nothing at progress update as well
         * String -> After completion it should return a string and it will be the json string
         * */
        class GetJSON extends AsyncTask<Void, Void, String> {

            //this method will be called before execution
            //you can display a progress bar or something
            //so that user can understand that he should wait
            //as network operation may take some time
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            //this method will be called after execution
            //so here we are displaying a toast with the json string
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }

            //in this method we are fetching the json string
            @Override
            protected String doInBackground(Void... voids) {



                try {
                    //creating a URL
                    URL url = new URL(urlWebService);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //StringBuilder object to read the string from the service
                    StringBuilder sb = new StringBuilder();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    //A simple string to read values from each line
                    String json;

                    //reading until we don't find null
                    while ((json = bufferedReader.readLine()) != null) {

                        //appending it to string builder
                        sb.append(json + "\n");
                    }

                    //finally returning the read string
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }

            }
        }

        //creating asynctask object and executing it
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void TruncateServerTable() {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

         //Message All data user delete
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
               // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL("http://172.20.10.4:80/sqli/mysql_truncate.php");

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void SyncData()
    {
        DBHelper mydb = new DBHelper(this);
        //String[] dbValues = new String[] {"id", "name", "phone","email"};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.colmn_row,
                mydb.getData(),
                new String[]{"id", "name","phone","email"},
                null);


        for(int i = 0 ; i < adapter.getCount();i++) {
            syn.doInBackground("syn",mydb.getAllContactsIDs().get(i),mydb.getAllContactsNames().get(i),mydb.getAllContactsPhones().get(i),mydb.getAllContactsEmails().get(i));

        }

    }

}
