/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.impl.db;

import controller.action.ICommanderAction;
import controller.action.view.CallViewLoginAction;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DTO.TimeDTO;
import model.dao.impl.TimeDao;
import model.dao.impl.UsuarioDao;

/**
 *
 * @author hugo.alexandre
 */
public class LoadTimesDisponiveisAction implements ICommanderAction{

    @Override
    public boolean ehLiberado() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        List<TimeDTO>timesDisponiveis = new TimeDao().retornaTimesDisponiveis();
         
    
    }
    
}
