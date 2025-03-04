package controller.Appointment;

import entity.Appointment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.AppointmentService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/fetchappointment")
public class FetchAppointmentById extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.trim().isEmpty()) {
            resp.getWriter().println("<h3 style='color:red;'>Error: Invalid Appointment ID</h3>");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            AppointmentService appServ = new AppointmentService();
            Appointment appointment = appServ.fetchAppointmentById(id);

            resp.setContentType("text/html");

            if (appointment != null) {
                resp.getWriter().println("<div class='card' style='width: 18rem; border: 1px solid #ccc; padding: 15px; border-radius: 10px; box-shadow: 2px 2px 10px rgba(0,0,0,0.1);'>");
                resp.getWriter().println("<div class='card-body'>");
                resp.getWriter().println("<h2 class='card-title'>Appointment Details</h2> <img src='https://img.freepik.com/free-vector/doctor-examining-patient-clinic-illustrated_23-2148856559.jpg?t=st=1740663999~exp=1740667599~hmac=6d5bc693ab440dd9f9564a288eb509f2b80fe39e6f726585a0d70faa1d2824f7&w=1380' alt='Doctor Image' style='width:100%; border-radius:10px; margin-bottom:10px;'>");
                resp.getWriter().println("<p class='card-text'><strong>Appointment ID:</strong> " + appointment.getId() + "</p>");
                resp.getWriter().println("<p class='card-text'><strong>Doctor Name:</strong> " + appointment.getDname() + "</p>");
                resp.getWriter().println("<p class='card-text'><strong>Patient Name:</strong> " + appointment.getPname() + "</p>");
                resp.getWriter().println("<p class='card-text'><strong>Appointment Date:</strong> " + appointment.getAppointmentDate() + "</p>");
                resp.getWriter().println("</div>");
                resp.getWriter().println("</div>");
            } else {
                resp.getWriter().println("<h1>No Appointment Found with ID " + id + "</h1>");
            }

        } catch (NumberFormatException e) {
            resp.getWriter().println("<h3 style='color:red;'>Error: Invalid Appointment ID Format</h3>");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
