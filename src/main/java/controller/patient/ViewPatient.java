package controller.patient;

import entity.Patient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PatientService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/viewpatient")
public class ViewPatient extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        PatientService patientService = new PatientService();

        try {
            resp.setContentType("text/html");
            resp.getWriter().print("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Patient Details</title>\n" +
                    "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                    "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>\n" +
                    " <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css\">"+
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"container mt-5\">\n" +
                    " <div class=\"justify-content-center\">\n" +
                            "      <a href=\"index.jsp\">\n" +
                            "        <i class=\"fa-solid fa-house-chimney\" style=\"color: #FFD43B; font-size: 1.5rem\"></i> \n" +
                            "      </a>\n" +
                            "    </div>"+
//                    "        <div class=\"d-flex justify-content-between align-items-center\">\n" +
//                    "            <h2 class=\"text-center\">Patient Details</h2>\n" +
//                    "            <button type=\"button\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#searchModal\">Search Patient</button>\n" +
//                    "        </div>\n" +
                    "        <h2 class=\"text-center\">Patient Details</h2>\n" +
                    "        <div class=\"table-container d-flex justify-content-around\">\n" +
                    "            <button type=\"button\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#searchModal\">Search Patient</button>\n"+
                    "             <a href=\"/HospitalManagement/addpatient\" class=\"btn btn-danger w-20 float-start\">Add Patient</a>"+
                    "        </div>\n"+
                    "        <table class=\"table table-bordered table-striped mt-4\">\n" +
                    "            <thead class=\"table-dark\">\n" +
                    "                <tr>\n" +
                    "                    <th>ID</th>\n" +
                    "                    <th>Name</th>\n" +
                    "                    <th>Age</th>\n" +
                    "                    <th>Gender</th>\n" +
                    "                    <th>Actions</th>\n" +
                    "                </tr>\n" +
                    "            </thead>\n" +
                    "            <tbody>\n");

            List<Patient> patientList = patientService.fetch();
            for (Patient patient : patientList) {
                resp.getWriter().print("  <tr>\n" +
                        "                    <td>" + patient.getId() + "</td>\n" +
                        "                    <td>" + patient.getName() + "</td>\n" +
                        "                    <td>" + patient.getAge() + "</td>\n" +
                        "                    <td>" + patient.getGender() + "</td>\n" +
                        "                    <td>\n" +
                        "                        <a href=\"updatePatient.html?id=" + patient.getId() + "\" class=\"btn btn-warning btn-sm\">Edit</a>\n" +
                        "                        <a   data-bs-toggle=\"modal\" data-bs-target=\"#deletePatientModal  href=\"deletePatient.html?id=" + patient.getId() + "\" class=\"btn btn-danger btn-sm\"   >Delete</a>\n" +
                        "                    </td>\n" +
                        "                </tr>\n");
            }

            resp.getWriter().print("            </tbody>\n" +
                    "        </table>\n" +

                    "        <!-- Search Modal -->\n" +
                    "        <div class=\"modal fade\" id=\"searchModal\" tabindex=\"-1\" aria-labelledby=\"modalLabel\" aria-hidden=\"true\">\n" +
                    "            <div class=\"modal-dialog\">\n" +
                    "                <div class=\"modal-content\">\n" +
                    "                    <div class=\"modal-header\">\n" +
                    "                        <h5 class=\"modal-title\" id=\"modalLabel\">Search Patient</h5>\n" +
                    "                        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n" +
                    "                    </div>\n" +
                    "                    <div class=\"modal-body\">\n" +
                    "                        <form action=\"fetchpatient\" method=\"POST\">\n" +
                    "                            <div class=\"mb-3\">\n" +
                    "                                <label for=\"patientId\" class=\"form-label\">Patient ID</label>\n" +
                    "                                <input type=\"text\" class=\"form-control\" id=\"patientId\" name=\"id\" required>\n" +
                    "                            </div>\n" +
                    "                            <button type=\"submit\" class=\"btn btn-primary\">Search</button>\n" +
                    "                        </form>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +

                    "    </div>\n" +
                    "</body>\n" +
                    "</html>\n");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
