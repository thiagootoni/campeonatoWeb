/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import model.domain.EStatusCampeonato;

/**
 *
 * @author Thiago
 */
public class CampeonatoPainelAdmDto {
    private int id;
    private String nome;
    private int qtdParticipantes;
    private EStatusCampeonato status;

    public CampeonatoPainelAdmDto() {
    }

    public CampeonatoPainelAdmDto(int id, String nome, int qtdParticipantes, EStatusCampeonato status) {
        this.id = id;
        this.nome = nome;
        this.qtdParticipantes = qtdParticipantes;
        this.status = status;
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

    public int getQtdParticipantes() {
        return qtdParticipantes;
    }

    public void setQtdParticipantes(int qtdParticipantes) {
        this.qtdParticipantes = qtdParticipantes;
    }

    public EStatusCampeonato getStatus() {
        return status;
    }

    public void setStatus(EStatusCampeonato status) {
        this.status = status;
    }
    
    
}
