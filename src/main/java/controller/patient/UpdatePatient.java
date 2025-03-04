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

@WebServlet("/updatePatient")
public class UpdatePatient extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        String name=req.getParameter("name");
        int age= Integer.parseInt(req.getParameter("age"));
        String gender=req.getParameter("gender");

        PatientService pst=new PatientService();
        Patient pt=new Patient(id,name,age,gender);

        try {
            int res=  pst.updatePatient(pt);
            if(res!=0)
            {
                RequestDispatcher rqd=req.getRequestDispatcher("addPatient.html");
                rqd.forward(req,resp);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
