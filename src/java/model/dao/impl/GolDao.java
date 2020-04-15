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
import model.domain.Gol;

/**
 *
 * @author Thiago
 */
public class GolDao extends GenericsDao<Integer, Gol> {

    @Override
    public Gol inserir(Gol obj) throws SQLException {
        this.getConexao().getTransaction().begin();
        this.getConexao().persist(obj);
        this.getConexao().getTransaction().commit();
        return obj;
    }

    @Override
    public Gol alterar(Gol obj) throws SQLException {
        this.getConexao().getTransaction().begin();
        this.getConexao().merge(obj);
        this.getConexao().getTransaction().commit();
        return obj;
    }

    @Override
    public void apagar(Integer key) throws SQLException {
        Gol obj = new Gol();
        obj.setId(key);

        this.getConexao().getTransaction().begin();
        this.getConexao().remove(obj);
        this.getConexao().getTransaction().commit();
    }

    @Override
    public Gol buscarUm(Integer key) throws SQLException {
        Query q = this.getConexao().createQuery("SELECT g FROM Gol WHERE g.id = :id");

        try {
            q.setParameter("id", key);
            return (Gol) q.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    @Override
    public List<Gol> buscarTodos() throws SQLException {
        Query q = this.getConexao().createQuery("SELECT c FROM Gol g");

        return q.getResultList();
    }

}
