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
import model.DTO.TimePainelAdmDTO;
import model.dao.impl.TimeDao;

/**
 *
 * @author Thiago
 */
public class CallViewPainelTimes implements ICommanderAction {

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("Template.jsp?page=painelTimes");
        
        request.removeAttribute("times");
        
        TimeDao tdao = new TimeDao();
        
        List<TimePainelAdmDTO> timesDto = tdao.buscarTodosPainelDto();
        if (timesDto.size() == 0) {
            request.setAttribute("mensagem", "Não há times cadastrados!");
        }else{
            request.setAttribute("times", timesDto);
        }   
        tdao.close();
        rd.forward(request, response);
    }

}
