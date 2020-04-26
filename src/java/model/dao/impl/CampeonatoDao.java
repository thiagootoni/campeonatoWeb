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
import model.domain.Campeonato;

/**
 *
 * @author Thiago
 */
public class CampeonatoDao extends GenericsDao<Integer, Campeonato>{

    @Override
    public Campeonato inserir(Campeonato obj) throws SQLException {
        this.getConexao().getTransaction().begin();
        this.getConexao().persist(obj);
        this.getConexao().getTransaction().commit();
        return obj;
    }

    @Override
    public Campeonato alterar(Campeonato obj) throws SQLException {
        this.getConexao().getTransaction().begin();
        this.getConexao().merge(obj);
        this.getConexao().getTransaction().commit();
        return obj;
    }

    @Override
    public void apagar(Integer key) throws SQLException {
        Campeonato c = new Campeonato();
        c.setId(key);
        
        this.getConexao().getTransaction().begin();
        this.getConexao().remove(c);
        this.getConexao().getTransaction().commit();
    }

    @Override
    public Campeonato buscarUm(Integer key) throws SQLException {
       Query q = this.getConexao().createQuery("SELECT c FROM Campeonato c WHERE c.id = :id");
       
       try {
            q.setParameter("id", key);
            return (Campeonato) q.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    @Override
    public List<Campeonato> buscarTodos() throws SQLException {
        Query q = this.getConexao().createQuery("SELECT c FROM Campeonato c");
        
        return q.getResultList();
    }
    

}