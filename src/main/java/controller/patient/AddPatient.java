package controller.patient;

import entity.Patient;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PatientService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addpatient")
public class AddPatient extends HttpServlet
{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("addPatient.html");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        int age= Integer.parseInt(req.getParameter("age"));
        String gender=req.getParameter("gender");

        Patient pt=new Patient(name,age,gender);
        PatientService pst=new PatientService();

        try {
            int res= pst.save(pt);
            if(res!=0)
            {
                RequestDispatcher rqd= req.getRequestDispatcher("addPatient.html");
                rqd.forward(req,resp);
            }
            else {
                RequestDispatcher rqd = req.getRequestDispatcher("test.html");
                rqd.include(req, resp);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
