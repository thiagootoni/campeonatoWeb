/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.impl.campeonato;

import controller.action.ICommanderAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.impl.CampeonatoDao;
import model.domain.Campeonato;
import model.domain.EStatusCampeonato;

/**
 *
 * @author Thiago
 */
public class IniciarCampeonatoAction implements ICommanderAction{

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CampeonatoDao cDao = new CampeonatoDao();
        
        int idCampeonato = Integer.parseInt(request.getParameter("id"));
        Campeonato campeonato = cDao.buscarUm(idCampeonato);
        campeonato.setStatus(EStatusCampeonato.EM_ANDAMENTO);
    }
    
}
