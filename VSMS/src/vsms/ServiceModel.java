/*
 * Rory Allen - s#12149026
 * Jorrel Arboleda - s#XXXXXXXX
 */
package vsms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ServiceModel {
    
    //connection constants
    private static final String DB_URL = "jdbc:mysql://localhost/vsms";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Acm3cats";
    
    //database connection
    private Connection connection = null;
    
    //stored sql queries
    private PreparedStatement   SQL_ALL_TESTS = null,
                                SQL_ALL_PATIENTS = null,
                                SQL_PATIENTS_BY_NAME = null,
                                SQL_PATIENTS_BY_PHONE = null,
                                SQL_INSERT_TEST = null,
                                SQL_INSERT_PATIENT = null,
                                SQL_UPDATE_TEST = null,
                                SQL_UPDATE_PATIENT = null,
                                SQL_TESTS_BY_PATIENTID = null;
    
    //constructor establishes connection to db and defines sql queries
    public ServiceModel (){
//        try {
//            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
//            
//            SQL_ALL_TESTS = connection.prepareStatement("SELECT * FROM VirusTest");
//            SQL_ALL_PATIENTS = connection.prepareStatement("SELECT * FROM Patient");
//            SQL_PATIENTS_BY_NAME = connection.prepareStatement("SELECT * FROM Patient WHERE FirstName LIKE ? OR LastName LIKE ?");
//            SQL_PATIENTS_BY_PHONE = connection.prepareStatement("SELECT * FROM Patient WHERE Phone = ?");
//            SQL_TESTS_BY_PATIENTID = connection.prepareStatement("SELECT * FROM VirusTest WHERE PatientID = ?");
//            SQL_INSERT_TEST = connection.prepareStatement("INSERT INTO VirusTest "
//                                                        + "(type, result, date, PatientID) "
//                                                        + "VALUES (?, ?, ?, ?)");
//            SQL_INSERT_PATIENT = connection.prepareStatement("INSERT INTO Patient "
//                                                        + "(FirstName, LastName, dob, phone, gender) "
//                                                        + "VALUES (?, ?, ?, ?, ?)");
//            SQL_UPDATE_TEST = connection.prepareStatement("UPDATE Test "
//                                                        + "SET type = ?, result = ?, date = ?, PatientID = ? "
//                                                        + "WHERE TestID = ?");
//            SQL_UPDATE_PATIENT = connection.prepareStatement("UPDATE Patient "
//                                                        + "SET FirstName = ?, LastName = ?, dob = ?, phone = ?, gender = ? "
//                                                        + "WHERE PatientID = ?");
//        } catch (SQLException e) {
//            System.out.println("VirusTestModel.init: cannot create connection to db - " + e.getLocalizedMessage());
//            close();
//        }
    }
    
    //returns all virus tests from db
//    public LinkedList<Service> getAllTests() {
//        LinkedList tests = new LinkedList();
//        try {
//            ResultSet rs = SQL_ALL_TESTS.executeQuery();
//            while (rs.next()){
//                Service v = new Service(
//                        rs.getInt("TestID"), 
//                        rs.getString("type"), 
//                        rs.getString("result"), 
//                        rs.getDate("date"), 
//                        rs.getInt("PatientID")
//                );
//                System.out.println("test: " + v.toString());
//                tests.add(v);
//            }
//        } catch (SQLException e) {
//            System.out.println("VirusTestModel.getAllTests: problem executing query - " + e.getLocalizedMessage());
//            close();
//        }
//        return tests;
//    }
//    
//    //returns all virus tests for a given patient 1d
//    public LinkedList<VirusTest> getTestsByPatientID(int id) {
//        LinkedList tests = new LinkedList();
//        try {
//            SQL_TESTS_BY_PATIENTID.setInt(1, id);
//            ResultSet rs = SQL_TESTS_BY_PATIENTID.executeQuery();
//            while (rs.next()){
//                VirusTest v = new VirusTest(
//                        rs.getInt("TestID"), 
//                        rs.getString("type"), 
//                        rs.getString("result"), 
//                        rs.getDate("date"), 
//                        rs.getInt("PatientID")
//                );
//                System.out.println("test: " + v.toString());
//                tests.add(v);
//            }
//        } catch (SQLException e) {
//            System.out.println("VirusTestModel.getAllTests: problem executing query - " + e.getLocalizedMessage());
//            close();
//        }
//        return tests;
//    }
//    
//    //returns all patients from db
//    public LinkedList<Patient> getAllPatients (){
//        LinkedList patients = new LinkedList();
//        try {
//            ResultSet rs = SQL_ALL_PATIENTS.executeQuery();
//            while (rs.next()){
//                Patient p = new Patient(
//                        rs.getInt("PatientID"), 
//                        rs.getString("FirstName"), 
//                        rs.getString("LastName"), 
//                        rs.getDate("dob"), 
//                        rs.getString("Phone"), 
//                        rs.getString("Gender")
//                );
//                p.addTests(getTestsByPatientID(p.getPatientID()));
//                System.out.println("patient: " + p.toString());
//                patients.add(p);
//            }
//        } catch (SQLException e) {
//            System.out.println("VirusTestModel.getPatientsByName: problem executing query - " + e.getLocalizedMessage());
//            close();
//        }
//        return patients;
//    }
//    
//    //returns patients for a given name **matches partial for both first and last names**
//    public LinkedList<Patient> getPatientsByName(String name) {
//        LinkedList patients = new LinkedList();
//        try {
//            name  = "%" + name + "%";
//            System.out.println(name);
//            SQL_PATIENTS_BY_NAME.setString(1, name);
//            SQL_PATIENTS_BY_NAME.setString(2, name);
//            System.out.println(SQL_PATIENTS_BY_NAME);
//            ResultSet rs = SQL_PATIENTS_BY_NAME.executeQuery();
//            while (rs.next()){
//                Patient p = new Patient(
//                        rs.getInt("PatientID"), 
//                        rs.getString("FirstName"), 
//                        rs.getString("LastName"), 
//                        rs.getDate("dob"), 
//                        rs.getString("Phone"), 
//                        rs.getString("Gender")
//                );
//                p.addTests(getTestsByPatientID(p.getPatientID()));
//                System.out.println("patient: " + p.toString());
//                patients.add(p);
//            }
//        } catch (SQLException e) {
//            System.out.println("VirusTestModel.getAllPatients: problem executing query - " + e.getLocalizedMessage());
//            close();
//        }
//        return patients;
//    }
//    
//    //returns patients for a given phone number
//    public LinkedList<Patient> getPatientsByPhone(String phone) {
//        LinkedList patients = new LinkedList();
//        try {
//            SQL_PATIENTS_BY_PHONE.setString(1, phone);
//            ResultSet rs = SQL_PATIENTS_BY_PHONE.executeQuery();
//            while (rs.next()){
//                Patient p = new Patient(
//                        rs.getInt("PatientID"), 
//                        rs.getString("FirstName"), 
//                        rs.getString("LastName"), 
//                        rs.getDate("dob"), 
//                        rs.getString("Phone"), 
//                        rs.getString("Gender")
//                );
//                p.addTests(getTestsByPatientID(p.getPatientID()));
//                System.out.println("patient: " + p.toString());
//                patients.add(p);
//            }
//        } catch (SQLException e) {
//            System.out.println("VirusTestModel.getPatientsByPhone: problem executing query - " + e.getLocalizedMessage());
//            close();
//        }
//        return patients;
//    }
//    
//    //adds a new virus test entry to the db
//    public void insertTest(VirusTest test) {
//        try {
//            SQL_INSERT_TEST.setString(1, test.getType());
//            SQL_INSERT_TEST.setString(2, test.getResult());
//            SQL_INSERT_TEST.setDate(3, test.getTestDate());
//            SQL_INSERT_TEST.setInt(4, test.getPatientID());
//            
//            SQL_INSERT_TEST.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("VirusTestModel.insertTest: problem executing query - " + e.getLocalizedMessage());
//            close();
//        }
//    }
//    
//    //adds a new patient entry to the db
//    public void insertPatient(Patient patient) {
//        try {
//            SQL_INSERT_PATIENT.setString(1, patient.getFirstName());
//            SQL_INSERT_PATIENT.setString(2, patient.getLastName());
//            SQL_INSERT_PATIENT.setDate(3, patient.getDob());
//            SQL_INSERT_PATIENT.setString(4, patient.getPhone());
//            SQL_INSERT_PATIENT.setString(5, patient.getGender());
//            
//            SQL_INSERT_PATIENT.executeUpdate();
//            System.out.println("patient added: " + patient.toString());
//        } catch (SQLException e) {
//            System.out.println("VirusTestModel.insertPatient: problem executing query - " + e.getLocalizedMessage());
//            close();
//        }
//    }
//    
//    //updates a virus test entry
//    public void updateTest(VirusTest test) {
//        try {
//            SQL_UPDATE_TEST.setString(1, test.getType());
//            SQL_UPDATE_TEST.setString(2, test.getResult());
//            SQL_UPDATE_TEST.setDate(3, test.getTestDate());
//            SQL_UPDATE_TEST.setInt(4, test.getPatientID());
//            SQL_UPDATE_TEST.setInt(5, test.getId());
//            
//            SQL_UPDATE_PATIENT.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("VirusTestModel.updatePatient: problem executing query - " + e.getLocalizedMessage());
//            close();
//        }
//    }
//    
//    //updates a patient entry
//    public void updatePatient(Patient patient) {
//        try {
//            SQL_UPDATE_PATIENT.setString(1, patient.getFirstName());
//            SQL_UPDATE_PATIENT.setString(2, patient.getLastName());
//            SQL_UPDATE_PATIENT.setDate(3, patient.getDob());
//            SQL_UPDATE_PATIENT.setString(4, patient.getPhone());
//            SQL_UPDATE_PATIENT.setString(5, patient.getGender());
//            SQL_UPDATE_PATIENT.setInt(6, patient.getPatientID());
//            
//            SQL_UPDATE_PATIENT.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("VirusTestModel.updatePatient: problem executing query - " + e.getLocalizedMessage());
//            close();
//        }
//    }
    
    //for closing connection
    public final void close(){
        try{
           connection.close();
        }
        catch (SQLException e){
            System.out.println("VirusTestModel.close: problem closing connection - " + e.getLocalizedMessage());
        }
    }
}
