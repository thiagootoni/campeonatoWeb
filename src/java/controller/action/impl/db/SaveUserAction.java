/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.impl.db;

import controller.action.ICommanderAction;
import controller.action.view.CallViewHomeAction;
import controller.action.view.CallViewLoginAction;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DTO.TimeDTO;
import model.dao.impl.CampeonatoDao;
import model.dao.impl.TimeDao;
import model.dao.impl.UsuarioDao;
import model.domain.Campeonato;
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

        String nome = request.getParameter("iptNome");
        String login = request.getParameter("iptLogin");
        String senha = request.getParameter("iptSenha");
        String time = request.getParameter("time");

        Usuario user = new Usuario();

        if (verificaCamposEssenciais(nome, login, senha)) {

            if (!user.validaSeExiste(request.getParameter("iptLogin"))) {
                user.setNome(request.getParameter("iptNome"));
                user.setLogin(request.getParameter("iptLogin"));
                user.setSenha(request.getParameter("iptSenha"));
                user.setTime(new TimeDao().buscarUm(Integer.parseInt(request.getParameter("slcTime"))));

                Campeonato campeonato = verificaSeHaCampeonatoEmAberto();
                if (campeonato != null) {
                    if (campeonato.getQtdUsuarios() > campeonato.getParticipantes().size()) {
                        user.setCampeonato(campeonato);
                        UsuarioDao uDao = new UsuarioDao();
                        uDao.inserir(user);
                        request.setAttribute("erro", "Cadastro concluído com Sucesso! Entre para saber qual será seu próximo desafio!");
                        uDao.close();
                        new CallViewLoginAction().executar(request, response);
                    } else {
                        request.setAttribute("erro", "Volta depois fera! O campeonato tá cheio!");
                        new CallViewLoginAction().executar(request, response);
                    }

                } else {
                    request.setAttribute("erro", "Volta depois fera! No momento não há campeonatos abertos!");
                    new CallViewLoginAction().executar(request, response);
                }

            } else {

                RequestDispatcher rd = request.getRequestDispatcher("Template.jsp?page=index");
                request.setAttribute("usuarioExistente", "Login já existente");
                rd.forward(request, response);

            }
        } else {
            request.setAttribute("erro", "Campos faltantes! Refaça o cadastro!");
            user.setNome(nome);
            user.setLogin(login);
            request.setAttribute("user", user);
            new CallViewLoginAction().executar(request, response);
        }

    }

    public boolean verificaCamposEssenciais(String nome, String login, String senha) {
        if (nome.isEmpty() || login.isEmpty() || senha.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public Campeonato verificaSeHaCampeonatoEmAberto() throws SQLException {
        CampeonatoDao cdao = new CampeonatoDao();
        Campeonato campeonato = cdao.getCampeonatoAberto();
        cdao.close();
        return campeonato;
    }

}
