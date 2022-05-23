package Model;

import java.util.GregorianCalendar;

public class Nurse {
    private String id;
    private String lastName;
    private String firstName;
    private GregorianCalendar arrivalDate;
    private String service;
    private String supervisor;

    public Nurse(String id, String lastName, String firstName, GregorianCalendar arrivalDate, String service, String supervisor) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.arrivalDate = arrivalDate;
        this.service = service;
        this.supervisor = supervisor;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public GregorianCalendar getArrivalDate() {
        return arrivalDate;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
