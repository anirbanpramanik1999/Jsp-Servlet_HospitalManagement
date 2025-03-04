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
import java.util.List;

@WebServlet("/viewappointment")
public class ViewAppointment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppointmentService appointmentService = new AppointmentService();

        try {
            List<Appointment> appointmentList = appointmentService.fetch();

            resp.setContentType("text/html");
            resp.getWriter().print("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Appointment Details</title>\n" +
                    "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                    "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>\n" +
                    " <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css\">"+
                    "    <style>\n" +
                    "        body { font-family: Arial, sans-serif; background-color: #f8f9fa; margin: 0; padding: 20px; }\n" +
                    "        .container { max-width: 800px; margin: 0 auto; }\n" +
                    "        .table-container { position: relative; margin-bottom: 15px; } \n" +
                    "        .search-button {  top: 0; right: 0; margin-top: 10px; }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "         <div class=\"justify-content-center\">\n" +
                    "            <a href=\"index.jsp\">\n" +
                    "             <i class=\"fa-solid fa-house-chimney\" style=\"color: #FFD43B; font-size: 1.5rem\"></i> \n" +
                    "            </a>\n" +
                    "         </div>"+
                    "    <div class=\"container\">\n" +
                    "        <h1 class=\"text-center\">Appointment Details</h1>\n" +
                    "\n" +
                    "        <!-- Search Button -->\n" +
                    "        <div class=\"table-container d-flex justify-content-around\">\n" +
                    "            <button type=\"button\" class=\"btn btn-primary search-button\" data-bs-toggle=\"modal\" data-bs-target=\"#appointmentModal\">\n" +
                    "                Search Appointment\n" +
                    "            </button>\n" +
                    "        <a href=\"/HospitalManagement/bookappointment\" class=\"btn btn-warning w-30\">Book Appointment</a>"+
                    "        </div>\n" +
                    "\n" +
                    "        <!-- Appointment Table -->\n" +
                    "        <table class=\"table table-bordered table-striped mt-4\">\n" +
                    "            <thead class=\"table-primary\">\n" +
                    "                <tr>\n" +
                    "                    <th>Doctor Name</th>\n" +
                    "                    <th>Patient Name</th>\n" +
                    "                    <th>Appointment Date</th>\n" +
                    "                    <th>Actions</th>\n" +
                    "                </tr>\n" +
                    "            </thead>\n" +
                    "            <tbody>\n");

            for (Appointment appointment : appointmentList) {
                int id = appointment.getId(); // Ensure you have an 'id' field in Appointment entity
                String doctorName = appointment.getDname();
                String patientName = appointment.getPname();
                String appointmentDate = appointment.getAppointmentDate();

                resp.getWriter().print("  <tr>\n" +
                        "                    <td>" + doctorName + "</td>\n" +
                        "                    <td>" + patientName + "</td>\n" +
                        "                    <td>" + appointmentDate + "</td>\n" +
                        "                    <td>\n" +
                        "                        <button type=\"button\" class=\"btn btn-danger btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#deleteAppointmentModal\" onclick=\"setDeleteAppointmentId(" + id + ")\">\n" +
                        "                            Delete\n" +
                        "                        </button>\n" +
                        "                    </td>\n" +
                        "                </tr>\n");
            }

            resp.getWriter().print("            </tbody>\n" +
                    "        </table>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <!-- Search Appointment Modal -->\n" +
                    "    <div class=\"modal fade\" id=\"appointmentModal\" tabindex=\"-1\" aria-labelledby=\"modalLabel\" aria-hidden=\"true\">\n" +
                    "        <div class=\"modal-dialog\">\n" +
                    "            <div class=\"modal-content\">\n" +
                    "                <div class=\"modal-header\">\n" +
                    "                    <h5 class=\"modal-title\" id=\"modalLabel\">Search Appointment</h5>\n" +
                    "                    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n" +
                    "                </div>\n" +
                    "                <div class=\"modal-body\">\n" +
                    "                    <form action=\"fetchappointment\" method=\"post\">\n" +
                    "                        <div class=\"mb-3\">\n" +
                    "                            <label for=\"id\" class=\"form-label\">Enter Appointment ID:</label>\n" +
                    "                            <input type=\"text\" id=\"id\" name=\"id\" class=\"form-control\" required>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"modal-footer\">\n" +
                    "                            <button type=\"submit\" class=\"btn btn-primary\">Fetch Appointment</button>\n" +
                    "                            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Close</button>\n" +
                    "                        </div>\n" +
                    "                    </form>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <!-- Delete Appointment Modal -->\n" +
                    "    <div class=\"modal fade\" id=\"deleteAppointmentModal\" tabindex=\"-1\" aria-labelledby=\"deleteAppointmentModalLabel\" aria-hidden=\"true\">\n" +
                    "        <div class=\"modal-dialog\">\n" +
                    "            <div class=\"modal-content\">\n" +
                    "                <div class=\"modal-header\">\n" +
                    "                    <h5 class=\"modal-title\" id=\"deleteAppointmentModalLabel\">Delete Appointment</h5>\n" +
                    "                    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n" +
                    "                </div>\n" +
                    "                <div class=\"modal-body\">\n" +
                    "                    <form action=\"deleteappointment\" method=\"post\">\n" +
                    "                        <div class=\"mb-3\">\n" +
                    "                            <label for=\"deleteAppointmentId\" class=\"form-label\">Enter Appointment ID to Delete:</label>\n" +
                    "                            <input type=\"text\" id=\"deleteAppointmentId\" name=\"id\" class=\"form-control\" required>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"text-end\">\n" +
                    "                            <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Cancel</button>\n" +
                    "                            <button type=\"submit\" class=\"btn btn-danger\">Delete Appointment</button>\n" +
                    "                        </div>\n" +
                    "                    </form>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <script>\n" +
                    "        function setDeleteAppointmentId(id) {\n" +
                    "            document.getElementById('deleteAppointmentId').value = id;\n" +
                    "        }\n" +
                    "    </script>\n" +
                    "</body>\n" +
                    "</html>\n");

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
