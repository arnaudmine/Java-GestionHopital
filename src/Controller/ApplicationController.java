package Controller;


import Business.*;
import Model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ApplicationController {
    private HeadDepartmentManager headDepartmentManager;
    private ConsultationManager consultationManager;
    private NurseManager nurseManager;
    private BedroomManager bedroomManager;
    private ServiceManager serviceManager;
    private PatientManager patientManager;
    private ExpertManager expertManager;
    private DBAccesManager dbAccesManager;

    public ApplicationController() throws Exception{
        headDepartmentManager = new HeadDepartmentManager();
        consultationManager = new ConsultationManager();
        nurseManager = new NurseManager();
        bedroomManager = new BedroomManager();
        patientManager = new PatientManager();
        serviceManager = new ServiceManager();
        expertManager = new ExpertManager();
        dbAccesManager = new DBAccesManager();
    }

    public ArrayList<String> getAllHeadsDepartmentName() throws Exception {
        return headDepartmentManager.getAllHeadsDepartmentName();
    }

    public ArrayList<String> getAllConsultationType() throws Exception {
        return consultationManager.getAllConsultationType();
    }

    public ArrayList<String> getAllNurses() throws Exception {
        return nurseManager.getAllNurses();
    }

    public ArrayList<QueryResultNurse> getNurseByDate(GregorianCalendar calendar1, GregorianCalendar calendar2) throws Exception{
        return nurseManager.getNurseByDate(calendar1, calendar2);
    }

    public String getNurseById(String id) throws Exception {
        return nurseManager.getNurseById(id);
    }

    public ArrayList<Integer> getAllAvailableBedroom() throws Exception {
        return bedroomManager.getAllAvailableBedroom();
    }

    public ArrayList<QueryResultConsultation> getConsultationByType(String typeConsultation) throws Exception{
        return consultationManager.getConsultationByType(typeConsultation);
    }

    public ArrayList<QueryResultPatient> getPatientByHeadDpt(String headDptID)throws Exception{
        return patientManager.getPatientByHeadDpt(headDptID);
    }

    public ArrayList<QueryResultBedroom> getBedroomByService(String type) throws Exception {
        return bedroomManager.getBedroomByService(type);
    }

    public ArrayList<QueryResultNurse> getNurseByService(String serviceType) throws Exception {
        return nurseManager.getNurseByService(serviceType);
    }

    public ArrayList<String> getAllServices() throws Exception {
        return serviceManager.getAllServices();
    }

    public String getServiceByNurse(String nurseId) throws Exception {
        return serviceManager.getServiceByNurse(nurseId);
    }

    public void deletePatient(String id) throws Exception{
        patientManager.deletePatient(id);
    }
    public double getServiceDailyPriceByType(String type) throws Exception{
        return serviceManager.getServiceDailyPriceByType(type);
    }
    public ArrayList<String> getAllExperts() throws Exception {
        return expertManager.getAllExpert();
    }

    public void addPatient(Patient patient) throws Exception {
        patientManager.addPatient(patient);
    }

    public void addConsultation(Consultation consultation) throws Exception {
        consultationManager.addConsultation(consultation);
    }

    public void updatePatient(UpdatePatientModel infoPatient) throws Exception{
        patientManager.updatePatient(infoPatient);
    }

    public void disconnect() throws Exception{
        dbAccesManager.disconnect();
    }
}