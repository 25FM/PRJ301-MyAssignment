/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import controller.auth.BaseRoleController;
import dal.AttendanceDBContext;
import dal.StudentDBContext;
import dal.SubjectDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Attendance;
import model.Student;
import model.Subject;

/**
 *
 * @author MANH
 */
public class AttendanceReport extends BaseRoleController {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void processAuthPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void processAuthGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int stdid = Integer.parseInt(request.getParameter("stdid"));
        String sem = request.getParameter("semester");
        String grid = request.getParameter("grid");
        if (sem == null) {
            sem = "FALL";
        }
        StudentDBContext stdb = new StudentDBContext();
        Student s = stdb.get(stdid);

        SubjectDBContext sjdb = new SubjectDBContext();
        ArrayList<Subject> subs = sjdb.get(stdid, sem);
        int Grid = 0;
        if (grid == null) {
            Grid = subs.get(0).getGroups().get(0).getId();
        } else {
            Grid = Integer.parseInt(grid);
        }
        AttendanceDBContext atdb = new AttendanceDBContext();
        ArrayList<Attendance> atts = atdb.getByStudent(stdid, Grid);
        request.setAttribute("student", s);
        request.setAttribute("subs", subs);
        request.setAttribute("atts", atts);
        request.getRequestDispatcher("../view/student/attendancereport.jsp").forward(request, response);
    }

}
