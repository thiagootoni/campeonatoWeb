/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

/**
 *
 * @author Thiago.Otoni e Hugo.Alexandre
 */
public enum EStatusCampeonato {
    EM_ABERTO("Em aberto"), 
    EM_ANDAMENTO("Em andamento"), 
    FINALIZADO("Finalizado");
    
    private String nome;

    private EStatusCampeonato(String nome) {
        this.nome = nome;
    }
    
    public String getNome(){
        return this.nome;
    }
    
}
