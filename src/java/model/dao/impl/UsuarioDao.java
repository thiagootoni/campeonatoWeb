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
import model.domain.Usuario;

/**
 *
 * @author Thiago
 */
public class UsuarioDao extends GenericsDao<Integer, Usuario>{

    @Override
    public Usuario inserir(Usuario obj) throws SQLException {
        this.getConexao().getTransaction().begin();
        this.getConexao().persist(obj);
        this.getConexao().getTransaction().commit();
        return obj;
    }

    @Override
    public Usuario alterar(Usuario obj) throws SQLException {
        this.getConexao().getTransaction().begin();
        this.getConexao().merge(obj);
        this.getConexao().getTransaction().commit();
        return obj;
    }

    @Override
    public void apagar(Integer key) throws SQLException {
        Usuario u = new Usuario();
        u.setId(key);
        
        this.getConexao().getTransaction().begin();
        this.getConexao().remove(u);
        this.getConexao().getTransaction().commit();
        
    }

    @Override
    public Usuario buscarUm(Integer key) throws SQLException {
        Query q = this.getConexao().createQuery("SELECT u FROM Usuario WHERE u.id = :id");
       
       try {
            q.setParameter("id", key);
            return (Usuario) q.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    @Override
    public List<Usuario> buscarTodos() throws SQLException {
        Query q = this.getConexao().createQuery("SELECT u FROM Usuario u");
        
        return q.getResultList();
    }
    
    
}
