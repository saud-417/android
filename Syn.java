package com.codingelab.tutorial;

import android.os.AsyncTask;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class Syn extends AsyncTask<String,Void,String> {

    ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
    @Override
    protected String doInBackground(String ... params) {
        if(params.length>0){
            if(params[0].equalsIgnoreCase("syn")){
                try {
                    onSyn(params);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if(params[0].equalsIgnoreCase("insert")){
               return onInsert(params);
            }else if(params[0].equalsIgnoreCase("update")){
                return onUpdate(params);
            }else if(params[0].equalsIgnoreCase("delete")){
                return onDelete(params);
            }else if(params[0].equalsIgnoreCase("read")){
                return Read(params);
            }
        }
        return null;
    }
    private String onInsert(String ... params){
        String phpPageULR="http://172.20.10.4:80/sqli/mysql_write.php";
        try {
            // preparing the URL for the connection
            URL url=new URL(phpPageULR);
            // open a channel between the client(android device) and the server (PHP)
            HttpURLConnection channel=(HttpURLConnection) url.openConnection();
            // specify what do you need post or get method
            channel.setRequestMethod("POST");
            channel.setDoOutput(true);
            // create a sub-channel inside the channel to specify weither you want to write or read
            // output means write, input means read
            OutputStream subChannel=channel.getOutputStream();
            // create a pen to write in a specific information and inx which language should this pen write.
            OutputStreamWriter pen=new OutputStreamWriter(subChannel,"UTF-8");
            // create a object to start write the information
            BufferedWriter student =new BufferedWriter(pen);
            // information to write
            String userid = URLEncoder.encode("userid", "UTF-8") + "=" + URLEncoder.encode("-1", "UTF-8");
            String name = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
            String phone = URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");
            String email = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8");
            String operation = URLEncoder.encode("operation", "UTF-8") + "=" + URLEncoder.encode("insert", "UTF-8");
            String information=userid+"&"+name+"&"+phone+"&"+email+"&"+operation;
            // student will start writing the information
            student.write(information);
            // student will push the information from the client side to the server side
            student.flush();
            // student finished his job
            student.close();
            System.out.println(params[1]);
            System.out.println(params[2]);
            System.out.println(params[3]);
            // closing the sub-channel
            subChannel.close();
            InputStream serverResponse = channel.getInputStream();
            serverResponse.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Insert data to MySQL";
    }

    private String onSyncInsert(String ... params){
        String phpPageULR="http://172.20.10.4:80/sqli/mysql_write.php";
        try {
            // preparing the URL for the connection
            URL url=new URL(phpPageULR);
            // open a channel between the client(android device) and the server (PHP)
            HttpURLConnection channel=(HttpURLConnection) url.openConnection();
            // specify what do you need post or get method
            channel.setRequestMethod("POST");
            channel.setDoOutput(true);
            // create a sub-channel inside the channel to specify weither you want to write or read
            // output means write, input means read
            OutputStream subChannel=channel.getOutputStream();
            // create a pen to write in a specific information and inx which language should this pen write.
            OutputStreamWriter pen=new OutputStreamWriter(subChannel,"UTF-8");
            // create a object to start write the information
            BufferedWriter student =new BufferedWriter(pen);
            // information to write
            String userid = URLEncoder.encode("userid", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
            String name = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");
            String phone = URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8");
            String email = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8");
            String operation = URLEncoder.encode("operation", "UTF-8") + "=" + URLEncoder.encode("insert", "UTF-8");
            String information=userid+"&"+name+"&"+phone+"&"+email+"&"+operation;
            // student will start writing the information
            student.write(information);
            // student will push the information from the client side to the server side
            student.flush();
            // student finished his job
            student.close();
            System.out.println(params[1]);
            System.out.println(params[2]);
            System.out.println(params[3]);
            // closing the sub-channel
            subChannel.close();
            InputStream serverResponse = channel.getInputStream();
            serverResponse.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Insert data to MySQL";
    }



    private String onUpdate(String ... params){
        String phpPageULR="http://172.20.10.4:80/sqli/mysql_write.php";
        try {
            // preparing the URL for the connection
            URL url=new URL(phpPageULR);
            // open a channel between the client(android device) and the server (PHP)
            HttpURLConnection channel=(HttpURLConnection) url.openConnection();
            // specify what do you need post or get method
            channel.setRequestMethod("POST");
            channel.setDoOutput(true);
            // create a sub-channel inside the channel to specify weither you want to write or read
            // output means write, input means read
            OutputStream subChannel=channel.getOutputStream();
            // create a pen to write in a specific information and inx which language should this pen write.
            OutputStreamWriter pen=new OutputStreamWriter(subChannel,"UTF-8");
            // create a object to start write the information
            BufferedWriter student =new BufferedWriter(pen);
            // information to write
            String userid = URLEncoder.encode("userid", "UTF-8") + "=" +URLEncoder.encode(params[4], "UTF-8");
            String name = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
            String phone = URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");
            String email = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8");
            String operation = URLEncoder.encode("operation", "UTF-8") + "=" + URLEncoder.encode("update", "UTF-8");
            String information=userid+"&"+name+"&"+phone+"&"+email+"&"+operation;
            // student will start writing the information
            student.write(information);
            // student will push the information from the client side to the server side
            student.flush();
            // student finished his job
            student.close();
            System.out.println(params[1]);
            System.out.println(params[2]);
            System.out.println(params[3]);
            // closing the sub-channel
            subChannel.close();
            InputStream serverResponse = channel.getInputStream();
            serverResponse.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Data Updated";
    }

    private String onDelete(String ... params){
        String phpPageULR="http://172.20.10.4:80/sqli/mysql_write.php";
        try {
            // preparing the URL for the connection
            URL url=new URL(phpPageULR);
            // open a channel between the client(android device) and the server (PHP)
            HttpURLConnection channel=(HttpURLConnection) url.openConnection();
            // specify what do you need post or get method
            channel.setRequestMethod("POST");
            channel.setDoOutput(true);
            // create a sub-channel inside the channel to specify weither you want to write or read
            // output means write, input means read
            OutputStream subChannel=channel.getOutputStream();
            // create a pen to write in a specific information and in which language should this pen write.
            OutputStreamWriter pen=new OutputStreamWriter(subChannel,"UTF-8");
            // create a object to start write the information
            BufferedWriter student =new BufferedWriter(pen);
            // information to write
            String userid = URLEncoder.encode("userid", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
            String operation = URLEncoder.encode("operation", "UTF-8") + "=" + URLEncoder.encode("delete", "UTF-8");

            String information=userid+"&"+operation;
            // student will start writing the information
            student.write(information);
            // student will push the information from the client side to the server side
            student.flush();
            // student finished his job
            student.close();
            System.out.println("--ID-->"+params[1]);
            // closing the sub-channel
            subChannel.close();
            InputStream serverResponse = channel.getInputStream();
            serverResponse.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Delete data from MySQL";
    }


    private String Read(String ... params){
        String phpPageULR="http://172.20.10.4:80/sqli/mysql_read.php";
        try {
            // preparing the URL for the connection
            URL url=new URL(phpPageULR);
            // open a channel between the client(android device) and the server (PHP)
            HttpURLConnection channel=(HttpURLConnection) url.openConnection();
            // specify what do you need post or get method
            channel.setRequestMethod("POST");
            channel.setDoOutput(true);
            // create a sub-channel inside the channel to specify weither you want to write or read
            // output means write, input means read
            OutputStream subChannel=channel.getOutputStream();
            // create a pen to write in a specific information and in which language should this pen write.
            OutputStreamWriter pen=new OutputStreamWriter(subChannel,"UTF-8");
            // create a object to start write the information
            BufferedWriter student =new BufferedWriter(pen);
            // information to write
            String delete = URLEncoder.encode("delete", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
            String information=delete;
            // student will start writing the information
            student.write(information);
            // student will push the information from the client side to the server side
            student.flush();
            // student finished his job
            student.close();
            System.out.println("--ID-->"+params[1]);
            // closing the sub-channel
            subChannel.close();
            InputStream serverResponse = channel.getInputStream();
            serverResponse.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Delete data from MySQL";
    }


    private void onSyn(String ... params) throws JSONException {
        //JSONArray jsonArray = new JSONArray(params[1]);
        //String[] heroes = new String[jsonArray.length()];
            String idd = params[1];
            //map.put("id",idd);
            String name = params[2];
            //map.put("name",name);
            String phone = params[3];
            //map.put("phone",phone);
            String email = params[4];
            //map.put("email",email);
            //Items.add(map);

        onSyncInsert("insert", idd, name, phone, email);





    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}

