package Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Patient {

    private String id;
    private String lastName;
    private String firstName;
    private GregorianCalendar arrivalDate;
    private String address;
    private String disease;
    private double bill;
    private String nurse;
    private int bedroom;
    private boolean canLeave;
    private String email;
    private String insuranceCompany;


    public Patient(String id, String lastName, String firstName, GregorianCalendar arrivalDate, String address, String disease, double bill, String nurse, int bedroom, boolean canLeave, String email, String insuranceCompany){

        setId(id);
        setLastName(lastName);
        setFirstName(firstName);
        setArrivalDate(arrivalDate);
        setAddress(address);
        setDisease(disease);
        setBill(bill);
        setNurse(nurse);
        setBedroom(bedroom);
        setCanLeave(canLeave);
        setEmail(email);
        setInsuranceCompany(insuranceCompany);
    }

    /*
    public Patient (String id, String lastName, String firstName, GregorianCalendar arrivalDate, String address, String disease, int bill, String nurse, int bedroom, boolean canLeave) {
        this(id, lastName, firstName, arrivalDate, address, disease, bill, nurse, bedroom, canLeave, null, null);
    }

    public Patient (String id, String lastName, String firstName, GregorianCalendar arrivalDate, String address, String disease, int bill, String nurse, int bedroom,  String email, boolean canLeave) {
        this(id, lastName, firstName, arrivalDate, address, disease, bill, nurse, bedroom, canLeave, email, null);
    }

    public Patient (String id, String lastName, String firstName, GregorianCalendar arrivalDate, String address, String disease, int bill, String nurse, int bedroom, boolean canLeave, String insuranceCompany) {
        this(id, lastName, firstName, arrivalDate, address, disease, bill, nurse, bedroom, canLeave, null, insuranceCompany);
    }
     */



    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public void setArrivalDate(GregorianCalendar arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public GregorianCalendar getArrivalDate() {
        return this.arrivalDate;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getAddress() {
        return address;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }


    public String getDisease() {
        return disease;
    }

    public void setBill(double bill) {
        this.bill = (double)Math.round(bill*100)/100;
    }

    public double getBill() {
        return bill;
    }


    public void setNurse(String nurse) {
        this.nurse = nurse;
    }


    public String getNurse() {
        return nurse;
    }

    public void setBedroom(int bedroom) {
        this.bedroom = bedroom;
    }

    public int getBedroom() {
        return bedroom;
    }

    public void setCanLeave(boolean canLeave) {
        this.canLeave = canLeave;
    }

    public boolean getCanLeave() {
        return canLeave;
    }

    public void setEmail(String email) {
        if(email != null) {
            if (email.equals("")) {
                this.email = null;
            } else {
                this.email = email;
            }
        }else
            this.email = null;
    }

    public String getEmail() {
        return email;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        if(insuranceCompany != null) {
            if (insuranceCompany.equals("")) {
                this.insuranceCompany = null;
            } else {
                this.insuranceCompany = insuranceCompany;
            }
        }else
            this.insuranceCompany = null;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }
}
