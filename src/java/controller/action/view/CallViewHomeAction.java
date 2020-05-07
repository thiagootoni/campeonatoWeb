/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.view;

import controller.action.ICommanderAction;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.impl.CampeonatoDao;
import model.dao.impl.GolDao;
import model.dao.impl.JogadorDao;
import model.dao.impl.JogoDao;
import model.dao.impl.TimeDao;
import model.domain.Campeonato;
import model.domain.Jogador;
import model.domain.Jogo;
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
        campeonato.getParticipantes();
        //campeonato.getJogos().get(0).getGolsDoJogo();
        
        List<Time> times = null;
        if (campeonato != null) {
            JogoDao jDao = new JogoDao();
            List<Jogo> jogos = jDao.buscarTodosPorCampeonato(campeonato.getId());
            jogos = buscaGolsDosJogos(jogos);
            campeonato.setJogos(jogos);
            jDao.close();

            TimeDao tDao = new TimeDao();            
            times = tDao.buscarTodosDoCampeonato(campeonato.getId());
            tDao.close();            
        }

        try {
            Jogador artilheiro = (Jogador)request.getAttribute("artilheiro");                    

            request.setAttribute("artilheiro", artilheiro);
        } catch (Exception ex) {
            request.setAttribute("artilheiro", null);
        }

        if (campeonato != null) {
            request.setAttribute("campeonato", campeonato);
        }
        
        request.setAttribute("times", times);
        cDao.close();
        rd.forward(request, response);
    }

    @Override
    public boolean ehLiberado() {
        return false;
    }
    
    public List<Jogo> buscaGolsDosJogos(List<Jogo> jogos) throws SQLException{
        GolDao gDao = new GolDao();
        ArrayList<Jogo> jogosComGols = new ArrayList<>();
        
        for (Jogo jogo : jogos) {
            jogo.setGolsDoJogo(gDao.buscarTodosPorJogo(jogo.getId()));
            jogosComGols.add(jogo);
        }
        
        return jogosComGols;
    }

}
