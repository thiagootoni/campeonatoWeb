/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.impl.db;

import controller.action.ICommanderAction;
import controller.action.view.CallViewPainelJogadores;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.impl.JogadorDao;
import model.dao.impl.TimeDao;
import model.domain.Jogador;

/**
 *
 * @author Thiago
 */
public class SaveNewJogadorAction implements ICommanderAction{

    @Override
    public boolean ehLiberado() {
       return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nome = request.getParameter("nomeJogador");
        String idTime = request.getParameter("timeJogador");
        
        
        if (verificaCamposEssenciais(nome, idTime)) {
            TimeDao tDao = new TimeDao();
            
            Jogador jogador = new Jogador();
            jogador.setNome(nome);
            jogador.setTime(tDao.buscarUm(Integer.parseInt(idTime)));
            
            tDao.close();
            JogadorDao jDao = new JogadorDao();
            
            jDao.inserir(jogador);
            jDao.close();
            
            request.setAttribute("mensagem", "Jogador salvo com sucesso!");
            new CallViewPainelJogadores().executar(request, response);
        }else{
            Jogador jogador = new Jogador();
            jogador.setNome(nome);
            request.setAttribute("mensagem", "Campos faltantes!");
            request.setAttribute("jogador", jogador);
            new CallViewPainelJogadores().executar(request, response);
        }       
        
    }
    
    public boolean verificaCamposEssenciais(String nome, String id){
        return (!(nome.isEmpty() || id.isEmpty()));
    }
    
}
