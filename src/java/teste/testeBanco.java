package teste;

import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Thiago
 */
public class testeBanco {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("campeonatoWebPU");
        EntityManager em = emf.createEntityManager();

        System.out.println("Conectado");
        System.out.println(LocalDate.now());
        
        em.close();
        emf.close();
    }
}
