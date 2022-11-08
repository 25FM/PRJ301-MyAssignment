 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecturer;

import controller.auth.BaseRoleController;
import dal.AttendanceDBContext;
import dal.SessionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import model.Attendance;
import model.Session;
import model.Student;
import util.DateTimeHelper;

/**
 *
 * @author MANH
 */
public class TakeAttendance extends BaseRoleController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

   

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void processAuthPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Attendance> atts = new ArrayList<>();
        int lid = Integer.parseInt(request.getParameter("lid"));
        int week = Integer.parseInt(request.getParameter("week"));
        int sesid = Integer.parseInt(request.getParameter("sesid"));
        String[] attid = request.getParameterValues("attid");

        for (String id : attid) {
            Attendance att = new Attendance();
            Session session = new Session();
            session.setId(sesid);
            att.setSession(session);
            att.setId(Integer.parseInt(id));
            att.setPresent(request.getParameter("status" + id).equals("present"));
            att.setDescription(request.getParameter("comment" + id));
            atts.add(att);
        }
        AttendanceDBContext atdb = new AttendanceDBContext();
        atdb.update(atts);
        response.sendRedirect("timetable?lid=" + lid + "&week=" + week);
    }

    @Override
    protected void processAuthGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int grid = Integer.parseInt(request.getParameter("grid"));
        int index = Integer.parseInt(request.getParameter("index"));
        int lid = Integer.parseInt(request.getParameter("lid"));
        int week = Integer.parseInt(request.getParameter("week"));
        AttendanceDBContext atdb = new AttendanceDBContext();
        ArrayList<Attendance> atts = atdb.getByLecturer(grid, index);
        Date day = atts.get(0).getSession().getDate();
        int time = DateTimeHelper.compareToNowByDay(day);
        request.setAttribute("grid", grid);
        request.setAttribute("lid", lid);
        request.setAttribute("week", week);
        request.setAttribute("atts", atts);
        if (time == -1) {
            request.getRequestDispatcher("../view/lecturer/attendance/attended.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/lecturer/addAttendance").forward(request, response);

        }    }

}
