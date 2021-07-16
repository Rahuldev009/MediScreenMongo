package com.example.MediScreenMongo.service;

import com.example.MediScreenMongo.model.PatientNote;

import java.util.List;
import java.util.Optional;

public interface PatientNoteService {

    public PatientNote getPatientById(String id);

    public void savePatient(PatientNote patientNote);

    public void updatePatient(PatientNote patientNote);

    public void deletePatient(String id);

    public List<PatientNote> getAllPatients();

}
