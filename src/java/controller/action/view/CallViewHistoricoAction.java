/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.view;

import controller.action.ICommanderAction;
import controller.action.view.CallViewLoginAction;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.impl.CampeonatoDao;
import model.domain.Campeonato;

/**
 *
 * @author Thiago
 */
public class CallViewHistoricoAction implements ICommanderAction{

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("Template.jsp?page=historico");        
        
        try {
            List<Campeonato> campeonatos = new CampeonatoDao().getCampeonatosFinalizados();
            request.setAttribute("campeonatos", campeonatos);
        } catch (Exception e) { 
            request.setAttribute("mensagem", "Não há campeonatos finalizados! Jogue o primeiro para começar o histórico!");
        }         
        rd.forward(request, response);
    }
    
}
