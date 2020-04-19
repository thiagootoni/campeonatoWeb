/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Thiago.Otoni e Hugo.Alexandre
 */
public abstract class GenericsDao<K, C> {
    
    private EntityManager conexao;
    private EntityManagerFactory emf;

    public GenericsDao() {

        emf = Persistence.createEntityManagerFactory("campeonatoWebPU");
        conexao = emf.createEntityManager();
    }

    public void close(){
       conexao.close();
       emf.close();
    }
    
    public EntityManager getConexao() {
        return conexao;
    }
    
    public abstract C inserir(C obj) throws SQLException;

    public abstract C alterar(C obj) throws SQLException;

    public abstract void apagar(K key) throws SQLException;

    public abstract C buscarUm(K key) throws SQLException;

    public abstract List<C> buscarTodos() throws SQLException;
}
