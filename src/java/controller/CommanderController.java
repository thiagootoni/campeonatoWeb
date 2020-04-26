/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.action.ICommanderAction;
import controller.action.impl.login.LogarUserAction;
import controller.action.impl.db.SaveUserAction;
import controller.action.impl.login.LogoutUserAction;
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
        comandos.put("", new CallViewLoginAction());
        comandos.put("login", new CallViewLoginAction());
        comandos.put("home", new CallViewHomeAction());
        comandos.put("saveUser", new SaveUserAction());
        comandos.put("logarUsuario", new LogarUserAction());
        comandos.put("logoutUsuario", new LogoutUserAction());

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String ac = request.getParameter("ac");
        ac = (ac == null) ? "" : ac;

        try {
            if (comandos.get(ac).ehLiberado()) {
                comandos.get(ac).executar(request, response);
            } else if (request.getSession().getAttribute("user") != null) {
                comandos.get(ac).executar(request, response);
            } else {
                request.setAttribute("erro", "Acesso restrito, faça login para aproveitar o site!");
                new CallViewLoginAction().executar(request, response);
            }

        } catch (Exception ex) {
            if (request.getSession().getAttribute("user")!= null) {
                RequestDispatcher rd = request.getRequestDispatcher("Template.jsp?page=home");
                request.setAttribute("erro", "Comando inexistente: " + ac);                
                rd.forward(request, response);
            }else{                
                RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
                request.setAttribute("erro", "Comando inexistente: " + ac);
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
