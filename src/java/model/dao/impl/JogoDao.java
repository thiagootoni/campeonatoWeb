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
import model.domain.Jogo;

/**
 *
 * @author Thiago e Hugo
 */
public class JogoDao extends GenericsDao<Integer, Jogo> {

    @Override
    public Jogo inserir(Jogo obj) throws SQLException {
        this.getConexao().getTransaction().begin();
        this.getConexao().persist(obj);
        this.getConexao().getTransaction().commit();
        return obj;
    }

    @Override
    public Jogo alterar(Jogo obj) throws SQLException {
        this.getConexao().getTransaction().begin();
        this.getConexao().persist(obj);
        this.getConexao().getTransaction().commit();
        return obj;
    }

    @Override
    public void apagar(Integer key) throws SQLException {
        Jogo j = new Jogo();
        j.setId(key);
        
        this.getConexao().getTransaction().begin();
        this.getConexao().persist(j);
        this.getConexao().getTransaction().commit();
    }

    @Override
    public Jogo buscarUm(Integer key) throws SQLException {
        Query q = this.getConexao().createQuery("SELECT j FROM Jogo WHERE j.id = :id");
       
       try {
            q.setParameter("id", key);
            return (Jogo) q.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    @Override
    public List<Jogo> buscarTodos() throws SQLException {
        Query q = this.getConexao().createQuery("SELECT j FROM Jogo j");
        
        return q.getResultList();}

}
