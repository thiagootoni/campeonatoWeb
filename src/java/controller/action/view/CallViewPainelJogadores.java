/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.view;

import controller.action.ICommanderAction;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DTO.JogadorPainelAdmDTO;
import model.DTO.TimePainelAdmDTO;
import model.dao.impl.JogadorDao;
import model.dao.impl.TimeDao;

/**
 *
 * @author Thiago
 */
public class CallViewPainelJogadores implements ICommanderAction {

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("Template.jsp?page=painelJogadores");
        
        request.removeAttribute("jogadores");
        
        TimeDao tdao = new TimeDao();
        List<TimePainelAdmDTO> times = tdao.buscarTodosPainelDto();
        
        JogadorDao jdao = new JogadorDao();
        List<JogadorPainelAdmDTO> jogadores = jdao.buscarTodosPainelAdmDto();
        
        if (times.size() == 0) {
            request.setAttribute("mensagem", "Não há times disponíveis! Para adicionar jogadores, adicione times primeiro");
        }
        
        request.setAttribute("times", times);
        request.setAttribute("jogadores", jogadores);
        
        rd.forward(request, response);
    }

}
