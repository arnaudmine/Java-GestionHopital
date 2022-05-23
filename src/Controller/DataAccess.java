package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface DataAccess {
    public ArrayList<String> getAllHeadsDepartmentName()throws Exception;
    public ArrayList<String> getAllConsultationType() throws Exception;
    public ArrayList<QueryResultConsultation> getConsultationByType(String type)throws Exception;
    public ArrayList<QueryResultPatient> getPatientByHeadDpt(String headDptID)throws Exception;
    public ArrayList<QueryResultBedroom> getBedroomByService(String type) throws Exception;
    public ArrayList<QueryResultNurse> getNurseByService(String type) throws Exception;
    public ArrayList<QueryResultNurse> getNurseByDate(GregorianCalendar calendar1, GregorianCalendar calendar2) throws Exception;
    public String getNurseById(String id) throws Exception;
    public ArrayList<String> getAllNurses() throws Exception;
    public ArrayList<Integer> getAllAvailableBedroom() throws Exception;
    public ArrayList<String> getAllServices() throws Exception;
    public String getServiceByNurse(String nurseId) throws Exception;
    public double getPatientBillByID(String id)throws Exception;
    public double getServiceDailyPriceByType(String type) throws Exception;
    public ArrayList<String> getAllExpert() throws Exception;
    public void addPatient(Patient patient) throws Exception;
    public void addConsultation(Consultation consultation) throws Exception;
    public void deletePatient(String id) throws Exception;
    public void deletePatientConsultation(String id) throws Exception;
    public void updatePatient(UpdatePatientModel infoPatient) throws Exception;
    public void disconnect() throws Exception;
}
