/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.impl.campeonato;

import controller.action.ICommanderAction;
import controller.action.view.CallViewPainelCampeonatoAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.impl.CampeonatoDao;
import model.domain.Campeonato;
import model.domain.EStatusCampeonato;

/**
 *
 * @author Thiago
 */
public class FinalizarCampeonatoAction implements ICommanderAction{

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CampeonatoDao cdao = new CampeonatoDao();
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        Campeonato campeonato = cdao.buscarUm(id);
        campeonato.setStatus(EStatusCampeonato.FINALIZADO);
        campeonato = cdao.alterar(campeonato);
        
        // Colocar campeonato no histórico
        // Apagar os registros dos usuários e times.
        
        cdao.close();
        new CallViewPainelCampeonatoAction().executar(request, response);
    }
    
}
