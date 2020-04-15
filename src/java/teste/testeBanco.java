package teste;

import java.sql.SQLException;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.dao.impl.UsuarioDao;
import model.domain.Campeonato;
import model.domain.Time;
import model.domain.Usuario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hugo.alexandre e thiago.otoni
 */
public class testeBanco {

    public static void main(String[] args) throws SQLException {

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("campeonatoWebPU");
//        EntityManager em = emf.createEntityManager();
//
          System.out.println("Conectado");
          System.out.println(LocalDate.now());
//        
//        em.close();
//        emf.close();
          
          Usuario user = new Usuario("Thiago", "totoni@t", "1234", null, null, true);
          new UsuarioDao().inserir(user);

          
    }
}
