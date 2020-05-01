/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import model.DTO.JogadorPainelAdmDTO;
import model.dao.GenericsDao;
import model.domain.Jogador;

/**
 *
 * @author Thiago
 */
public class JogadorDao extends GenericsDao<Integer, Jogador>{

    @Override
    public Jogador inserir(Jogador obj) throws SQLException {
        this.getConexao().getTransaction().begin();
        this.getConexao().persist(obj);
        this.getConexao().getTransaction().commit();
        return obj;
    }

    @Override
    public Jogador alterar(Jogador obj) throws SQLException {
        this.getConexao().getTransaction().begin();
        this.getConexao().merge(obj);
        this.getConexao().getTransaction().commit();
        return obj;}

    @Override
    public void apagar(Integer key) throws SQLException {
        Jogador j = this.buscarUm(key);
        
        this.getConexao().getTransaction().begin();
        this.getConexao().remove(j);
        this.getConexao().getTransaction().commit();
        
    }

    @Override
    public Jogador buscarUm(Integer key) throws SQLException {
        Query q = this.getConexao().createQuery("SELECT j FROM Jogador j WHERE j.id = :id");
       
       try {
            q.setParameter("id", key);
            return (Jogador) q.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    @Override
    public List<Jogador> buscarTodos() throws SQLException {
        Query q = this.getConexao().createQuery("SELECT j FROM Jogador j");
        
        return q.getResultList();
    }
    
    public List<JogadorPainelAdmDTO> buscarTodosPainelAdmDto() throws SQLException{
        List<Jogador> jogadores = this.buscarTodos();
        List<JogadorPainelAdmDTO> jogadoresDto = new ArrayList<>();
        
        for (Jogador jogador : jogadores) {
            JogadorPainelAdmDTO jDto = new JogadorPainelAdmDTO();
            jDto.setId(jogador.getId());
            jDto.setNome(jogador.getNome());
            jDto.setClube(jogador.getTime().getNome());
            
            jogadoresDto.add(jDto);
        }
        
        return jogadoresDto;
    }
    
    
}
