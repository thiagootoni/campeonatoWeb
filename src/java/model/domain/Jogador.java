/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

/**
 *
 * @author hugo.alexandre
 */
public class Jogador {
 private int id;
 private int nome;
 private Time time;
 private int golsFeitos;

    public Jogador() {
    }

    public Jogador(int nome, Time time, int golsFeitos) {
        this.nome = nome;
        this.time = time;
        this.golsFeitos = golsFeitos;
    }

    public Jogador(int id, int nome, Time time, int golsFeitos) {
        this.id = id;
        this.nome = nome;
        this.time = time;
        this.golsFeitos = golsFeitos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNome() {
        return nome;
    }

    public void setNome(int nome) {
        this.nome = nome;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getGolsFeitos() {
        return golsFeitos;
    }

    public void setGolsFeitos(int golsFeitos) {
        this.golsFeitos = golsFeitos;
    }
 
 
}
