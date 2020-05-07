/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.view;

import controller.action.ICommanderAction;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DTO.TimeDTO;
import model.dao.impl.CampeonatoDao;
import model.dao.impl.TimeDao;
import model.domain.Campeonato;

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
            Campeonato campeonato = new CampeonatoDao().getCampeonatoAbertoOuEmAndamento();
            List<TimeDTO>timesDisponiveis = new TimeDao().retornaTimesDisponiveis();
            //request.setAttribute("timedisponivel", timesDisponiveis.get(0));
            int vagasDisponiveis = campeonato.getQtdUsuarios() - campeonato.getParticipantes().size();
            
            request.setAttribute("vagasDisponiveis", timesDisponiveis.size());
            
            request.setAttribute("timesdisponiveis", timesDisponiveis);
            request.setAttribute("nomeCampeonato", campeonato.getNome());
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
            
          
        }
    }

    @Override
    public boolean ehLiberado() {
        return true;
    }

}
