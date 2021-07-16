package com.example.MediScreenMongo.service;

import com.example.MediScreenMongo.model.PatientNote;
import com.example.MediScreenMongo.repository.PatientsNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientNoteServiceImpl implements PatientNoteService {

    @Autowired
    private PatientsNoteRepository patientsNoteRepository;

    @Override
    public PatientNote getPatientById(String id) {

        PatientNote patientNote =  patientsNoteRepository.findById(id).get();
        return patientNote;
    }

    @Override
    public void savePatient(PatientNote patientNote) {
        patientsNoteRepository.save(patientNote);
    }

    @Override
    public void updatePatient(PatientNote patientNote) {
        patientsNoteRepository.save(patientNote);
    }

    @Override
    public void deletePatient(String id) {
        patientsNoteRepository.deleteById(id);
    }

    @Override
    public List<PatientNote> getAllPatients() {
        return patientsNoteRepository.findAll();
    }
}
