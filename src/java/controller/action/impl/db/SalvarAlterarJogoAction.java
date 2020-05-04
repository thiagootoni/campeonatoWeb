/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.impl.db;

import controller.action.ICommanderAction;
import controller.action.view.CallViewHomeAction;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.impl.GolDao;
import model.dao.impl.JogoDao;
import model.dao.impl.TimeDao;
import model.domain.EDiscriminanteGol;
import model.domain.Gol;
import model.domain.Jogador;
import model.domain.Jogo;
import model.domain.Time;

/**
 *
 * @author Thiago
 */
public class SalvarAlterarJogoAction implements ICommanderAction {

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int idJogo = Integer.parseInt(request.getParameter("idJogo"));
        int idDesafiante = Integer.parseInt(request.getParameter("idDesafiante"));
        int idDesafiado = Integer.parseInt(request.getParameter("idDesafiado"));
        int placarDesafiante = Integer.parseInt(request.getParameter("placarDesafiante"));
        int placarDesafiado = Integer.parseInt(request.getParameter("placarDesafiado"));
        String[] idJogadoresMarcadores = request.getParameterValues("golsIdJogador");
        String[] golsMarcadosPorJogador = request.getParameterValues("golsQtdJogador");
        String[] infoDentroFora = request.getParameterValues("infoDentroFora");

        if (comparaScoreJogadoresComScorePartida((placarDesafiado + placarDesafiante), golsMarcadosPorJogador)) {
            JogoDao jDao = new JogoDao();
            Jogo jogo = jDao.buscarUm(idJogo);
            jogo.setFoiJogado(true);
            jogo.setGolsDesafiado(placarDesafiado);
            jogo.setGolsDesafiante(placarDesafiante);
            jDao.alterar(jogo);
            jDao.close();

            computaPersisteGols(idJogadoresMarcadores, golsMarcadosPorJogador, infoDentroFora, jogo);

            TimeDao tDao = new TimeDao();
            Time timeDesafiante = tDao.buscarUm(idDesafiante);
            Time timeDesafiado = tDao.buscarUm(idDesafiado);
            timeDesafiante.calculaPontuacaoEStats(placarDesafiante, placarDesafiado);
            timeDesafiado.calculaPontuacaoEStats(placarDesafiado, placarDesafiante);
            tDao.alterar(timeDesafiante);
            tDao.alterar(timeDesafiado);
            tDao.close();
            request.setAttribute("mensagem", "Jogo atualizado com Sucesso");
        } else {
            request.setAttribute("mensagem", "Número de gols do placar não é igual à soma dos gols dos jogadores!");
        }
        
        new CallViewHomeAction().executar(request, response);
    }

    public ArrayList<Gol> computaPersisteGols(String[] vetorDeId, String[] VetorDeQtdGols, String[] info, Jogo jogo) throws SQLException {
        ArrayList<Gol> gols = new ArrayList<>();
        int tamanho = vetorDeId.length;

        GolDao gDao = new GolDao();

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < Integer.parseInt(VetorDeQtdGols[i]); j++) {
                Jogador jogador = new Jogador();
                jogador.setId(Integer.parseInt(vetorDeId[i]));
                Gol gol = new Gol(jogador, jogo, EDiscriminanteGol.valueOf(info[i]));
                gols.add(gol);
                gDao.inserir(gol);
            }
        }
        gDao.close();
        return gols;
    }

    public boolean comparaScoreJogadoresComScorePartida(int golsPartida, String[] vetorGolsJogadores) {
        int golsJogadores = 0;

        for (String gol : vetorGolsJogadores) {
            golsJogadores += Integer.parseInt(gol);
        }

        return golsJogadores == golsPartida;
    }
}
