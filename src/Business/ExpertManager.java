package Business;

import Controller.DataAccess;
import DataAccess.DBAccess;

import java.util.ArrayList;

public class ExpertManager {
    private DataAccess dao;

    public ExpertManager() throws Exception {
        dao = new DBAccess();
    }

    public ArrayList<String> getAllExpert() throws Exception {
        return dao.getAllExpert();
    }
}
