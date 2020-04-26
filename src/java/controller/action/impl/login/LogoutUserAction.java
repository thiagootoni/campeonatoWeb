/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.impl.login;

import controller.action.ICommanderAction;
import controller.action.view.CallViewLoginAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thiago
 */
public class LogoutUserAction implements ICommanderAction{

    @Override
    public boolean ehLiberado() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        new CallViewLoginAction().executar(request, response);
    }
    
}
