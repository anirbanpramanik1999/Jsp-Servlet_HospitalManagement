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
import java.util.List;

@WebServlet("/viewdoctor")
public class ViewDoctor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DoctorService doctorService = new DoctorService();

        try {
            List<Doctor> doctorList = doctorService.fetch();

            resp.setContentType("text/html");
            resp.getWriter().print("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Doctor Details</title>\n" +
                    "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                    "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>\n" +
                    " <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css\">\n" +
                    "    <style>\n" +
                    "        body { font-family: Arial, sans-serif; background-color: #f8f9fa; margin: 0; padding: 20px; }\n" +
                    "        .container { max-width: 1200px; margin: 0 auto; }\n" +
                    "        .button-container { display: flex; justify-content: center; gap: 10px; margin-bottom: 20px; }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"container\">\n" +
                    "        <h1 class=\"text-center\">Doctor Details</h1>\n" +
                    "        <div class=\"button-container\">\n" +
                    "            <a href=\"index.jsp\" class=\"btn btn-warning\"><i class=\"fa-solid fa-house-chimney\"></i> Home</a>\n" +
                    "            <a href=\"/HospitalManagement/adddoctor\" class=\"btn btn-danger\">Add Doctor</a>\n" +
                    "            <button type=\"button\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#doctorModal\">Search Doctor</button>\n" +
                    "        </div>\n" +
                    "        <div class=\"row row-cols-1 row-cols-md-4 g-4\">\n");

            for (Doctor doctor : doctorList) {
                String name = doctor.getName();
                String specialization = doctor.getSpecialization();

                resp.getWriter().print(" <div class=\"col\">\n" +
                        "                <div class=\"card\" style=\"width: 100%;\">\n" +
                        "                    <img src=\"https://img.freepik.com/free-vector/doctors-concept-illustration_114360-1515.jpg?t=st=1740666918~exp=1740670518~hmac=79a632447e83aa0423b6d724f7976784f8f7cd869059c552571040f30c097074&w=1380\" class=\"card-img-top\" alt=\"Doctor Image\">\n" +
                        "                    <div class=\"card-body\">\n" +
                        "                        <h5 class=\"card-title\">" + name + "</h5>\n" +
                        "                        <p class=\"card-text\">Specialization: " + specialization + "</p>\n" +
                        "                    </div>\n" +
                        "                </div>\n" +
                        "            </div>\n");
            }

            resp.getWriter().print("        </div>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <!-- Search Doctor Modal -->\n" +
                    "    <div class=\"modal fade\" id=\"doctorModal\" tabindex=\"-1\" aria-labelledby=\"modalLabel\" aria-hidden=\"true\">\n" +
                    "        <div class=\"modal-dialog\">\n" +
                    "            <div class=\"modal-content\">\n" +
                    "                <div class=\"modal-header\">\n" +
                    "                    <h5 class=\"modal-title\" id=\"modalLabel\">Search Doctor</h5>\n" +
                    "                    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n" +
                    "                </div>\n" +
                    "                <div class=\"modal-body\">\n" +
                    "                    <form action=\"fetchdoctor\" method=\"post\">\n" +
                    "                        <div class=\"mb-3\">\n" +
                    "                            <label for=\"id\" class=\"form-label\">Enter Doctor ID:</label>\n" +
                    "                            <input type=\"text\" id=\"id\" name=\"id\" class=\"form-control\" required>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"modal-footer\">\n" +
                    "                            <button type=\"submit\" class=\"btn btn-primary\">Fetch Doctor</button>\n" +
                    "                            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Close</button>\n" +
                    "                        </div>\n" +
                    "                    </form>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>\n" +
                    "</body>\n" +
                    "</html>\n");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
