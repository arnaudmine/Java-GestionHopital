package Model;

public class Expert {

    private int id;
    private String specialisation;
    private String lastName;
    private String firstName;

    public Expert(int id, String specialisation, String lastName, String firstName){

        setId(id);
        setSpecialisation(specialisation);
        setLastName(lastName);
        setFirstName(firstName);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }
}
