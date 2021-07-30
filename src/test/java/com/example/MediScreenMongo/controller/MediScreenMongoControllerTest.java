package com.example.MediScreenMongo.controller;

import com.example.MediScreenMongo.dto.PatientNoteDto;
import com.example.MediScreenMongo.model.PatientNote;
import com.example.MediScreenMongo.service.PatientNoteService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class MediScreenMongoControllerTest {

    @Mock
    PatientNoteService patientNoteService;

    @InjectMocks
    MediScreenMongoController mediScreenMongoController;

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    @Test
    void addPatientsNotes() {
        String s = mediScreenMongoController.addPatientsNotes(21,"Test Note");
        Assert.assertEquals("data saved", s);
    }

    @Test
    void adminHome() {
        ModelAndView modelAndView = mediScreenMongoController.adminHome(model);
        Assert.assertEquals("redirect:/patHistory/list", modelAndView.getViewName());
    }

    @Test
    void home() {
        ModelAndView modelAndView = mediScreenMongoController.home(model);
        Assert.assertEquals("patHistory/list", modelAndView.getViewName());
    }

    @Test
    void addPatient() {
        PatientNote patientNote = new PatientNote();
        patientNote.setPatId(21);
        patientNote.setId("1234567890");
        patientNote.setNote("Test Note");
        ModelAndView modelAndView = mediScreenMongoController.addPatient(patientNote);
        Assert.assertEquals("patHistory/add", modelAndView.getViewName());
    }

    @Test
    void validate() {
        PatientNote patientNote = new PatientNote();
        patientNote.setPatId(21);
        patientNote.setId("1234567890");
        patientNote.setNote("Test Note");
        ModelAndView modelAndView = mediScreenMongoController.validate(patientNote,bindingResult,model);
        Assert.assertEquals("redirect:/patHistory/list", modelAndView.getViewName());
    }

    @Test
    void showUpdateForm() {
        PatientNote patientNote = new PatientNote();
        patientNote.setPatId(21);
        patientNote.setId("1234567890");
        patientNote.setNote("Test Note");
        Mockito.when(patientNoteService.getPatientById("1234567890")).thenReturn(patientNote);
        ModelAndView modelAndView = mediScreenMongoController.showUpdateForm("1234567890",model);
        Assert.assertEquals("patHistory/update", modelAndView.getViewName());
    }

    @Test
    void updatePatientNote() {
        PatientNote patientNote = new PatientNote();
        patientNote.setPatId(21);
        patientNote.setId("1234567890");
        patientNote.setNote("Test Note");
        ModelAndView modelAndView = mediScreenMongoController.updatePatientNote("1234567890", patientNote, bindingResult, model);
        Assert.assertEquals("redirect:/patHistory/list", modelAndView.getViewName());
    }

    @Test
    void deletePatient() {
        PatientNote patientNote = new PatientNote();
        patientNote.setPatId(21);
        patientNote.setId("1234567890");
        patientNote.setNote("Test Note");
        Mockito.when(patientNoteService.getPatientById("1234567890")).thenReturn(patientNote);
        ModelAndView modelAndView = mediScreenMongoController.deletePatient("1234567890", model);
        Assert.assertEquals("redirect:/patHistory/list", modelAndView.getViewName());
    }

    @Test
    void getPatientNotes() {
        PatientNoteDto patientNoteDto1 = new PatientNoteDto();
        patientNoteDto1.setPatId(21);
        patientNoteDto1.setNote("Test Note1");
        PatientNoteDto patientNoteDto2 = new PatientNoteDto();
        patientNoteDto2.setPatId(21);
        patientNoteDto2.setNote("Test Note2");
        List<PatientNoteDto> patientNoteDtoList = new ArrayList<>();
        patientNoteDtoList.add(patientNoteDto1);
        patientNoteDtoList.add(patientNoteDto2);
        Mockito.when(patientNoteService.getAllByPatId(21)).thenReturn(patientNoteDtoList);
       Assert.assertEquals(patientNoteDto1.getPatId(),
               mediScreenMongoController.getPatientNotes(21).get(0).getPatId());
    }

}