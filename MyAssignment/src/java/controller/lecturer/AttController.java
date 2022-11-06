/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecturer;

import dal.SessionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Attendance;
import model.Session;
import model.Student;

/**
 *
 * @author MANH
 */
public class AttController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int sesid = Integer.parseInt(request.getParameter("id"));
        SessionDBContext sesDB = new SessionDBContext();
        Session ses = sesDB.get(sesid);
        request.setAttribute("ses", ses);
        request.getRequestDispatcher("../view/lecturer/att.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Attendance> atts = new ArrayList<>();
        int lid = Integer.parseInt(request.getParameter("lid"));
        int week = Integer.parseInt(request.getParameter("week"));
        int sesid = Integer.parseInt(request.getParameter("sesid"));
        String[] attid = request.getParameterValues("attid");
        
        for (String id : attid) {
            Attendance att =new Attendance();
            Session session = new Session();
            session.setId(sesid);
            att.setSession(session);
            att.setId(Integer.parseInt(id));
            att.setPresent(request.getParameter("status" + id).equals("present"));
            att.setDescription(request.getParameter("comment" + id));
            atts.add(att);
        }
        SessionDBContext db = new SessionDBContext();
        db.update(atts);
        response.sendRedirect("takeatt?id="+ses.getId());
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
