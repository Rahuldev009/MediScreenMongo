package com.example.MediScreenMongo.repository;

import com.example.MediScreenMongo.model.PatientNote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientsNoteRepository extends MongoRepository<PatientNote, String> {


}
