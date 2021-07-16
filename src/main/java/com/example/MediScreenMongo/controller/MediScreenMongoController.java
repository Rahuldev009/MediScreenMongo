package com.example.MediScreenMongo.controller;

import com.example.MediScreenMongo.model.PatientNote;
import com.example.MediScreenMongo.service.PatientNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class MediScreenMongoController {

    @Autowired
    PatientNoteService patientNoteService;

    @RequestMapping("/test")
    public String testMethod() {
        return "hello world";
    }

//    @PostMapping("/patientNotes/add")
//    public String addPatientsNotes(@RequestParam int patId, @RequestParam String note) {
//        PatientNote patientNote = new PatientNote();
//        patientNote.setPatId(patId);
//        patientNote.setNote(note);
//        patientNoteService.savePatient(patientNote);
//        return "Data added";
//    }

    @RequestMapping("/")
    public String adminHome(Model model) {
        //
        return "redirect:/patHistory/list";
    }

    @RequestMapping("/patHistory/list")
    public String home(Model model) {
        List<PatientNote> patientNotes = patientNoteService.getAllPatients();
        model.addAttribute("patientNotes", patientNotes);
        //
        return "patHistory/list";
    }

    @GetMapping("/patHistory/add")
    public ModelAndView addPatient(PatientNote patientNote) {
        // logger.info("patient to be added ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("patientNote", patientNote);
        modelAndView.setViewName("/patHistory/add");
        return modelAndView;
    }


    @PostMapping("/patHistory/validate")
    public ModelAndView validate(@Valid PatientNote patientNote, BindingResult result, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        if (!result.hasErrors()) {
            //
            patientNoteService.savePatient(patientNote);
            model.addAttribute("patientNotes", patientNoteService.getAllPatients());
            modelAndView.setViewName("redirect:/patHistory/list");
            return modelAndView;
        }
        modelAndView.setViewName("/patHistory/add");
        return modelAndView;
    }

    @GetMapping("/patHistory/update/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        PatientNote patientNote = patientNoteService.getPatientById(id);
        //
//        model.addAttribute("patientNote", patientNote);
        model.addAttribute("patientNote", patientNote);
        return "patHistory/update";
    }

    @PostMapping("/patHistory/update/{id}")
    public String updateBid(@PathVariable("id") String id, @Valid PatientNote patientNote,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "patHistory/update";
        }
        patientNote.setId(id);
        //
        patientNoteService.updatePatient(patientNote);
        model.addAttribute("patientNotes", patientNoteService.getAllPatients());
        return "redirect:/patHistory/list";
    }

    @GetMapping("/patHistory/delete/{id}")
    public String deletePatient(@PathVariable("id") String id, Model model) {
        //
        patientNoteService.deletePatient(id);
        model.addAttribute("patientNotes", patientNoteService.getAllPatients());
        return "redirect:/patHistory/list";
    }
}
