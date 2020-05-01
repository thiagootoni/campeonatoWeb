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
import model.domain.Jogador;

/**
 *
 * @author Thiago
 */
public class ExcluirJogadorAction implements ICommanderAction{

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JogadorDao jDao = new JogadorDao();
        
        int idJogador = Integer.parseInt(request.getParameter("id"));
        jDao.apagar(idJogador);
        
        request.setAttribute("mensagem", "Jogador Excluído com sucesso!");
        new CallViewPainelJogadores().executar(request, response);
        
        
    }
    
}
