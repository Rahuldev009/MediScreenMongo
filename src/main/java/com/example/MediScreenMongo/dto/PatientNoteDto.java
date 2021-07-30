package com.example.MediScreenMongo.dto;

public class PatientNoteDto {

    int patId;
    String note;

    public int getPatId() {
        return patId;
    }

    public void setPatId(int patId) {
        this.patId = patId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "PatientNotedto{" +
                "patId=" + patId +
                ", note='" + note + '\'' +
                '}';
    }

}
