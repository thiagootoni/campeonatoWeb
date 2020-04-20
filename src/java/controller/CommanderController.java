/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.action.ICommanderAction;
import controller.action.impl.db.SaveUserAction;
import controller.action.view.CallViewCadastroAction;
import controller.action.view.CallViewHomeAction;
import controller.action.view.CallViewLoginAction;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hugo.alexandre
 */
@WebServlet(name = "CommanderController", urlPatterns = {"/central"})
public class CommanderController extends HttpServlet {

    private static Hashtable<String, ICommanderAction> comandos;

    static {
        comandos = new Hashtable<>();
        comandos.put("login", new CallViewLoginAction());
        comandos.put("home", new CallViewHomeAction());
        comandos.put("saveUser", new SaveUserAction());

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String ac = request.getParameter("ac");
        if (ac == null) {
            RequestDispatcher rd = request.getRequestDispatcher("Template.jsp?page=erro");
            request.setAttribute("err", "ac está chegando null");
            rd.forward(request, response);
        } else {
            try {
                comandos.get(ac).executar(request, response);
            } catch (Exception ex) {

                RequestDispatcher rd = request.getRequestDispatcher("Template.jsp?page=erro");
                request.setAttribute("err", "Comando não encontrado!");
                rd.forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
