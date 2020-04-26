/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.view;

import controller.action.ICommanderAction;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hugo.alexandre
 */
public class CallViewLoginAction implements ICommanderAction {

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        if (request.getSession().getAttribute("user") != null) {
            new CallViewHomeAction().executar(request, response);
        }else{
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    public boolean ehLiberado() {
        return true;
    }

}
