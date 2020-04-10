/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.util.ArrayList;

/**
 *
 * @author hugo.alexandre
 */
public class Campeonato {
   private int id;
   private String nome;
   private ArrayList<Usuario> participantes;
   private ArrayList<Jogo> jogos;
   private Jogador artilheiro;
   private Usuario campeao;

    public Campeonato() {
    }

    public Campeonato(String nome, ArrayList<Usuario> participantes, ArrayList<Jogo> jogos, Jogador artilheiro, Usuario campeao) {
        this.nome = nome;
        this.participantes = participantes;
        this.jogos = jogos;
        this.artilheiro = artilheiro;
        this.campeao = campeao;
    }

    public Campeonato(int id, String nome, ArrayList<Usuario> participantes, ArrayList<Jogo> jogos, Jogador artilheiro, Usuario campeao) {
        this.id = id;
        this.nome = nome;
        this.participantes = participantes;
        this.jogos = jogos;
        this.artilheiro = artilheiro;
        this.campeao = campeao;
    }
   
   public String gerarPartidas(){return "";};

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

    public ArrayList<Usuario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Usuario> participantes) {
        this.participantes = participantes;
    }

    public ArrayList<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(ArrayList<Jogo> jogos) {
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
   
   
}
