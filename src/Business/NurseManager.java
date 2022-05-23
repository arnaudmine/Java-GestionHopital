package Business;

import Controller.DataAccess;
import DataAccess.DBAccess;
import Model.QueryResultNurse;

import java.util.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class NurseManager {
    private DataAccess dao;

    public NurseManager() throws Exception {
        dao = new DBAccess();
    }

    public ArrayList<String> getAllNurses() throws Exception {
        return dao.getAllNurses();
    }

    public ArrayList<QueryResultNurse> getNurseByService(String serviceType) throws Exception {
        return dao.getNurseByService(serviceType);
    }

    public ArrayList<QueryResultNurse> getNurseByDate(GregorianCalendar calendar1, GregorianCalendar calendar2) throws Exception{
        return dao.getNurseByDate(calendar1,calendar2);
    }

    public String getNurseById(String id) throws Exception {
        return dao.getNurseById(id);
    }
}
