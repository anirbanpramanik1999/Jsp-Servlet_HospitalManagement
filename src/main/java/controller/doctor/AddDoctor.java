package controller.doctor;

import entity.Doctor;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.DoctorService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/adddoctor")
public class AddDoctor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("addDoctor.html");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String specialization = req.getParameter("specialization");

        Doctor doc = new Doctor();
        doc.setName(name);
        doc.setSpecialization(specialization);
        DoctorService docServ = new DoctorService();

        try {
            int result = docServ.save(doc);
            if (result != 0) {
                RequestDispatcher rqd = req.getRequestDispatcher("addDoctor.html");
                rqd.forward(req, resp);
            } else {
                RequestDispatcher rqd = req.getRequestDispatcher("test.html");
                rqd.include(req, resp);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
