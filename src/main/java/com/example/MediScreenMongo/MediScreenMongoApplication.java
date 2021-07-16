package com.example.MediScreenMongo;

import com.example.MediScreenMongo.repository.PatientsNoteRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories(basePackageClasses = PatientsNoteRepository.class)
public class MediScreenMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediScreenMongoApplication.class, args);
	}

}
