/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecturer;

import controller.auth.BaseRoleController;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Student;

/**
 *
 * @author MANH
 */
public class ViewAttendanceReport extends BaseRoleController {

    
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
        StudentDBContext sdb = new StudentDBContext();
        ArrayList<Student> list = sdb.getByGroup(grid);
        request.setAttribute("list", list);
        request.getRequestDispatcher("../view/lecturer/attendance/attendanceReport.jsp").forward(request, response);
    }

}
