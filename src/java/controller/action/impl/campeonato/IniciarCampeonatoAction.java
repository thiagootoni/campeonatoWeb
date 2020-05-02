/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.impl.campeonato;

import controller.action.ICommanderAction;
import controller.action.view.CallViewHomeAction;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.impl.CampeonatoDao;
import model.dao.impl.JogoDao;
import model.domain.Campeonato;
import model.domain.EStatusCampeonato;
import model.domain.Jogo;

/**
 *
 * @author Thiago
 */
public class IniciarCampeonatoAction implements ICommanderAction {

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CampeonatoDao cDao = new CampeonatoDao();

        int idCampeonato = Integer.parseInt(request.getParameter("id"));

        try {
            Campeonato campeonato = cDao.buscarUm(idCampeonato);
            campeonato.setStatus(EStatusCampeonato.EM_ANDAMENTO);

            ArrayList<Jogo> jogosOrdenados = campeonato.criaTabela();
            persisteJogos(campeonato, jogosOrdenados);
            cDao.alterar(campeonato);
            
            request.setAttribute("mensagem", "Campeonato iniciado com sucesso!");
            cDao.close();
            new CallViewHomeAction().executar(request, response);
        } catch (Exception e) {
            cDao.close();
            request.setAttribute("mensagem", "Campeonato deu pau!: " + e.getMessage());
            new CallViewHomeAction().executar(request, response);
        }

    }

    public ArrayList<Jogo> criaJogos(int qtdJogos, Campeonato c) {
        ArrayList<Jogo> jogos = new ArrayList<>();

        for (int i = 0; i < qtdJogos; i++) {
            Jogo j = new Jogo();
            j.setCampeonato(c);
            jogos.add(j);
        }

        return jogos;
    }

    public void persisteJogos(Campeonato campeonato, ArrayList<Jogo> jogos) throws SQLException {
        JogoDao jDao = new JogoDao();
        for (Jogo jogo : jogos) {
            jogo.setCampeonato(campeonato);
            jDao.inserir(jogo);
        }
    }

}
