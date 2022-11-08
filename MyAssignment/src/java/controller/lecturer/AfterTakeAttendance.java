/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecturer;

import controller.auth.BaseRoleController;
import dal.AttendanceDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Attendance;

/**
 *
 * @author MANH
 */
public class AfterTakeAttendance extends BaseRoleController {

    
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

    }

    @Override
    protected void processAuthGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int grid = Integer.parseInt(request.getParameter("grid"));
            int index = Integer.parseInt(request.getParameter("index"));
            AttendanceDBContext atdb = new AttendanceDBContext();
            ArrayList<Attendance> atts = atdb.getByLecturer(grid, index);
            request.setAttribute("atts", atts);
            request.getRequestDispatcher("../../view/lecturer/attendance/afterattended.jsp").forward(request, response);
    }

}
