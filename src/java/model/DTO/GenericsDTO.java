/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author hugo.alexandre
 */
public class GenericsDTO {
    
    private EntityManager conexao;
    private EntityManagerFactory emf;

    public GenericsDTO() {

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
    
}
