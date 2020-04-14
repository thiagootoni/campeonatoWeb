/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author hugo.alexandre
 */
@Entity

public class Jogo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    @OneToOne
    @JoinColumn(name = "id_time_desafiante")
    private Time desafiante;
    @OneToOne
    @JoinColumn(name = "id_time_desafiado")
    private Time desafiado;
    private int rodada;
    @OneToMany(mappedBy = "jogo")
    private ArrayList<Gol> golsDoJogo;
    @ManyToOne
    @JoinColumn(name = "id_campeonato")
    private Campeonato camp;

    public Jogo(Time desafiante, Time desafiado) {
        this.desafiado = desafiado;
        this.desafiante = desafiante;
    }

    public Jogo(int id, Time desafiante, Time desafiado, int rodada, ArrayList<Gol> golsDoJogo, Campeonato campeonato) {
        this.id = id;
        this.desafiante = desafiante;
        this.desafiado = desafiado;
        this.rodada = rodada;
        this.golsDoJogo = golsDoJogo;
        this.camp = campeonato;
    }

    public Jogo(Time desafiante, Time desafiado, int rodada, ArrayList<Gol> golsDoJogo, Campeonato campeonato) {
        this.desafiante = desafiante;
        this.desafiado = desafiado;
        this.rodada = rodada;
        this.golsDoJogo = golsDoJogo;
        this.camp = campeonato;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getDesafiante() {
        return desafiante;
    }

    public void setDesafiante(Time desafiante) {
        this.desafiante = desafiante;
    }

    public Time getDesafiado() {
        return desafiado;
    }

    public void setDesafiado(Time desafiado) {
        this.desafiado = desafiado;
    }

    public int getRodada() {
        return rodada;
    }

    public void setRodada(int rodada) {
        this.rodada = rodada;
    }

    public ArrayList<Gol> getGolsDoJogo() {
        return golsDoJogo;
    }

    public void setGolsDoJogo(ArrayList<Gol> golsDoJogo) {
        this.golsDoJogo = golsDoJogo;
    }

    

    public Campeonato getCampeonato() {
        return camp;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.camp = campeonato;
    }
    
}
