/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import controller.auth.BaseRoleController;
import dal.SessionDBContext;
import dal.StudentDBContext;
import dal.TimeSlotDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import model.Lecturer;
import model.Session;
import model.Student;
import model.TimeSlot;
import model.Week;
import util.DateTimeHelper;

/**
 *
 * @author MANH
 */
public class TimetableStuController extends BaseRoleController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            int stdid = Integer.parseInt(request.getParameter("stdid"));
            String paraweek = request.getParameter("week");
            ArrayList<Week> weeks = DateTimeHelper.getAllWeek();

            TimeSlotDBContext tdb = new TimeSlotDBContext();
            ArrayList<TimeSlot> slots = tdb.list();

            StudentDBContext stdb = new StudentDBContext();
            Student student = stdb.get(stdid);
            int indexWeek;
            if (paraweek == null) {
                Date dnow = new Date();
                indexWeek = DateTimeHelper.getWeekOfYear(dnow);
            } else {
                indexWeek = Integer.parseInt(paraweek);
            }
            Week currentWeek = DateTimeHelper.getWeekTime(indexWeek);
            ArrayList<String> daysOfWeek = currentWeek.toStringValues();
            SessionDBContext ssdb = new SessionDBContext();
            ArrayList<Session> sessions = ssdb.getByStudent(stdid, new Date(currentWeek.getFrom().getTime()), new Date(currentWeek.getTo().getTime()));
            request.setAttribute("slots", slots);
            request.setAttribute("weeks", weeks);
            request.setAttribute("currentWeek", currentWeek);
            request.setAttribute("indexCurrentWeek", indexWeek);
            request.setAttribute("daysOfWeek", daysOfWeek);
            request.setAttribute("sessions", sessions);
            request.setAttribute("student", student);
            request.getRequestDispatcher("../view/student/timetable.jsp").forward(request, response);
    }



    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void processAuthPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void processAuthGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

}
