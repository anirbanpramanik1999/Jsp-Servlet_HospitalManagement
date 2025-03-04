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

@WebServlet("/bookappointment")
public class BookAppointment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/bookAppointment.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int dId = Integer.parseInt(req.getParameter("doctor_id"));
        int pId = Integer.parseInt(req.getParameter("patient_id"));
        String bookDate = req.getParameter("appointment_date");

        Appointment app = new Appointment(dId, pId, bookDate);
        AppointmentService appService = new AppointmentService();

        try {
            int res = appService.save(app);
            if (res != 0) {
                req.setAttribute("message", "Appointment booked successfully!");
                RequestDispatcher dispatcher = req.getRequestDispatcher("bookAppointment.jsp");
                dispatcher.forward(req, resp);
            }
            else {
                String errorMessage = "Error: Doctor or Patient may not exist!!<br>OR<br>Error: Doctor not available on this date!!";
                req.setAttribute("errorMessage", errorMessage);
                RequestDispatcher dispatcher = req.getRequestDispatcher("bookAppointment.jsp");
                dispatcher.forward(req, resp);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

