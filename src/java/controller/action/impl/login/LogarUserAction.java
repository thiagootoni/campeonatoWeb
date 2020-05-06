/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.impl.login;

import controller.action.ICommanderAction;
import controller.action.view.CallViewHomeAction;
import controller.action.view.CallViewLoginAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DTO.UsuarioLogadoDTO;
import model.dao.impl.UsuarioDao;

/**
 *
 * @author Thiago
 */
public class LogarUserAction implements ICommanderAction {

    @Override
    public boolean ehLiberado() {
        return true;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String login = request.getParameter("iptLogin");
        String senha = request.getParameter("iptSenha");

        UsuarioLogadoDTO udto = new UsuarioDao().buscarPorLoginESenha(login, senha);

        if (udto != null && verificaCamposEssenciais(login, senha)) {
            //request.setAttribute("udto", udto);
            request.getSession().setAttribute("user", udto);
            new CallViewHomeAction().executar(request, response);
        } else {
            request.setAttribute("erro", "Login e/ou senha incorretos!");
            new CallViewLoginAction().executar(request, response);
        }
    }

    public boolean verificaCamposEssenciais(String login, String senha) {
        return !(login.isEmpty() || senha.isEmpty());
    }

}
