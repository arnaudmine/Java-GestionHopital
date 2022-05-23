package Model;

public class QueryResultBedroom {
    private Integer bedroomNumber;

    public QueryResultBedroom(Integer bedroomNumber) {
        this.bedroomNumber = bedroomNumber;
    }

    public Integer getBedroomNumber() {
        return this.bedroomNumber;
    }

    @Override
    public String toString() {
        return "bedroom numner " + bedroomNumber;
    }
}
