/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.impl.db;

import controller.action.ICommanderAction;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DTO.TimeDTO;
import model.dao.impl.TimeDao;
import model.dao.impl.UsuarioDao;
import model.domain.Time;
import model.domain.Usuario;

/**
 *
 * @author hugo.alexandre
 */
public class SaveUserAction implements ICommanderAction {

    @Override
    public boolean ehLiberado() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {


 
        Usuario user = new Usuario();
        
        if (!user.validaSeExiste(request.getParameter("iptLogin"))) {
            user.setNome(request.getParameter("iptNome"));
            user.setLogin(request.getParameter("iptLogin"));
            user.setSenha(request.getParameter("iptSenha"));
            user.setTime(new TimeDao().buscarUm(new TimeDTO().retornaIdTime(request.getParameter("slcTime"))));
            new UsuarioDao().inserir(user);
        } else {
                
             RequestDispatcher rd = request.getRequestDispatcher("Template.jsp?page=index");
             request.setAttribute("usuarioExistente", "Login já existente");
             rd.forward(request, response);
             
        }

    }

}
