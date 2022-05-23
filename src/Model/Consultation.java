package Model;

import java.util.Date;
import java.util.GregorianCalendar;

public class Consultation {

    private String type;
    private GregorianCalendar date;
    private Double price;
    private String note;
    private String patient;
    private String expert;

    public Consultation(String type, GregorianCalendar date, Double price, String note, String patient, String expert){
        setType(type);
        setDate(date);
        setPrice(price);
        setNote(note);
        setPatient(patient);
        setExpert(expert);
    }

    public Consultation(String type, GregorianCalendar date, Double price, String patient, String expert) {
        this(type, date, price, null, patient, expert);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getPatient() {
        return patient;
    }

    public void setNote(String note) {
        if(note != null) {
            if (note.equals("")) {
                this.note = null;
            } else {
                this.note = note;
            }
        }else
            this.note = null;
    }

    public String getNote() {
        return note;
    }

    public void setExpert(String expert) {
        this.expert = expert;
    }

    public String getExpert() {
        return expert;
    }
}
