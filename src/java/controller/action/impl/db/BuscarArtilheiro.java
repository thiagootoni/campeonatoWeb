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
import java.util.Collections;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.impl.CampeonatoDao;
import model.dao.impl.JogadorDao;
import model.dao.impl.TimeDao;
import model.domain.Campeonato;
import model.domain.Gol;
import model.domain.Jogador;
import model.domain.Jogo;

/**
 *
 * @author hugo.alexandre
 */
public class BuscarArtilheiro implements ICommanderAction {

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Jogador> todosJogadores = new JogadorDao().buscarTodos();
        List<Jogador> jogadoresGoleadores = new ArrayList<>();
        Campeonato camp = new CampeonatoDao()
                .buscaCampeonatoEmAbertoOuEmAndamento();

        if (validaTodosOsJogos(camp)) {
            try {
                new JogadorDao()
                        .buscarUm(
                                new CampeonatoDao()
                                        .getCampeonatoAberto()
                                        .getArtilheiro()
                                        .getId());

                new CallViewHomeAction().executar(request, response);
            } catch (Exception exp) {
                for (Jogador jogador : todosJogadores) {
                    if (jogador.getGolsFeitos().size() > 0) {
                        jogadoresGoleadores.add(jogador);
                    }

                }
                Collections.sort(
                        jogadoresGoleadores,
                        (j1, j2)
                        -> j2.getGolsFeitos().size()
                        - j1.getGolsFeitos().size());

                Jogador artilheiro = jogadoresGoleadores.get(0);

                camp.setArtilheiro(artilheiro);
                /*new CampeonatoDao()
                .buscaCampeonatoEmAbertoOuEmAndamento()
                .setArtilheiro(artilheiro);*/
                artilheiro.getTime().setPontos(artilheiro.getTime().getPontos() + 5);
                new TimeDao().alterar(artilheiro.getTime());

                new CampeonatoDao().alterar(camp);

                request.setAttribute("artilheiro", artilheiro);
                new CallViewHomeAction().executar(request, response);
            }

        } else if (camp == null) {
            request.setAttribute("naojogados", "Não é possivel definir o Artilheiro nas férias né? Abre um campeonato ai Champs!");
            request.setAttribute("artilheiro", null);
            new CallViewHomeAction().executar(request, response);
        } else {
            request.setAttribute("naojogados", "Não é possivel definir o Artilheiro com jogos em aberto! Já ta querendo parar é?");
            request.setAttribute("artilheiro", null);
            new CallViewHomeAction().executar(request, response);
        }
    }

    public static boolean validaTodosOsJogos(Campeonato camp) throws SQLException {
        List<Jogador> todosJogadores = new JogadorDao().buscarTodos();
        for (Jogador jogador : todosJogadores) {
            jogador.getGolsFeitos();
            for (Gol gol : jogador.getGolsFeitos()) {
                gol.getId();
            }
        }

        int cont = 0;
        for (Jogador jogador : todosJogadores) {
            if (jogador.getGolsFeitos().size() < 1) {
                cont++;
            }
        }
        if (cont == todosJogadores.size()) {
            return false;
        }

        if (camp == null) {
            return false;
        }

        for (Jogo jogo : camp.getJogos()) {
            if (!jogo.isFoiJogado()) {
                return false;
            }
        }
        return true;
    }
}
