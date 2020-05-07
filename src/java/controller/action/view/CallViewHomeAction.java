/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.view;

import controller.action.ICommanderAction;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.impl.CampeonatoDao;
import model.dao.impl.JogadorDao;
import model.dao.impl.JogoDao;
import model.dao.impl.TimeDao;
import model.domain.Campeonato;
import model.domain.Jogador;
import model.domain.Time;

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
        CampeonatoDao cDao = new CampeonatoDao();
        Campeonato campeonato = cDao.buscaCampeonatoEmAbertoOuEmAndamento();
        TimeDao tDao = new TimeDao();
        JogoDao jDao = new JogoDao();
        List<Time> times = null;
        if (campeonato != null) {
            campeonato.setJogos(jDao.buscarTodosPorCampeonato(campeonato.getId()));
            times = tDao.buscarTodosDoCampeonato(campeonato.getId());
        }

        try {

            Jogador artilheiro = new JogadorDao()
                    .buscarUm(
                            new CampeonatoDao()
                                    .getCampeonatoAberto()
                                    .getArtilheiro()
                                    .getId());

            request.setAttribute("artilheiro", artilheiro);
        } catch (Exception ex) {
            request.setAttribute("artilheiro", null);
        }

        if (campeonato != null) {
            request.setAttribute("campeonato", campeonato);
        }
        request.setAttribute("times", times);
        tDao.close();
        cDao.close();
        rd.forward(request, response);
    }

    @Override
    public boolean ehLiberado() {
        return false;
    }

}


