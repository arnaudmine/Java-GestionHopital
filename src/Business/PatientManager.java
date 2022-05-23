package Business;

import Controller.DataAccess;
import DataAccess.DBAccess;
import Model.Patient;
import Model.QueryResultPatient;
import Model.UpdatePatientModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;


public class PatientManager {
    private DataAccess dao;

    public PatientManager() throws Exception {
        dao = new DBAccess();
    }
    public ArrayList<QueryResultPatient> getPatientByHeadDpt (String headDptID) throws Exception{
        return dao.getPatientByHeadDpt(headDptID);
    }
    public void addPatient(Patient patient) throws Exception {
        dao.addPatient(patient);
    }
    public void deletePatient(String id) throws Exception{
        dao.deletePatient(id);
    }
    public void updatePatient(UpdatePatientModel infoPatient) throws Exception{
        dao.updatePatient(infoPatient);
    }

    //tâche métier
    public double getFinalBill(Patient patient) throws Exception {
        ServiceManager serviceManager = new ServiceManager();
        String serviceType = serviceManager.getServiceByNurse(patient.getNurse());
        double serviceDailyPrice = serviceManager.getServiceDailyPriceByType(serviceType);
        GregorianCalendar todayCalendar = new GregorianCalendar();
        long diff = todayCalendar.getTimeInMillis()-patient.getArrivalDate().getTimeInMillis();
        int nbDays = (int)TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
        System.out.println(nbDays + " P/J : " + serviceDailyPrice);
        return patient.getBill() + (serviceDailyPrice*nbDays);
    }
}
