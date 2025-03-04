package controller.patient;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PatientService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deletePatient")
public class DeletePatient extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        // Check if idParam is null or empty
        if (idParam == null || idParam.trim().isEmpty()) {
            resp.getWriter().print("<h3 style='color:red;'>Invalid request: Patient ID is required.</h3>");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            PatientService pst = new PatientService();
            int res = pst.deleteStudentBy(id);

            if (res != 0) {
                RequestDispatcher rqd = req.getRequestDispatcher("addPatient.html");
                rqd.forward(req, resp);
            } else {
                resp.getWriter().print("<h3 style='color:red;'>Patient ID not found.</h3>");
            }
        } catch (NumberFormatException e) {
            resp.getWriter().print("<h3 style='color:red;'>Invalid Patient ID format.</h3>");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
