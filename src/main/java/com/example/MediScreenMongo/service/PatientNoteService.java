package com.example.MediScreenMongo.service;

import com.example.MediScreenMongo.dto.PatientNoteDto;
import com.example.MediScreenMongo.model.PatientNote;

import java.util.List;

public interface PatientNoteService {

    public PatientNote getPatientById(String id);

    public void savePatient(PatientNote patientNote);

    public void updatePatient(PatientNote patientNote);

    public void deletePatient(String id);

    public List<PatientNote> getAllPatients();

    public PatientNote getByPatId(int id);

    List<PatientNoteDto> getAllByPatId(int id);
}
