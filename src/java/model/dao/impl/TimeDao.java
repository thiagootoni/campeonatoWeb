/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import model.DTO.TimeDTO;
import model.DTO.TimePainelAdmDTO;
import model.dao.GenericsDao;
import model.domain.Campeonato;
import model.domain.Time;

/**
 *
 * @author Thiago
 */
public class TimeDao extends GenericsDao<Integer, Time> {

    @Override
    public Time inserir(Time obj) throws SQLException {
        this.getConexao().getTransaction().begin();
        this.getConexao().persist(obj);
        this.getConexao().getTransaction().commit();
        return obj;
    }

    @Override
    public Time alterar(Time obj) throws SQLException {
        this.getConexao().getTransaction().begin();
        this.getConexao().merge(obj);
        this.getConexao().getTransaction().commit();
        return obj;
    }

    @Override
    public void apagar(Integer key) throws SQLException {
        Time t = new Time();
        t.setId(key);
        t = this.buscarUm(key);

        this.getConexao().getTransaction().begin();
        this.getConexao().remove(t);
        this.getConexao().getTransaction().commit();

    }

    @Override
    public Time buscarUm(Integer key) throws SQLException {
        Query q = this.getConexao().createQuery("SELECT t FROM Time t WHERE t.id = :id");

        try {
            q.setParameter("id", key);
            return (Time) q.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    @Override
    public List<Time> buscarTodos() throws SQLException {
        Query q = this.getConexao().createQuery("SELECT t FROM Time t");

        return q.getResultList();
    }

    public List<TimePainelAdmDTO> buscarTodosPainelDto() throws SQLException {
        List<Time> times = this.buscarTodos();
        List<TimePainelAdmDTO> timesDto = new ArrayList<>();

        for (Time time : times) {
            TimePainelAdmDTO tdto = new TimePainelAdmDTO();
            tdto.setId(time.getId());
            tdto.setNome(time.getNome());
            tdto.setQtdJogadores(time.getJogadores().size());

            timesDto.add(tdto);
        }

        return timesDto;
    }

    public List<Time> buscarTodosDisponiveis() throws SQLException {
        Query q = this.getConexao().createQuery("SELECT t \n"
                + "FROM Time t\n"
                + "LEFT JOIN t.usuario u\n"
                + "where u.id is null");

        return (List<Time>) q.getResultList();
    }

    public List<TimeDTO> retornaTimesDisponiveis() throws SQLException {

        List<Time> times = this.buscarTodosDisponiveis();

        List<TimeDTO> timesDTO = new ArrayList<>();

        for (Time time : times) {
            timesDTO.add(new TimeDTO(time.getId(), time.getNome()));

        }
        return timesDTO;

    }
    
    public List<Time> buscarTodosDoCampeonato(Integer idCampeonato){
        Query q = this.getConexao().createQuery("SELECT t \n"
                + "FROM Time t \n"
                + "INNER JOIN t.usuario u \n"
                + "WHERE u.campeonato.id = :id \n"
                + "ORDER BY t.pontos DESC");
        
        try {
            q.setParameter("id", idCampeonato);
            return (List<Time>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
        
    }
    
    
    
    
}
