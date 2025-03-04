package controller.Appointment;

import entity.Appointment;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.AppointmentService;

import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/deleteappointment")
public class DeleteAppointment extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        // Check if idParam is null or empty
        if (idParam == null || idParam.trim().isEmpty()) {
            resp.getWriter().print("<h3 style='color:red;'>Invalid request: Patient ID is required.</h3>");
            return;
        }
        int id = Integer.parseInt(idParam);
        Appointment app=new Appointment();
        AppointmentService appServ=new AppointmentService();
        try {
           int res= appServ.deleteAppointmentById(id);
           if(res!=0)
           {
               RequestDispatcher rqd= req.getRequestDispatcher("bookAppointment.jsp");
               rqd.forward(req,resp);
           }
           else {
               resp.getWriter().print("<h3 style='color:red;'>Appointment details not found.</h3>");
           }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
