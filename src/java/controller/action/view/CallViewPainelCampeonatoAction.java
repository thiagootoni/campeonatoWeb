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
import model.DTO.CampeonatoPainelAdmDto;
import model.dao.impl.CampeonatoDao;
import model.domain.Campeonato;
import model.domain.EStatusCampeonato;

/**
 *
 * @author Thiago
 */
public class CallViewPainelCampeonatoAction implements ICommanderAction{

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("Template.jsp?page=painelCampeonato");
        
        boolean temCampeonatoAberto = new CampeonatoDao().checkCampeonatoAberto();
        request.setAttribute("temCampeonatoAberto", temCampeonatoAberto);
                
        List<CampeonatoPainelAdmDto> campsPainelDto = new CampeonatoDao().buscarTodosCampPainelDto();
        request.setAttribute("campsPainelDto", campsPainelDto);
        
        rd.forward(request, response);
    }
    
}
