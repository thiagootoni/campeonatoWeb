/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

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
    
    public int retornaIdTime(String nomeTime){
    
     Query q = this.getConexao().createQuery("SELECT t.id FROM Time WHERE t.nome = :nome");
       
       try {
            q.setParameter("nome", nomeTime);
            return (Integer) q.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return -1;
        }
    }
    
}
