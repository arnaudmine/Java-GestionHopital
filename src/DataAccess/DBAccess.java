package DataAccess;

import Controller.DataAccess;
import Model.*;
import Utils.DateFormator;
import View.SearchConsultationPanel;
import jdk.swing.interop.SwingInterOpUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DBAccess implements DataAccess {

    public DBAccess() throws Exception {}

    public ArrayList<String> getAllHeadsDepartmentName()throws Exception {
        ArrayList<String> getAllHeadsDepartmentName = new ArrayList<>();
        try {
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction = "SELECT last_name, first_name FROM head_department";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery(sqlInstruction);

            String lastName;
            String firstName;

            while(data.next()) {
                lastName = data.getString("last_name");
                firstName = data.getString("first_name");

                getAllHeadsDepartmentName.add(lastName + " " + firstName);
            }
        }
        catch(SQLException exception) {
            throw new Exception();
        }

        return getAllHeadsDepartmentName;
    }

    public ArrayList<String> getAllConsultationType() throws Exception {
        ArrayList<String> allConsultationsType = new ArrayList<>();
        try {
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction = "SELECT DISTINCT * FROM consultation";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery(sqlInstruction);


            String consultationType;

            while(data.next()) {
                consultationType = data.getString("type");

                allConsultationsType.add(consultationType);
            }
        }
        catch(SQLException exception) {
            throw new Exception(exception);
        }

        return allConsultationsType;
    }

    public ArrayList<QueryResultConsultation> getConsultationByType(String type)throws Exception{
        ArrayList<QueryResultConsultation> queryResultList = new ArrayList<>();

        try {
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction =
                    "Select c.date, c.price, p.last_name as 'patient last name', p.first_name as 'patient first name', e.last_name as 'expert last name', e.specialisation " +
                            "from consultation c " +
                            "join patient p " +
                            "on c.patient = p.id " +
                            "join expert e " +
                            "on c.expert = e.id " +
                            "where c.type = '"+ type +"';";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery(sqlInstruction);

            QueryResultConsultation queryResultConsultation;

            while(data.next()) {
                java.sql.Date sqlDate = data.getDate("date");
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(sqlDate);

                queryResultConsultation = new QueryResultConsultation(
                        DateFormator.fromCalendarToString(calendar),
                        data.getDouble("price"),
                        data.getString("patient last name"),
                        data.getString("patient first name"),
                        data.getString("expert last name"),
                        data.getString("specialisation")
                );

                queryResultList.add(queryResultConsultation);
            }
            return queryResultList;
        }
        catch(SQLException exception) {
            throw new Exception(exception);
        }

    }

    public ArrayList<QueryResultBedroom> getBedroomByService(String type) throws Exception {
        ArrayList<QueryResultBedroom> queryResultList = new ArrayList<>();

        try {
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction =
                    "SELECT number " +
                            "FROM bedroom b " +
                            "JOIN service s " +
                            "on b.service = s.type " +
                            "WHERE b.service = '"+ type +"';";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery(sqlInstruction);

            QueryResultBedroom queryResultBedroom;
            while(data.next()) {
                queryResultBedroom = new QueryResultBedroom(
                        data.getInt("number")
                );
                queryResultList.add(queryResultBedroom);
            }
            return queryResultList;
        }
        catch(SQLException e){
            throw new Exception(e);
        }
    }

    public ArrayList<QueryResultNurse> getNurseByService(String type) throws Exception {
        ArrayList<QueryResultNurse> queryResultList = new ArrayList<>();

        try {
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction =
                    "SELECT n.last_name, n.first_name, s.type, n.service " +
                            "FROM nurse n " +
                            "JOIN service s " +
                            "ON s.type = n.service " +
                            "WHERE s.type = '" + type + "';";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery(sqlInstruction);

            QueryResultNurse queryResultNurse;
            while (data.next()) {
                queryResultNurse = new QueryResultNurse (
                        data.getString("last_name"),
                        data.getString("first_Name"),
                        data.getString("service")
                );
                queryResultList.add(queryResultNurse);
            }

            return queryResultList;
        }
        catch(SQLException e){
            throw new Exception(e);
        }
    }

    public ArrayList<QueryResultNurse> getNurseByDate(GregorianCalendar calendar1, GregorianCalendar calendar2) throws Exception {
        ArrayList<QueryResultNurse> queryResultList = new ArrayList<>();

        try {
            Connection connection = SingletonConnexion.getInstance();

            java.util.Date date1 = calendar1.getTime();
            java.util.Date date2 = calendar2.getTime();

            java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
            java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());

            String sqlInstruction =
                    "SELECT n.last_name, n.first_name,n.id, n.service, h.id as'hd_id' " +
                            "from nurse n " +
                            "join service s " +
                            "on n.service = s.type " +
                            "join head_department h " +
                            "on s.supervisor = h.id " +
                            "where DATE(n.arrivalDate) " +
                            "between '"+sqlDate1+"' and '"+sqlDate2+"';";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery(sqlInstruction);
            QueryResultNurse queryResultNurse;
            while (data.next()) {
                queryResultNurse = new QueryResultNurse (
                        data.getString("last_name"),
                        data.getString("first_Name"),
                        data.getString("id"),
                        data.getString("service"),
                        data.getString("hd_id")
                );
                queryResultList.add(queryResultNurse);
            }
            return queryResultList;
        }
        catch(SQLException e){
            throw new Exception(e);
        }
    }

    public String getNurseById(String id) throws Exception {

        try {
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction = "SELECT n.id, n.last_name, n.first_name, n.service " +
                    "FROM nurse n " +
                    "WHERE n.id = '" + id + "';";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery(sqlInstruction);

            String nurseLastName = "";
            String nurseFirstName = "";
            String service = "";
            while (data.next()) {
                nurseLastName = data.getString("last_name");
                nurseFirstName = data.getString("first_name");
                service = data.getString("service");
            }

            return nurseFirstName + " " + nurseLastName + " (" + service + ")";
        }
        catch(SQLException e){
            throw new SQLException(e);
        }
    }

    public ArrayList<QueryResultPatient> getPatientByHeadDpt(String headDptID)throws Exception {

        ArrayList<QueryResultPatient> queryResultList = new ArrayList<>();

        try{
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction =
                    "Select p.*, n.last_name as'nom de infirmier', n.first_name as 'prénom de infirmier' \n" +
                            "from head_department hd " +
                            "join service s " +
                            "on hd.id = s.supervisor " +
                            "join nurse n " +
                            "on n.supervisor = s.supervisor " +
                            "join patient p " +
                            "on p.nurse = n.id " +
                            "join bedroom b " +
                            "on b.number = p.bedroom " +
                            "where hd.id = '"+headDptID+"';";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery(sqlInstruction);

            QueryResultPatient queryResultPatient;

            while (data.next()){
                java.sql.Date sqlDate = data.getDate("arrival_date");
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(sqlDate);

                Patient patient = new Patient(
                        data.getString("id"),
                        data.getString("last_name"),
                        data.getString("first_name"),
                        calendar,data.getString("address"),
                        data.getString("disease"),
                        data.getDouble("bill"),
                        data.getString("nurse"),
                        data.getInt("bedroom"),
                        data.getBoolean("can_leave"),
                        data.getString("email"),
                        data.getString("insurance_compagny")
                );

                queryResultPatient = new QueryResultPatient(
                        patient,
                        data.getString("nom de infirmier"),
                        data.getString("prénom de infirmier")
                );
                queryResultList.add(queryResultPatient);
            }
        }
        catch(SQLException e){
            throw new Exception(e);
        }
        return queryResultList;
    }
    public ArrayList<String> getAllNurses() throws Exception {
        ArrayList<String> nursesList = new ArrayList<>();
        try {
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction = "SELECT * FROM nurse";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery(sqlInstruction);

            String lastName;
            String firstName;
            String service;

            while(data.next()) {
                lastName = data.getString("first_name");
                firstName = data.getString("last_name");
                service = data.getString("service");

                nursesList.add(lastName + " " + firstName + " (" + service + ")");
            }
        }
        catch(Exception exception) {
            throw new Exception();
        }

        return nursesList;
    }

    public ArrayList<Integer> getAllAvailableBedroom() throws Exception {
        ArrayList<Integer> bedroomsList = new ArrayList<>();

        try {
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction = "SELECT number FROM bedroom WHERE nb_beds > 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery(sqlInstruction);

            Integer bedroomNumber;

            while(data.next()) {
                bedroomNumber = data.getInt("number");
                bedroomsList.add(bedroomNumber);
            }
        }
        catch(Exception exception) {
            throw new Exception();
        }

        return bedroomsList;
    }

    public ArrayList<String> getAllServices() throws Exception {
        ArrayList<String> servicesList = new ArrayList<>();

        try {
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction = "SELECT type FROM service";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery(sqlInstruction);

            String service;
            while(data.next()) {
                service = data.getString("type");
                servicesList.add(service);
            }
        }
        catch(SQLException exception) {
            throw new Exception(exception);
        }

        return servicesList;
    }

    public String getServiceByNurse(String nurseId) throws Exception {

        try {
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction = "SELECT p.nurse, n.service " +
                    "FROM patient p " +
                    "JOIN nurse n " +
                    "ON p.nurse = n.id " +
                    "WHERE p.nurse = '" + nurseId + "';";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery(sqlInstruction);

            String service = "";
            while (data.next()) {
                service = data.getString("service");
            }

            return service;
        }
        catch(SQLException e){
            throw new Exception(e);
        }
    }

    public ArrayList<String> getAllExpert() throws Exception {
        ArrayList<String> expertsList = new ArrayList<>();

        try {
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction = "SELECT last_name, first_name, specialisation FROM expert";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery(sqlInstruction);

            String lastName;
            String firstName;
            String specialisation;
            while (data.next()) {
                lastName = data.getString("last_name");
                firstName = data.getString("first_name");
                specialisation = data.getString("specialisation");
                expertsList.add(firstName + " " + lastName + " (" + specialisation + ")");
            }
        }
        catch(SQLException exception) {
            throw new Exception(exception);
        }

        return expertsList;
    }


    public void addPatient(Patient patient) throws Exception {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction = "insert into patient (id, last_name, first_name, arrival_date, address, disease, bill, nurse, bedroom, can_leave) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, patient.getId());
            preparedStatement.setString(2, patient.getLastName());
            preparedStatement.setString(3, patient.getFirstName());
            GregorianCalendar calendar = patient.getArrivalDate();
            java.sql.Date sqlDate = new java.sql.Date(calendar.getTimeInMillis());
            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setString(5, patient.getAddress());
            preparedStatement.setString(6, patient.getDisease());
            preparedStatement.setDouble(7, patient.getBill());
            preparedStatement.setString(8, patient.getNurse());
            preparedStatement.setInt(9, patient.getBedroom());
            preparedStatement.setBoolean(10, patient.getCanLeave());
            preparedStatement.executeUpdate();
            if (patient.getEmail() != null) {
                sqlInstruction = "update patient set email = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setString(1, patient.getEmail());
                preparedStatement.setString(2, patient.getId());
                preparedStatement.executeUpdate();
            } else {
                sqlInstruction = "update patient set email = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setNull(1, Types.VARCHAR);
            }

            if (patient.getInsuranceCompany() != null) {
                sqlInstruction = "update patient set insurance_compagny = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setString(1, patient.getInsuranceCompany());
                preparedStatement.setString(2, patient.getId());
                preparedStatement.executeUpdate();
            }
        }
        catch(SQLException e){
            throw new Exception(e);
        }

    }

    public void addConsultation(Consultation consultation) throws Exception {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction = "insert into consultation (type, date, price, patient, expert) values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, consultation.getType());
            GregorianCalendar calendar = consultation.getDate();
            java.sql.Date sqlDate = new java.sql.Date(calendar.getTimeInMillis());
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setDouble(3, consultation.getPrice());
            preparedStatement.setString(4, consultation.getPatient());
            preparedStatement.setString(5, consultation.getExpert());
            preparedStatement.executeUpdate();

            if(consultation.getNote() != null) {
                sqlInstruction = "update consultation set note = ? WHERE type = ? AND date = ? AND patient = ?";
                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setString(1, consultation.getNote());
                preparedStatement.setString(2, consultation.getType());
                preparedStatement.setDate(3, sqlDate);
                preparedStatement.setString(4, consultation.getPatient());
                preparedStatement.executeUpdate();
            } else {
                sqlInstruction = "update consultation set note = ? WHERE type = ? AND date = ? AND patient = ?";
                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setNull(1, Types.VARCHAR);
            }

            updatePatientBill(consultation.getPatient(), consultation.getPrice());
        }
        catch(SQLException e){
            throw new Exception(e);
        }
    }
    public void updatePatientBill(String id, Double price) throws Exception {
        Double newBill = price + getPatientBillByID(id);
        try{
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction = "UPDATE patient SET bill = ? WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setDouble(1, newBill);
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();
        }
        catch(SQLException exception) {
            throw new Exception(exception);
        }

    }
    public double getServiceDailyPriceByType(String type) throws Exception {
        try{
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction = "SELECT daily_price FROM service WHERE type = '"+type+"';";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery(sqlInstruction);

            double dailyPrice = .0;
            while(data.next()){
                dailyPrice = data.getDouble("daily_price");
            }
            return dailyPrice;
        }
        catch(SQLException exception){
            throw new Exception(exception);
        }
    }
    public double getPatientBillByID(String id)throws Exception {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction = "SELECT bill FROM patient WHERE id = '"+id+"';";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery(sqlInstruction);

            Double bill = .0;
            while(data.next()){
                bill = data.getDouble("bill");
            }
            return bill;
        }
        catch(SQLException exception) {
            throw new Exception(exception);
        }
    }
    public void deletePatient(String id) throws Exception {
        try{
            Connection connection = SingletonConnexion.getInstance();
            deletePatientConsultation(id);
            String sqlInstruction = "delete from patient where id = '"+id+"'";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.executeUpdate(sqlInstruction);
        }
        catch(SQLException exception){
            throw new Exception(exception);
        }
    }
    public void deletePatientConsultation(String id) throws Exception{
        try{
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction = "delete from consultation where patient = '"+id+"'";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.executeUpdate(sqlInstruction);
        }
        catch(SQLException exception){
            throw new Exception(exception);
        }
    }
    public void disconnect() throws Exception {
        SingletonConnexion.disconnect();
    }

    public void updatePatient(UpdatePatientModel infoPatient) throws Exception {
        try {
            System.out.println(infoPatient.getId());
            Connection connection = SingletonConnexion.getInstance();
            String sqlInstruction = "update patient " +
                    "set address = ?, " +
                    "nurse = ?, " +
                    "bedroom = ?, " +
                    "can_leave = ?, " +
                    "email = ?, " +
                    "insurance_compagny = ? " +
                    "WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, infoPatient.getAddress());
            preparedStatement.setString(2, infoPatient.getNurse());
            preparedStatement.setInt(3, infoPatient.getBedroom());
            preparedStatement.setBoolean(4, infoPatient.getCanLeave());
            if (infoPatient.getEmail() != null) {
                preparedStatement.setString(5, infoPatient.getEmail());
            } else {
                preparedStatement.setNull(5, Types.VARCHAR);
            }

            if (infoPatient.getInsurance() != null) {
                preparedStatement.setString(6, infoPatient.getInsurance());
            } else {
                preparedStatement.setNull(6, Types.VARCHAR);
            }

            preparedStatement.setString(7, infoPatient.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
}