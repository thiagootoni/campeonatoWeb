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
import model.DTO.CampeonatoPainelAdmDto;
import model.dao.GenericsDao;
import model.domain.Campeonato;
import model.domain.EStatusCampeonato;

/**
 *
 * @author Thiago
 */
public class CampeonatoDao extends GenericsDao<Integer, Campeonato>{

    @Override
    public Campeonato inserir(Campeonato obj) throws SQLException {
        this.getConexao().getTransaction().begin();
        this.getConexao().persist(obj);
        this.getConexao().getTransaction().commit();
        return obj;
    }

    @Override
    public Campeonato alterar(Campeonato obj) throws SQLException {
        this.getConexao().getTransaction().begin();
        this.getConexao().merge(obj);
        this.getConexao().getTransaction().commit();
        return obj;
    }

    @Override
    public void apagar(Integer key) throws SQLException {
        Campeonato c = new Campeonato();
        c.setId(key);
        
        this.getConexao().getTransaction().begin();
        this.getConexao().remove(c);
        this.getConexao().getTransaction().commit();
    }

    @Override
    public Campeonato buscarUm(Integer key) throws SQLException {
       Query q = this.getConexao().createQuery("SELECT c FROM Campeonato c WHERE c.id = :id");
       
       try {
            q.setParameter("id", key);
            return (Campeonato) q.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    @Override
    public List<Campeonato> buscarTodos() throws SQLException {
        Query q = this.getConexao().createQuery("SELECT c FROM Campeonato c");
        
        return q.getResultList();
    }
    
    public List<CampeonatoPainelAdmDto> buscarTodosCampPainelDto() throws SQLException {
        List<Campeonato> campeonatos = this.buscarTodos();
        List<CampeonatoPainelAdmDto> campsDto =  new ArrayList<>();
        
        for (Campeonato campeonato : campeonatos) {
            CampeonatoPainelAdmDto campDto = new CampeonatoPainelAdmDto();
            campDto.setId(campeonato.getId());
            campDto.setNome(campeonato.getNome());
            campDto.setQtdParticipantes(campeonato.getParticipantes().size());
            campDto.setQtdVagas(campeonato.getQtdUsuarios());
            campDto.setStatus(campeonato.getStatus());
            
            campsDto.add(campDto);
        }
        
        return campsDto;
    }
    
    public Campeonato getCampeonatoAbertoOuEmAndamento() throws SQLException {
        List<Campeonato> campeonatos = this.buscarTodos();
        
        for (Campeonato campeonato : campeonatos) {
            if (!campeonato.getStatus().equals(EStatusCampeonato.FINALIZADO)) {
                return campeonato;
            }
        }        
        return new Campeonato();        
    }
    
    public Campeonato getCampeonatoAberto() throws SQLException {
        List<Campeonato> campeonatos = this.buscarTodos();
        
        for (Campeonato campeonato : campeonatos) {
            if (campeonato.getStatus().equals(EStatusCampeonato.EM_ABERTO)) {
                return campeonato;
            }
        }        
        return new Campeonato();        
    }
    
    public boolean checkCampeonatoAbertoOuEmAndamento() throws SQLException {
        List<Campeonato> campeonatos = this.buscarTodos();
        
        for (Campeonato campeonato : campeonatos) {
            if (!campeonato.getStatus().equals(EStatusCampeonato.FINALIZADO)) {
                return true;
            }
        }        
        return false;        
    }
    
    
    
    public Campeonato buscaCampeonatoEmAbertoOuEmAndamento() throws SQLException {
        List<Campeonato> campeonatos = this.buscarTodos();
        
        for (Campeonato campeonato : campeonatos) {
            if (!campeonato.getStatus().equals(EStatusCampeonato.FINALIZADO)) {
                return campeonato;
            }
        }        
        return null;        
    }
    
}