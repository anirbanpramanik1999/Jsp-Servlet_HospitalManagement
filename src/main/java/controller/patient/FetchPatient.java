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

@WebServlet("/fetchpatient")
public class FetchPatient extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.trim().isEmpty()) {
            resp.getWriter().println("<h3 style='color:red;'>Error: Invalid Patient ID</h3>");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            PatientService pserv = new PatientService();
            Patient patient = pserv.fetchPatientById(id);

            if (patient != null) {
                resp.getWriter().println("<div class='card' style='width: 18rem; border: 1px solid #ccc; padding: 15px; border-radius: 10px; box-shadow: 2px 2px 10px rgba(0,0,0,0.1);'>");
                resp.getWriter().println("<div class='card-body'>");
                resp.getWriter().println("<h2 class='card-title'>Patient Details</h2> <img src='https://img.freepik.com/free-vector/flat-hand-drawn-patient-taking-medical-examination-illustration_23-2148859982.jpg?t=st=1740665169~exp=1740668769~hmac=492503b2abaf9967b7dd59aa3c0c128247970a24eabf2af46778cb180698abb5&w=900' alt='Patient Image' style='width:100%; border-radius:10px; margin-bottom:10px;'>");
                resp.getWriter().println("<p class='card-text'><strong>Name:</strong> " + patient.getName() + "</p>");
                resp.getWriter().println("<p class='card-text'><strong>Age:</strong> " + patient.getAge() + "</p>");
                resp.getWriter().println("<p class='card-text'><strong>Gender:</strong> " + patient.getGender() + "</p>");
                resp.getWriter().println("</div>");
                resp.getWriter().println("</div>");
            } else {
                resp.getWriter().println("<h1>No Patient Found with ID " + id + "</h1>");
            }
        } catch (NumberFormatException e) {
            resp.getWriter().println("<h3 style='color:red;'>Error: Invalid Patient ID Format</h3>");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
