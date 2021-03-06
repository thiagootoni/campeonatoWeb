/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.view;

import controller.action.ICommanderAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DTO.CampeonatoPainelAdmDto;
import model.dao.impl.CampeonatoDao;
import model.dao.impl.JogadorDao;
import model.domain.Campeonato;
import model.domain.Gol;
import model.domain.Jogador;

/**
 *
 * @author hugo.alexandre
 */
public class CallViewArtilharia implements ICommanderAction {

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        RequestDispatcher rd = request.getRequestDispatcher("Template.jsp?page=painelArtilharia");

        //Pegando artilheiro do campeonato
        JogadorDao jDao = new JogadorDao();

        Campeonato camps = new CampeonatoDao()
                .getCampeonatoAberto();
                

        if (camps == null || camps.getArtilheiro()==null) {

            List<Jogador> todosJogadores = jDao.buscarTodos();

            System.out.println(todosJogadores.get(2).getGolsFeitos().size());
            List<Jogador> jogadoresGoleadores = new ArrayList<>();
            int i = 0;
            for (Jogador jogador : todosJogadores) {
                jogador.getGolsFeitos();
                for (Gol golsFeito : jogador.getGolsFeitos()) {
                    golsFeito.getId();
                }
                if (jogador.getGolsFeitos().size() > 0) {

                    jogadoresGoleadores.add(jogador);
                }
                i++;
            }
            Collections.sort(
                    jogadoresGoleadores,
                    (j1, j2)
                    -> j2.getGolsFeitos().size()
                    - j1.getGolsFeitos().size());

            request.setAttribute("jogadoresGoleadores", jogadoresGoleadores);
            request.setAttribute("artilheiro", null);

        } else {
            Jogador artilheiro = jDao
                    .buscarUm(camps.getId());

            request.setAttribute("artilheiro", artilheiro);
        }
/*
        try {

            Jogador artilheiro = jDao
                    .buscarUm(
                            new CampeonatoDao()
                                    .getCampeonatoAberto()
                                    .getArtilheiro()
                                    .getId());

            request.setAttribute("artilheiro", artilheiro);
        } //int golsFeitos = artilheiro.getGolsFeitos().size();
        catch (Exception ex) {
            List<Jogador> todosJogadores = jDao.buscarTodos();

            System.out.println(todosJogadores.get(2).getGolsFeitos().size());
            List<Jogador> jogadoresGoleadores = new ArrayList<>();
            int i = 0;
            for (Jogador jogador : todosJogadores) {
                jogador.getGolsFeitos();
                for (Gol golsFeito : jogador.getGolsFeitos()) {
                    golsFeito.getId();
                }
                if (jogador.getGolsFeitos().size() > 0) {

                    jogadoresGoleadores.add(jogador);
                }
                i++;
            }
            Collections.sort(
                    jogadoresGoleadores,
                    (j1, j2)
                    -> j2.getGolsFeitos().size()
                    - j1.getGolsFeitos().size());

            request.setAttribute("jogadoresGoleadores", jogadoresGoleadores);
            request.setAttribute("artilheiro", null);
        } finally {
            jDao.close();
            //System.out.println("Passei aq");
        }*/

        rd.forward(request, response);
    }

}
