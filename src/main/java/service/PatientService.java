package service;

import entity.Doctor;
import entity.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientService {

    private final String url="jdbc:mysql://localhost:3306/jsp";
    private final String username="root";
    private final String password="system";
    Connection con;

    public int  save(Patient pt) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection(url,username,password);

        String query = "INSERT INTO patients(name, age, gender) VALUES(?, ?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, pt.getName());
        preparedStatement.setInt(2, pt.getAge());
        preparedStatement.setString(3, pt.getGender());
        return  preparedStatement.executeUpdate();
    }

    public List<Patient>  fetch() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection(url,username,password);
        List<Patient> pList=new ArrayList<>();
        String query="Select * from patients";
        PreparedStatement pts= con.prepareStatement(query);
        ResultSet rs= pts.executeQuery();
        while (rs.next())
        {
            int id=rs.getInt("id");
            String name=rs.getString("name");
            int age=rs.getInt("age");
            String gender=rs.getString("gender");
            pList.add(new Patient(id,name,age,gender));
        }
        return pList;
    }

    public int deleteStudentBy(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection(url,username,password);
        String query = "DELETE FROM patients WHERE id = (?)";

        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate();

    }


    public int updatePatient(Patient pt) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, username, password);

        String sql = "UPDATE patients SET name=?, age=?, gender=? WHERE id=?";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, pt.getName());
        pst.setInt(2, pt.getAge());
        pst.setString(3, pt.getGender());
        pst.setInt(4, pt.getId()); // Fix the ID parameter index

        return pst.executeUpdate();
    }

    public Patient fetchPatientById(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, username, password);

        String sql = "SELECT * FROM patients WHERE id=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, id);
        Patient pt=new Patient();


        ResultSet rs=pst.executeQuery();
        while (rs.next())
        {
            String name=rs.getString("name");
            int age=rs.getInt("age");
            String gender=rs.getString("gender");
            return new Patient(name,age,gender);
        }
        return pt;
    }


    public boolean getPatientById(int id) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, username, password);
        String query = "SELECT * FROM patients WHERE id = ?";

        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
