package com.example.login;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

public class PatientListAdapter extends BaseAdapter {

    private Context patient_context;
    private List<Patient> patientList;
    private List<Patient> patient_saveList;
    private Activity patient_parentActivity;
    public PatientListAdapter(Context patient_context, List<Patient> patientList, Activity patient_parentActivity, List<Patient> patient_saveList){
        this.patient_context=patient_context;
        this.patientList=patientList;
        this.patient_parentActivity=patient_parentActivity;
        this.patient_saveList=patient_saveList;
    }

    @Override
    public int getCount() {
        return patientList.size();
    }

    @Override
    public Object getItem(int i) {
        return patientList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View P = View.inflate(patient_context,R.layout.patient,null);
        TextView patientID= (TextView) P.findViewById(R.id.patientID);
        TextView patientLocation = (TextView)P.findViewById(R.id.patientLocation);
        TextView patientStatus = (TextView)P.findViewById(R.id.patientStatus);


        patientID.setText(patientList.get(i).getPatientID());
        patientLocation.setText(patientList.get(i).getPatientLocation());
        patientStatus.setText(patientList.get(i).getPatientStatus());
        P.setTag(patientList.get(i).getPatientID());
        return P;
    }
}

