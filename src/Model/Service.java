package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Service {
    private String type;
    private double dailyPrice;
    private HeadDepartment supervisor;
    private ArrayList<Bedroom> bedrooms;
    private ArrayList<Nurse> nurses;

    public Service(String type, double dailyPrice, HeadDepartment supervisor) {
        this.type = type;
        this.dailyPrice = dailyPrice;
        this.supervisor = supervisor;
        this.bedrooms = new ArrayList<>();
        this.nurses = new ArrayList<>();
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public void setSupervisor(HeadDepartment supervisor) {
        this.supervisor = supervisor;
    }
}
