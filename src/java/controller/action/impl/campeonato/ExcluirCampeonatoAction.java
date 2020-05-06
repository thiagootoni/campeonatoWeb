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

/**
 *
 * @author Thiago
 */
public class ExcluirCampeonatoAction implements ICommanderAction{

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        int idCampeonato = Integer.parseInt(request.getParameter("id"));
        
        CampeonatoDao cDao = new CampeonatoDao();
        cDao.apagar(idCampeonato);
        request.setAttribute("mensagem", "Campeonato excluído com sucesso!");
        new CallViewPainelCampeonatoAction().executar(request, response);
    }
    
}
