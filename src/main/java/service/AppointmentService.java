package service;

import entity.Appointment;
import entity.Doctor;
import entity.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService
{
    private final String url="jdbc:mysql://localhost:3306/jsp";
    private final String username="root";
    private final String password="system";
    Connection con;

    public int save(Appointment apt) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection(url,username,password);
        Doctor doc=new Doctor();
        DoctorService ds=new DoctorService();
        int docId= apt.getDcoId();
        Patient pt=new Patient();
        PatientService ps=new PatientService();
        int pId=apt.getPtId();
        AppointmentService apServ=new AppointmentService();

        if(ds.getDoctorById(docId) && ps.getPatientById(pId) ) {
            if(apServ.checkDoctorAvailability(apt.getDcoId(),apt.getAppointmentDate())) {
                String query = "INSERT INTO appointments (patient_id,doctor_id,appointment_date)VALUES (?,?,?);";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, apt.getPtId());
                pst.setInt(2, apt.getDcoId());
                pst.setString(3, apt.getAppointmentDate());
                return pst.executeUpdate();
            }
        }
        return 0;
    }


    public List<Appointment> fetch() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection(url,username,password);
        List<Appointment> aList=new ArrayList<>();
        String query=" SELECT a.id as id, d.name AS doctor_name, p.name AS patient_name, a.appointment_date FROM Appointments a LEFT JOIN Patients p ON a.patient_id = p.id  JOIN Doctors d ON a.doctor_id = d.id;";
        PreparedStatement pts= con.prepareStatement(query);
        ResultSet rs= pts.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");  // ✅ Check column exists
            String doctorName = rs.getString("doctor_name");
            String patientName = rs.getString("patient_name");
            String appointmentDate = rs.getString("appointment_date");

            Appointment appointment = new Appointment(id, doctorName, patientName, appointmentDate);
            aList.add(appointment);
        }

        return aList;
    }


    public Appointment fetchAppointmentById(int id) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, username, password);

        String query = "SELECT a.id as id, d.name AS doctor_name, p.name AS patient_name, a.appointment_date FROM Appointments a JOIN Doctors d ON a.doctor_id = d.id JOIN Patients p ON a.patient_id = p.id  WHERE a.id = ?";

        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            int aid = rs.getInt("id");
            String dname = rs.getString("doctor_name");
            String pname = rs.getString("patient_name");
            String appDate = rs.getString("appointment_date");
            return new Appointment(aid, dname, pname, appDate);
        }

        return null; // Return null if no record is found
    }

    public int deleteAppointmentById(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection(url,username,password);
        String query = "DELETE FROM Appointments where id=?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1,id);
        return pst.executeUpdate();
    }

    public  boolean checkDoctorAvailability(int doctorId, String appointmentDate) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection(url,username,password);
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                int count = resultSet.getInt(1);
                if(count==0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
