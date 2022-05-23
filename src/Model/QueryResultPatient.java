package Model;

public class QueryResultPatient {

    private Patient patient;

    private String nurseLastName;

    private String nurseFirstName;


    public QueryResultPatient(Patient patient, String nurseLastName, String nurseFirstName) {
        this.patient = patient;
        this.nurseLastName = nurseLastName;
        this.nurseFirstName = nurseFirstName;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getNurseLastName() {
        return nurseLastName;
    }

    public String getNurseFirstName() {
        return nurseFirstName;
    }

    @Override
    public String toString() {
        return "patient : " + patient.getLastName()+ " " +patient.getFirstName()+ "\ninfirmier : " + nurseLastName + " "+nurseFirstName +"--------------------------\n" ;
    }
}
