package com.example.MediScreenMongo.repository;

import com.example.MediScreenMongo.dto.PatientNoteDto;
import com.example.MediScreenMongo.model.PatientNote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PatientsNoteRepository extends MongoRepository<PatientNote, String> {

 PatientNote getByPatId(int id);

 List<PatientNoteDto> getAllByPatId(int id);

}
