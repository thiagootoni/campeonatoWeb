/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.impl.db;

import controller.action.ICommanderAction;
import controller.action.view.CallViewHomeAction;
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
import model.domain.Jogador;

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
        try {
            new JogadorDao()
                    .buscarUm(
                            new CampeonatoDao()
                                    .getCampeonatoAberto()
                                    .getArtilheiro()
                                    .getId());

            new CallViewHomeAction().executar(request, response);
        } catch (Exception ex) {
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

            Campeonato camp = new CampeonatoDao()
                    .buscaCampeonatoEmAbertoOuEmAndamento();

            camp.setArtilheiro(artilheiro);
            /*new CampeonatoDao()
                .buscaCampeonatoEmAbertoOuEmAndamento()
                .setArtilheiro(artilheiro);*/

            new CampeonatoDao().alterar(camp);
            artilheiro.getTime().setPontos(artilheiro.getTime().getPontos() + 5);
            new TimeDao().alterar(artilheiro.getTime());
            request.setAttribute("artilheiro", artilheiro);
            new CallViewHomeAction().executar(request, response);
        }

    }

}
