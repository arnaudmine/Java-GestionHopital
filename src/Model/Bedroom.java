package Model;

public class Bedroom {
    private int number;
    private int nbBeds;
    private String service;

    private String supervisor;

    public Bedroom(int number, int nbBeds, String service, String supervisor) {
        this.number = number;
        this.nbBeds = nbBeds;
        this.service = service;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNbBeds(int nbBeds) {
        this.nbBeds = nbBeds;
    }

    public void setService(String service) {
        this.service = service;
    }
}
