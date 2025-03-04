package controller.doctor;

import entity.Doctor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.DoctorService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/fetchdoctor")
public class FetchDoctor extends HttpServlet {

    // Explicit No-Argument Constructor (Fixes NoSuchMethodException)
    public FetchDoctor() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.trim().isEmpty()) {
            resp.getWriter().println("<h3 style='color:red;'>Error: Invalid Doctor ID</h3>");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            DoctorService doctorService = new DoctorService();
            Doctor doctor = doctorService.fetchDoctorById(id);

            resp.setContentType("text/html");
            if (doctor != null) {
                resp.getWriter().println("<div class='card' style='width: 18rem; border: 1px solid #ccc; padding: 15px; border-radius: 10px; box-shadow: 2px 2px 10px rgba(0,0,0,0.1);'>");
                resp.getWriter().println("<div class='card-body'>");
                resp.getWriter().println("<h2 class='card-title'>Doctor Details</h2> <img src='https://img.freepik.com/free-vector/doctors-concept-illustration_114360-1515.jpg?t=st=1740666918~exp=1740670518~hmac=79a632447e83aa0423b6d724f7976784f8f7cd869059c552571040f30c097074&w=1380' alt='Doctor Image' style='width:100%; border-radius:10px; margin-bottom:10px;'>");
                resp.getWriter().println("<p class='card-text'><strong>Name:</strong> " + doctor.getName() + "</p>");
                resp.getWriter().println("<p class='card-text'><strong>Specialization:</strong> " + doctor.getSpecialization() + "</p>");
                resp.getWriter().println("</div>");
                resp.getWriter().println("</div>");
            } else {
                resp.getWriter().println("<h1>No Doctor Found with ID " + id + "</h1>");
            }
        } catch (NumberFormatException e) {
            resp.getWriter().println("<h3 style='color:red;'>Error: Invalid Doctor ID Format</h3>");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
