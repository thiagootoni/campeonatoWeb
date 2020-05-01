/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.impl.db;

import controller.action.ICommanderAction;
import controller.action.view.CallViewPainelCampeonatoAction;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.impl.CampeonatoDao;
import model.domain.Campeonato;
import model.domain.EStatusCampeonato;

/**
 *
 * @author Thiago
 */
public class SaveCampeonatoAction implements ICommanderAction{

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nomeCampeonato = request.getParameter("nomeCampeonato");
        String qtdDeJogadres = request.getParameter("numeroPlayers");
        
        Campeonato campeonato = new Campeonato(nomeCampeonato, Integer.parseInt(qtdDeJogadres));
        
        if (verificaCamposEssenciais(nomeCampeonato, qtdDeJogadres)) {
            CampeonatoDao cdao = new CampeonatoDao();            
            cdao.inserir(campeonato);
            
            request.setAttribute("mensagem", "Campeonto inserido com sucesso!");
            // fechar cdao aqui? cdao.close();
            cdao.close();
            new CallViewPainelCampeonatoAction().executar(request, response);
            
        }else{
            request.setAttribute("campeonato", campeonato);
            request.setAttribute("mensagem", "Dados Faltantes");
            
            new CallViewPainelCampeonatoAction().executar(request, response);
        }
        
    }
    
    public boolean verificaCamposEssenciais(String nome, String qtd){
        if (nome.isEmpty() || qtd.isEmpty()) {
            return false;
        }else{
            return true;
        }
    }
    
}
