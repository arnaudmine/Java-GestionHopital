package Model;

public class QueryResultNurse {
    private String nurseLastName;
    private String nurseFirstName;
    private String service;
    private String supervisor;
    private String id;

    public QueryResultNurse(String nurseLastName, String nurseFirstName,String id, String service, String supervisor ){
        this.nurseLastName = nurseLastName;
        this.nurseFirstName = nurseFirstName;
        this.service = service;
        this.supervisor = supervisor;
        this.id = id;
    }
    public QueryResultNurse(String nurseLastName, String nurseFirstName, String service) {
        this(nurseLastName, nurseFirstName, service, null, null);
    }

    public String getNurseLastName() {
        return nurseLastName;
    }

    public String getNurseFirstName() {
        return nurseFirstName;
    }

    public String getService() {
        return service;
    }

    public String getId() {
        return id;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public String toString() {
        return "Infirmier : " +nurseLastName + " " + nurseFirstName +"(id:"+id+ ") HD : " +supervisor+" Service : " +service+"\n";
    }
}
