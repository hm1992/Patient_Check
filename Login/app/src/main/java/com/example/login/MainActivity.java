package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button managementButton = findViewById(R.id.managementButton);
        Button patientButton = findViewById(R.id.patientbutton);

        Intent intent=getIntent();
        String userID=intent.getStringExtra("userID");

        if(!userID.equals("admin")){
            managementButton.setVisibility(View.GONE);
        }

        managementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTask_user().execute();

            }
        });
        patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTask_patient().execute();
            }
        });
    }
    class BackgroundTask_user extends AsyncTask<Void,Void,String>
    {
        String target;
        @Override
        protected void onPreExecute()
        {
            target ="http://mjk4845.cafe24.com/List.php";
        }
        @Override
        protected String doInBackground(Void... voids){
            try{
                URL url= new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder =new StringBuilder();
                while((temp =bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(temp+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        public void onProgressUpdate(Void...values)
        {
            super.onProgressUpdate(values);
        }
        @Override
        public void onPostExecute(String result)
        {
            Intent intent = new Intent(MainActivity.this, ManagementActivity.class);
            intent.putExtra("userList",result);
            MainActivity.this.startActivity(intent);
        }
    }
    class BackgroundTask_patient extends AsyncTask<Void,Void,String>
    {
        String target_patient;
        @Override
        protected void onPreExecute()
        {
            target_patient ="http://mjk4845.cafe24.com/patient_List.php";
        }
        @Override
        protected String doInBackground(Void... voids){
            try{
                URL P_url= new URL(target_patient);
                HttpURLConnection httpURLConnection = (HttpURLConnection) P_url.openConnection();
                InputStream P_inputStream = httpURLConnection.getInputStream();
                BufferedReader P_bufferedReader= new BufferedReader(new InputStreamReader(P_inputStream));
                String temp_patient;
                StringBuilder stringBuilder =new StringBuilder();
                while((temp_patient =P_bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(temp_patient+"\n");
                }
                P_bufferedReader.close();
                P_inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        public void onProgressUpdate(Void...values)
        {
            super.onProgressUpdate(values);
        }
        @Override
        public void onPostExecute(String result)
        {
            Intent intent = new Intent(MainActivity.this, patientManagementActivity.class);
            intent.putExtra("patientList",result);
            MainActivity.this.startActivity(intent);
        }
    }
}
