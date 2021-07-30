package com.example.MediScreenMongo.controller;

import com.example.MediScreenMongo.dto.PatientNoteDto;
import com.example.MediScreenMongo.model.PatientNote;
import com.example.MediScreenMongo.service.PatientNoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MediScreenMongoController {

    private Logger logger = LoggerFactory.getLogger(MediScreenMongoController.class);
    private PatientNoteService patientNoteService;

    @Autowired
    public MediScreenMongoController(PatientNoteService patientNoteService) {
        this.patientNoteService = patientNoteService;
    }

    /**
     * HTTP POST request to add Patient notes in the DB
     * @param patId
     * @param note
     * @return String
     */
    @PostMapping("/patHistory/add")
    public String addPatientsNotes(@RequestParam int patId, @RequestParam String note) {
        PatientNote patientNote = new PatientNote();
        patientNote.setPatId(patId);
        patientNote.setNote(note);
        patientNoteService.savePatient(patientNote);
        return "data saved";
    }

    /**
     * This is the home page - shows data of all patients
     * @param model
     * @return ModelAndView
     */
    @RequestMapping("/")
    public ModelAndView adminHome(Model model) {
        logger.info("Home page of patient notes ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/patHistory/list");
        return modelAndView;
    }

    /**
     * HTTP GET request shows list of all patientsNotes present in Database
     * @param model
     * @return ModelAndView
     */
    @RequestMapping("/patHistory/list")
    public ModelAndView home(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        List<PatientNote> patientNotes = patientNoteService.getAllPatients();
        model.addAttribute("patientNotes", patientNotes);
        logger.info("all patientsNotes list"+ patientNotes.toString());
        modelAndView.setViewName("patHistory/list");
        return modelAndView;
    }

    /**
     * HTTP GET request loads form for new patient data entry
     * @param patientNote
     * @return
     */
    @GetMapping("/patHistory/add")
    public ModelAndView addPatient(PatientNote patientNote) {
        logger.info("patientNote to be added ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("patientNote", patientNote);
        modelAndView.setViewName("patHistory/add");
        return modelAndView;
    }

    /**
     * HTTP POST request validates the data and if correct create a new entry in DB
     * @param patientNote
     * @param result
     * @param model
     * @return ModelAndView
     */
    @PostMapping("/patHistory/validate")
    public ModelAndView validate(@Valid PatientNote patientNote, BindingResult result, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        if (!result.hasErrors()) {
            logger.info("patientNote to be added "+ patientNote.toString());
            patientNoteService.savePatient(patientNote);
            model.addAttribute("patientNotes", patientNoteService.getAllPatients());
            modelAndView.setViewName("redirect:/patHistory/list");
            return modelAndView;
        }
        modelAndView.setViewName("patHistory/add");
        return modelAndView;
    }

    /**
     * HTTP GET request loads form for updating the note entry
     * @param id
     * @param model
     * @return ModelAndView
     */
    @GetMapping("/patHistory/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") String id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        PatientNote patientNote = patientNoteService.getPatientById(id);
        logger.info("patient to be added "+ patientNote.toString());
        model.addAttribute("patientNote", patientNote);
        modelAndView.setViewName("patHistory/update");
        return modelAndView;
    }

    /**
     * HTTP POST request validates the data and if correct, update the DB
     * @param id
     * @param patientNote
     * @param result
     * @param model
     * @return ModelAndView
     */
    @PostMapping("/patHistory/update/{id}")
    public ModelAndView updatePatientNote(@PathVariable("id") String id, @Valid PatientNote patientNote,
                                  BindingResult result, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("patHistory/update");
            return modelAndView;
        }
        patientNote.setId(id);
        logger.info("patient to be updated "+ patientNote.toString());
        patientNoteService.updatePatient(patientNote);
        model.addAttribute("patientNotes", patientNoteService.getAllPatients());
        modelAndView.setViewName("redirect:/patHistory/list");
        return modelAndView;
    }

    /**
     * HTTP GET request deletes the requested patient entry from DB
     * @param id
     * @param model
     * @return ModelAndView
     */
    @GetMapping("/patHistory/delete/{id}")
    public ModelAndView deletePatient(@PathVariable("id") String id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        logger.info("patient to be deleted "+ patientNoteService.getPatientById(id).toString());
        patientNoteService.deletePatient(id);
        model.addAttribute("patientNotes", patientNoteService.getAllPatients());
        modelAndView.setViewName("redirect:/patHistory/list");
        return modelAndView;
    }

    /**
     * HTTP GET request to return the request patient data on the basis of ID
     * @param patId
     * @return List
     */
    @GetMapping("/getPatientNotes")
    public List<PatientNoteDto> getPatientNotes(@RequestParam int patId) {
        List<PatientNoteDto> patientNoteList = patientNoteService.getAllByPatId(patId);
        return patientNoteList;
    }
    
}
