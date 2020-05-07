/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.impl.campeonato;

import controller.action.ICommanderAction;
import controller.action.view.CallViewPainelCampeonatoAction;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.impl.CampeonatoDao;
import model.dao.impl.GolDao;
import model.dao.impl.TimeDao;
import model.dao.impl.UsuarioDao;
import model.domain.Campeonato;
import model.domain.EStatusCampeonato;
import model.domain.Time;
import model.domain.Usuario;

/**
 *
 * @author Thiago
 */
public class FinalizarCampeonatoAction implements ICommanderAction {

    @Override
    public boolean ehLiberado() {
        return false;
    }

    @Override
    public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CampeonatoDao cdao = new CampeonatoDao();

        int id = Integer.parseInt(request.getParameter("id"));

        Campeonato campeonato = cdao.buscarUm(id);
        // Mudar o status do campeonato
        campeonato.setStatus(EStatusCampeonato.FINALIZADO);
        campeonato = cdao.alterar(campeonato);
        cdao.close();

        // guardar os dados do campeão
        TimeDao tDao = new TimeDao();
        String nomeDoCampeao ="";
       try{
        nomeDoCampeao = tDao.buscarTimeDoCampeao(id).getUsuario().getNome();
       }
       catch(Exception ex){
        nomeDoCampeao = "";
       }
       
        tDao.close();
        int idCampeonatoFinalizado = campeonato.getId();

        // Apagar os registros dos usuários
        removerUsuarios(campeonato);

        //Apagar os gols dos jogadores
        GolDao gDao = new GolDao();
        gDao.apagarTodos();

        // Em times: Zerar Stats
        zerarStatsTimes(campeonato);
        request.setAttribute("mensagem", "Campeonato finalizado e adicionado ao Histórico");

        //Criar um usuário fake e inutilizável para guardar dados do campeão
        Usuario campeao = new Usuario();
        campeao.setNome(nomeDoCampeao);
        campeao.setCampeonato(campeonato);
        campeao.setLogin(geraStringAleatoria());
        campeao.setSenha(geraStringAleatoria());
        new UsuarioDao().inserir(campeao);
        CampeonatoDao cDao = new CampeonatoDao();
        campeonato = cDao.buscarUm(id);
        campeonato.setCampeao(campeao);
        cDao.alterar(campeonato);
        cDao.close();
        //

        new CallViewPainelCampeonatoAction().executar(request, response);
    }

    public void zerarStatsTimes(Campeonato campeonato) throws SQLException {
        TimeDao tDao = new TimeDao();
        //List<Time> times = tDao.buscarTodosDoCampeonato(campeonato.getId());
        List<Time> times = tDao.buscarTodos();
                
        for (Time time : times) {
            time.zerarStats();
            tDao.alterar(time);
        }
        tDao.close();
    }

    public void removerUsuarios(Campeonato campeonato) {
        UsuarioDao uDao = new UsuarioDao();
        int usuariosRemovidos = uDao.apagarUsuariosCampeonatoFinalizado(campeonato);
        uDao.close();
    }

    public String geraStringAleatoria() {
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        return myRandom.substring(0, 20);
    }
}
