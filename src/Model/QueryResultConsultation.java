package Model;

public class QueryResultConsultation {

    private String dateConsultation;

    private double priceConsultation;

    private String patientLastName;

    private String patientFirstName;

    private String expertLastName;

    private String expertSpecialisation;

    public QueryResultConsultation(String dateConsultation, double priceConsultation, String patientLastName, String patientFirstName, String expertLastName, String expertSpecialisation) {
        this.dateConsultation = dateConsultation;
        this.priceConsultation = priceConsultation;
        this.patientLastName = patientLastName;
        this.patientFirstName = patientFirstName;
        this.expertLastName = expertLastName;
        this.expertSpecialisation = expertSpecialisation;
    }

    public String getDateConsultation() {
        return dateConsultation;
    }

    public double getPriceConsultation() {
        return priceConsultation;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public String getExpertLastName() {
        return expertLastName;
    }

    public String getExpertSpecialisation() {
        return expertSpecialisation;
    }
}
