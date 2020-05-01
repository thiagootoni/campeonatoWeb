/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.impl.db;

import controller.action.ICommanderAction;
import controller.action.view.CallViewPainelTimes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.impl.TimeDao;
import model.domain.Time;

/**
 *
 * @author Thiago
 */
public class AlterarTimeAction implements ICommanderAction{

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeDao tdao = new TimeDao();
        
        int idTime = Integer.parseInt(request.getParameter("id"));
        Time time = tdao.buscarUm(idTime);
        
        request.setAttribute("time", time);
        request.setAttribute("botaoAlterar", "alterar");
        new CallViewPainelTimes().executar(request, response);
    }
    
}
