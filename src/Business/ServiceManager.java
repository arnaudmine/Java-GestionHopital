package Business;

import Controller.DataAccess;
import DataAccess.DBAccess;

import java.util.ArrayList;

public class ServiceManager {
    private DataAccess dao;

    public ServiceManager() throws Exception {
        dao = new DBAccess();
    }

    public ArrayList<String> getAllServices() throws Exception {
        return dao.getAllServices();
    }

    public String getServiceByNurse(String nurseId) throws Exception {
        return dao.getServiceByNurse(nurseId);
    }

    public double getServiceDailyPriceByType(String type) throws Exception{
        return dao.getServiceDailyPriceByType(type);
    }
}
