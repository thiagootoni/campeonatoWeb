/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author hugo.alexandre e thiago.otoni
 */@Entity
public class Gol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    
    @ManyToOne
    @JoinColumn(name ="id_jogador")
    private Jogador autor;
    
    @ManyToOne
    @JoinColumn(name ="id_jogo")
    private Jogo jogo;
    
    @Enumerated(EnumType.STRING)
    private EDiscriminanteGol discriminante;

    public Gol() {
    }

    public EDiscriminanteGol getDiscriminante() {
        return discriminante;
    }

    public void setDiscriminante(EDiscriminanteGol discriminante) {
        this.discriminante = discriminante;
    }

    public Gol(Jogador autor, Jogo jogo, EDiscriminanteGol discriminante) {
        this.autor = autor;
        this.jogo = jogo;
        this.discriminante = discriminante;
    }

    public Gol(int id, Jogador autor, Jogo jogo, EDiscriminanteGol discriminante) {
        this.id = id;
        this.autor = autor;
        this.jogo = jogo;
        this.discriminante = discriminante;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Jogador getAutor() {
        return autor;
    }

    public void setAutor(Jogador autor) {
        this.autor = autor;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
    
    
}
