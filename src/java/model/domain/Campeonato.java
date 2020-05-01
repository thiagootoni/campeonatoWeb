/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author hugo.alexandre e thiago.otoni
 */@Entity
public class Campeonato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    
    @Column(nullable = false)
    private String nome;
    
    @OneToMany(mappedBy = "campeonato")
    private List<Usuario> participantes;
    
    @OneToMany(mappedBy = "campeonato")
    private List<Jogo> jogos;
    
    @OneToOne
    @JoinColumn(name="id_jogador_artilheiro")
    private Jogador artilheiro;
    
    @OneToOne
    @JoinColumn(name="id_usuario_campeao")
    private Usuario campeao;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EStatusCampeonato status = EStatusCampeonato.EM_ABERTO;
    
    @Column(nullable = false)
    private int qtdUsuarios;

    
    public Campeonato() {
        this.participantes = new ArrayList<>();
        this.jogos = new ArrayList<>();
    }
    
    public Campeonato(String nome, int qtdUsuarios){
        this.nome = nome;
        this.qtdUsuarios = qtdUsuarios;
        this.status = EStatusCampeonato.EM_ABERTO;
        this.participantes = new ArrayList<>();
        this.jogos = new ArrayList<>();
    }

    public Campeonato(int id, String nome, List<Usuario> participantes, List<Jogo> jogos, Jogador artilheiro, Usuario campeao, int qtdUsuarios) {
        this.id = id;
        this.nome = nome;
        this.participantes = participantes;
        this.jogos = jogos;
        this.artilheiro = artilheiro;
        this.campeao = campeao;
        this.qtdUsuarios = qtdUsuarios;
    }

    public Campeonato(String nome, List<Usuario> participantes, List<Jogo> jogos, Jogador artilheiro, Usuario campeao, int qtdUsuarios) {
        this.nome = nome;
        this.participantes = participantes;
        this.jogos = jogos;
        this.artilheiro = artilheiro;
        this.campeao = campeao;
        this.qtdUsuarios = qtdUsuarios;
    }

    public String gerarPartidas() {
        return "";
    }

    ;

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

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    public Jogador getArtilheiro() {
        return artilheiro;
    }

    public void setArtilheiro(Jogador artilheiro) {
        this.artilheiro = artilheiro;
    }

    public Usuario getCampeao() {
        return campeao;
    }

    public void setCampeao(Usuario campeao) {
        this.campeao = campeao;
    }

    public EStatusCampeonato getStatus() {
        return status;
    }

    public void setStatus(EStatusCampeonato status) {
        this.status = status;
    }

    public int getQtdUsuarios() {
        return qtdUsuarios;
    }

    public void setQtdUsuarios(int qtdUsuarios) {
        this.qtdUsuarios = qtdUsuarios;
    }

}
