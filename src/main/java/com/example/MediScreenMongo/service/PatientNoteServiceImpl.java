package com.example.MediScreenMongo.service;

import com.example.MediScreenMongo.dto.PatientNoteDto;
import com.example.MediScreenMongo.model.PatientNote;
import com.example.MediScreenMongo.repository.PatientsNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientNoteServiceImpl implements PatientNoteService {

    private PatientsNoteRepository patientsNoteRepository;

    @Autowired
    public PatientNoteServiceImpl(PatientsNoteRepository patientsNoteRepository) {
        this.patientsNoteRepository = patientsNoteRepository;
    }

    /**
     * Note on the basis of ID
     * @param id
     * @return patientNote
     */
    @Override
    public PatientNote getPatientById(String id) {

        PatientNote patientNote =  patientsNoteRepository.findById(id).get();
        return patientNote;
    }

    /**
     * create a new entry in DB
     * @param patientNote
     */
    @Override
    public void savePatient(PatientNote patientNote) {
        patientsNoteRepository.save(patientNote);
    }

    /**
     * Update patient entry
     * @param patientNote
     */
    @Override
    public void updatePatient(PatientNote patientNote) {
        patientsNoteRepository.save(patientNote);
    }

    /**
     * Delete patient entry on the basis of ID
     * @param id
     */
    @Override
    public void deletePatient(String id) {
        patientsNoteRepository.deleteById(id);
    }

    /**
     * List of all notes
     * @return List
     */
    @Override
    public List<PatientNote> getAllPatients() {
        return patientsNoteRepository.findAll();
    }

    /**
     * Note on the basis of patID
     * @param id
     * @return patientNote
     */
    @Override
    public PatientNote getByPatId(int id) {
       return patientsNoteRepository.getByPatId(id);
    }

    /**
     * all notes by patID
     * @param id
     * @return List
     */
    @Override
    public List<PatientNoteDto> getAllByPatId(int id) {
       return patientsNoteRepository.getAllByPatId(id);
    }

}
