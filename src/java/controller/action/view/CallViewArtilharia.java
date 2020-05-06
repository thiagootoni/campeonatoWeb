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
    Jogador maior = null;
    Jogador artilheiro = new JogadorDao()
            .buscarUm(
                    new CampeonatoDao()
                            .getCampeonatoAberto()
                            .getArtilheiro()
                            .getId());
    
    int golsFeitos = artilheiro.getGolsFeitos().size();
       List<Jogador> todosJogadores = new JogadorDao().buscarTodos();
      List<Jogador> jogadoresGoleadores = new ArrayList<>();
      
      for (Jogador jogador : todosJogadores) {
           if (jogador.getGolsFeitos().size() >0) 
           jogadoresGoleadores.add(jogador);    
        }
        Collections.sort(
                jogadoresGoleadores 
                ,(j1,j2) 
                 -> j2.getGolsFeitos().size() 
                 - j1.getGolsFeitos().size());
        
        request.setAttribute("jogadoresGoleadores", jogadoresGoleadores);
        request.setAttribute("golsfeitos",golsFeitos);
        request.setAttribute("artilheiro", artilheiro);
        
       rd.forward(request, response);
    }
    
}
