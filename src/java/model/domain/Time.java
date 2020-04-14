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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author hugo.alexandre
 */
@Entity
public class Time implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private String nome;
    @OneToMany(mappedBy = "time")
    private ArrayList<Jogador>jogadores;
    @OneToOne
    @JoinColumn(name ="id_time")
    private Usuario usuario;
    private double pontos;
    private int golsAFavor;
    private int golsContra;

    public Time() {
    }

    public Time(String nome, ArrayList<Jogador> jogadores, Usuario usuario, double pontos, int golsAFavor, int golsContra) {
        this.nome = nome;
        this.jogadores = jogadores;
        this.usuario = usuario;
        this.pontos = pontos;
        this.golsAFavor = golsAFavor;
        this.golsContra = golsContra;
    }

    public Time(int id, String nome, ArrayList<Jogador> jogadores, Usuario usuario, double pontos, int golsAFavor, int golsContra) {
        this.id = id;
        this.nome = nome;
        this.jogadores = jogadores;
        this.usuario = usuario;
        this.pontos = pontos;
        this.golsAFavor = golsAFavor;
        this.golsContra = golsContra;
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

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getPontos() {
        return pontos;
    }

    public void setPontos(double pontos) {
        this.pontos = pontos;
    }

    public int getGolsAFavor() {
        return golsAFavor;
    }

    public void setGolsAFavor(int golsAFavor) {
        this.golsAFavor = golsAFavor;
    }

    public int getGolsContra() {
        return golsContra;
    }

    public void setGolsContra(int golsContra) {
        this.golsContra = golsContra;
    }
    
}