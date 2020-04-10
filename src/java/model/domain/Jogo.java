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
public class Jogo {
    private int id;
    private Time desafiante;
    private Time desafiado;
    private int rodada;
    private ArrayList<Jogador> marcadoresDeGolsDesafiantes;
    private ArrayList<Jogador> marcadoresDeGolsDesafiado;
    private int golsDoDesafiante;
    private int golsDoDesafiado;

    public Jogo(Time desafiante, Time desafiado) {
        this.desafiado = desafiado;
        this.desafiante = desafiante;
    }

    public Jogo(int id, Time desafiante, Time desafiado, int rodada, ArrayList<Jogador> marcadoresDeGolsDesafiantes, ArrayList<Jogador> marcadoresDeGolsDesafiado, int golsDoDesafiante, int golsDoDesafiado) {
        this.id = id;
        this.desafiante = desafiante;
        this.desafiado = desafiado;
        this.rodada = rodada;
        this.marcadoresDeGolsDesafiantes = marcadoresDeGolsDesafiantes;
        this.marcadoresDeGolsDesafiado = marcadoresDeGolsDesafiado;
        this.golsDoDesafiante = golsDoDesafiante;
        this.golsDoDesafiado = golsDoDesafiado;
    }

    public Jogo(Time desafiante, Time desafiado, int rodada, ArrayList<Jogador> marcadoresDeGolsDesafiantes, ArrayList<Jogador> marcadoresDeGolsDesafiado, int golsDoDesafiante, int golsDoDesafiado) {
        this.desafiante = desafiante;
        this.desafiado = desafiado;
        this.rodada = rodada;
        this.marcadoresDeGolsDesafiantes = marcadoresDeGolsDesafiantes;
        this.marcadoresDeGolsDesafiado = marcadoresDeGolsDesafiado;
        this.golsDoDesafiante = golsDoDesafiante;
        this.golsDoDesafiado = golsDoDesafiado;
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

    public ArrayList<Jogador> getMarcadoresDeGolsDesafiantes() {
        return marcadoresDeGolsDesafiantes;
    }

    public void setMarcadoresDeGolsDesafiantes(ArrayList<Jogador> marcadoresDeGolsDesafiantes) {
        this.marcadoresDeGolsDesafiantes = marcadoresDeGolsDesafiantes;
    }

    public ArrayList<Jogador> getMarcadoresDeGolsDesafiado() {
        return marcadoresDeGolsDesafiado;
    }

    public void setMarcadoresDeGolsDesafiado(ArrayList<Jogador> marcadoresDeGolsDesafiado) {
        this.marcadoresDeGolsDesafiado = marcadoresDeGolsDesafiado;
    }
    
    public double calculaPontos(){return 0.0;}
    public int atualizaGolsPro(){return 0;}
    public int atualizaGolsContra(){return 0;}
}
