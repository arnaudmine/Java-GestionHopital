package Model;

public class UpdatePatientModel {

    private String id;
    private String address;
    private String nurse;
    private int bedroom;
    private boolean canLeave;
    private String email;
    private String insurance;


    public UpdatePatientModel(String id, String address, String nurse, int bedroom, boolean canLeave, String email, String insurance) {
        this.id = id;
        this.address = address;
        this.nurse = nurse;
        this.bedroom = bedroom;
        this.canLeave = canLeave;
        setEmail(email);
        setInsurance(insurance);
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getNurse() {
        return nurse;
    }

    public int getBedroom() {
        return bedroom;
    }

    public boolean getCanLeave() {
        return canLeave;
    }

    public String getEmail() {
        return email;
    }

    public String getInsurance() {
        return insurance;
    }

    public String toString(){
        return "modifier patient "+ id;
    }
}
