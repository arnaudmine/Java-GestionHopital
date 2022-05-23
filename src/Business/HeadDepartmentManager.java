package Business;

import Controller.DataAccess;
import DataAccess.DBAccess;

import java.util.ArrayList;

public class HeadDepartmentManager {
    private DataAccess dao;

    public HeadDepartmentManager() throws Exception{
        dao = new DBAccess();
    }

    public ArrayList<String> getAllHeadsDepartmentName() throws Exception {
        return dao.getAllHeadsDepartmentName();
    }
}
