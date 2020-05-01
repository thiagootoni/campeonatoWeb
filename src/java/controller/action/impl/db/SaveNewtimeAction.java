/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.impl.db;

import controller.action.ICommanderAction;
import controller.action.view.CallViewPainelTimes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.impl.TimeDao;
import model.domain.Time;

/**
 *
 * @author Thiago
 */
public class SaveNewtimeAction implements ICommanderAction {

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String nomeTime = request.getParameter("nomeTime");  
        

        Time time = new Time();
        time.setNome(nomeTime);

        if (verificaCamposEssenciais(nomeTime)) {
            TimeDao tdao = new TimeDao();
            String alterar = request.getParameter("alterar");
            
            if (alterar != null && alterar.equals("alterar")) {                
                int idTime = Integer.parseInt(request.getParameter("id"));                
                time.setId(idTime);
                tdao.alterar(time);                
                request.setAttribute("mensagem", "Time alterado com sucesso!");
            } else {
                tdao.inserir(time);
                request.setAttribute("mensagem", "Time salvo com sucesso!");
            }
            tdao.close();
            new CallViewPainelTimes().executar(request, response);

        } else {
            request.setAttribute("time", time);
            request.setAttribute("mensagem", "Campos faltantes!");

            new CallViewPainelTimes().executar(request, response);
        }
    }

    public boolean verificaCamposEssenciais(String nome) {
        if (nome.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
