package com.example.login;

public class Patient {
    String patientID;
    String patientLocation;
    String patientStatus;


    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPatientLocation() {
        return patientLocation;
    }

    public void setPatientLocation(String patientLocation) {
        this.patientLocation = patientLocation;
    }

    public String getPatientStatus() {
        return patientStatus;
    }

    public void setPatientStatus(String patientStatus) {
        this.patientStatus = patientStatus;
    }


    public Patient(String patientID, String patientLocation, String patientStatus) {
        this.patientID = patientID;
        this.patientLocation = patientLocation;
        this.patientStatus = patientStatus;
    }
}
