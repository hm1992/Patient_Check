package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class patientManagementActivity extends AppCompatActivity {
    private ListView listView_patient;
    private PatientListAdapter patient_adapter;
    private List<Patient> patientList;
    private List<Patient> patient_saveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientmangement);
        Intent intent = getIntent();

        listView_patient = findViewById(R.id.ListView_patient);
        patientList = new ArrayList<Patient>();
        patient_saveList = new ArrayList<Patient>();

        patient_adapter = new PatientListAdapter(getApplicationContext(), patientList, this, patient_saveList);
        listView_patient.setAdapter(patient_adapter);
        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("patientList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            String patientID, patientLocation, patientStatus;
            while (count < jsonArray.length()) {
                JSONObject object = jsonArray.getJSONObject(count);
                patientID = object.getString("patientID");
                patientLocation = object.getString("patientLocation");
                patientStatus = object.getString("patientStatus");
                Patient patient = new Patient(patientID, patientLocation,patientStatus);

                patientList.add(patient);
                patient_saveList.add(patient);

                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        EditText search = (EditText) findViewById(R.id.search_patient);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchUser(charSequence.toString());//회원 검색 기능용
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    public void searchUser(String search) {
        patientList.clear();
        for (int i = 0; i < patient_saveList.size(); i++) {
            if (patient_saveList.get(i).getPatientID().contains(search)) {//contains메소드로 search 값이 있으면 true를 반환함
                patientList.add(patient_saveList.get(i));
            }
        }
        patient_adapter.notifyDataSetChanged();//어댑터에 값일 바뀐것을 알려줌
    }
}
