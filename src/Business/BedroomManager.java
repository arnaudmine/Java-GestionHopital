package Business;

import Controller.DataAccess;
import Model.Bedroom;
import DataAccess.DBAccess;
import Model.QueryResultBedroom;

import java.util.ArrayList;

public class BedroomManager {
    private DataAccess dao;

    public BedroomManager() throws Exception {
        dao = new DBAccess();
    }

    public ArrayList<Integer> getAllAvailableBedroom() throws Exception {
        return dao.getAllAvailableBedroom();
    }

    public ArrayList<QueryResultBedroom> getBedroomByService(String serviceType) throws Exception {
        return dao.getBedroomByService(serviceType);
    }
}
