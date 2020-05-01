/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import com.sun.faces.config.manager.tasks.ParseConfigResourceToDOMTask;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import model.dao.GenericsDao;
import model.domain.Time;

/**
 *
 * @author hugo.alexandre
 */
public class TimeDTO extends GenericsDTO{
  
  private int id;
  private String nome;

    public TimeDTO() {
    }

    public TimeDTO(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
  
  
    
    /*public int retornaIdTime(String nomeTime){
    
     Query q = this.getConexao().createQuery("SELECT t.id FROM Time t WHERE t.nome = :nome");
       
       try {
            q.setParameter("nome", Parse nomeTime);
            return (Integer) q.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return -1;
        }
    }
    */
}
