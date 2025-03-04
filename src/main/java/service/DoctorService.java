package service;

import entity.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorService
{
    private final String url="jdbc:mysql://localhost:3306/jsp";
    private final String username="root";
    private final String password="system";
    Connection con;



    public int save(Doctor doc) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection(url,username,password);
        String query = "INSERT INTO doctors (name,specialization)VALUES (?,?);";
        PreparedStatement preparedStatement=con.prepareStatement(query);
        preparedStatement.setString(1,doc.getName());
        preparedStatement.setString(2,doc.getSpecialization());
        int affectedRows = preparedStatement.executeUpdate();

        return affectedRows;
    }

    public List<Doctor> fetch() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection(url,username,password);
        List<Doctor> dList=new ArrayList<>();
        String query="Select * from doctors";
        PreparedStatement pts= con.prepareStatement(query);
        ResultSet rs= pts.executeQuery();
        while (rs.next())
        {
            String name=rs.getString("name");
            String specialization=rs.getString("specialization");
            dList.add(new Doctor(name,specialization));
        }
        return dList;

    }


    public Doctor fetchDoctorById(int id) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection(url,username,password);
        //System.out.println("Fetching doctor with ID: " + id);
        String query = "SELECT * FROM doctors WHERE id = ?";

        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, id);
        Doctor doc = new Doctor();
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            //int idd = rs.getInt("id");  // Fetch ID from DB
            String name = rs.getString("name");
            String specialization = rs.getString("specialization");
            //return new Doctor(idd, name, specialization); // Pass ID
            return new Doctor(name, specialization); // Pass ID
        }


        return doc;
    }

    public boolean getDoctorById(int id) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, username, password);
        String query = "SELECT * FROM doctors WHERE id = ?";

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
