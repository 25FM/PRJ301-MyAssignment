/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Account;

/**
 *
 * @author sonnt
 */
public abstract class BaseAuthenticationController extends HttpServlet {

    private boolean isAuthenticated(HttpServletRequest req) {
        return req.getSession().getAttribute("account") != null;
    }

    protected abstract void processPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    protected abstract void processGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthenticated(req)) {
            //code business
            processPost(req, resp);
        } else {
            if (req.getServletPath().equals("lecturer/takeattendance/after")) {
                resp.sendRedirect("../../view/home.jsp");
            }
            resp.sendRedirect("../view/home.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthenticated(req)) {
            //code business
            processGet(req, resp);
        } else {
            if (req.getServletPath().equals("/lecturer/takeattendance/after")) {
                resp.sendRedirect("../../view/home.jsp");
            } else {
                resp.sendRedirect("../view/home.jsp");
            }
        }
    }

}
