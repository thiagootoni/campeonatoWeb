/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import model.dao.GenericsDao;
import model.domain.Time;

/**
 *
 * @author Thiago
 */
public class TimeDao extends GenericsDao<Integer, Time>{

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
    
}
