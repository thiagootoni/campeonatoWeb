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
public class CallViewHomeAction implements ICommanderAction {

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("Template.jsp?page=home");
        
        // Pegar o campeonato que não está finalizado e manda pra view
        // Na view verifica o status e renderiza de acordo com o perfil do usuário       
        
        rd.forward(request, response);
    }

    @Override
    public boolean ehLiberado() {
        return false;
    }
}
