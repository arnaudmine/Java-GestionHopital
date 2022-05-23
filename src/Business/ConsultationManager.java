package Business;

import Controller.DataAccess;
import DataAccess.DBAccess;
import Model.Consultation;
import Model.QueryResultConsultation;

import java.util.ArrayList;

public class ConsultationManager {
    private DataAccess dao;

    public ConsultationManager() throws Exception {
        dao = new DBAccess();
    }

    public ArrayList<String> getAllConsultationType() throws Exception {
        return dao.getAllConsultationType();
    }

    public ArrayList<QueryResultConsultation> getConsultationByType(String typeConsultation) throws Exception {
        return dao.getConsultationByType(typeConsultation);
    }

    public void addConsultation(Consultation consultation) throws Exception {
        dao.addConsultation(consultation);
    }
}
