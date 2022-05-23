package Business;

import DataAccess.DBAccess;

public class DBAccesManager {

    private DBAccess dao;

    public DBAccesManager() throws Exception {
        this.dao = new DBAccess();
    }

    public void disconnect() throws Exception{
        dao.disconnect();
    }
}
