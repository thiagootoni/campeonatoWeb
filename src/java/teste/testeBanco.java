package teste;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.DTO.TimeDTO;
import model.dao.impl.TimeDao;
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

////        EntityManagerFactory emf = Persistence.createEntityManagerFactory("campeonatoWebPU");
////        EntityManager em = emf.createEntityManager();
////
//        System.out.println("Conectado");
//        System.out.println(LocalDate.now());
////        
////        em.close();
////        emf.close();
//
//        //  Usuario user = new Usuario("Karol", "karol@k", "1234", null, null, true);
//        //  new UsuarioDao().inserir(user);  
//        System.out.println(new TimeDao().retornaTimesDisponiveis().get(0).getNome());
        for (int i = 0; i < 4; i++) {
            UUID uuid = UUID.randomUUID();
            String myRandom = uuid.toString();
            String myRandomSubs = myRandom.substring(0, 20);
            System.out.println(myRandomSubs);
        }

    }
}
