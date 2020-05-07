/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
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
 * @author hugo.alexandre e thiago.otoni
 */
@Entity
public class Time implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "time", cascade = CascadeType.REMOVE)
    private List<Jogador> jogadores;

    @OneToOne(mappedBy = "time")
    private Usuario usuario;

    private int numJogos;

    private int vitorias;

    private int empates;

    private int derrotas;

    private double pontos;

    private int golsAFavor;

    private int golsContra;

    public Time() {
        this.jogadores = new ArrayList<>();
    }

    public Time(String nome, List<Jogador> jogadores, Usuario usuario, int numJogos, int vitorias, int empates, int derrotas, double pontos, int golsAFavor, int golsContra) {
        this.nome = nome;
        this.jogadores = jogadores;
        this.usuario = usuario;
        this.numJogos = numJogos;
        this.vitorias = vitorias;
        this.empates = empates;
        this.derrotas = derrotas;
        this.pontos = pontos;
        this.golsAFavor = golsAFavor;
        this.golsContra = golsContra;
    }

    public Time(int id, String nome, List<Jogador> jogadores, Usuario usuario, int numJogos, int vitorias, int empates, int derrotas, double pontos, int golsAFavor, int golsContra) {
        this.id = id;
        this.nome = nome;
        this.jogadores = jogadores;
        this.usuario = usuario;
        this.numJogos = numJogos;
        this.vitorias = vitorias;
        this.empates = empates;
        this.derrotas = derrotas;
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

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
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

    public int getNumJogos() {
        return numJogos;
    }

    public void setNumJogos(int numJogos) {
        this.numJogos = numJogos;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }
    
    

    public boolean validaSeExiste(String nome) {

        List<Time> times = new ArrayList<>();

        for (Time time : times) {
            if (time.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }

        return false;
    }

    public boolean equals(Time time) {
        return this.nome.equalsIgnoreCase(time.getNome());
    }
    
    private void ganhou(){
        this.numJogos++;
        this.vitorias++;
    }
    
    private void empatou(){
        this.numJogos++;
        this.empates++;
    }
    
    private void perdeu(){
        this.numJogos++;
        this.derrotas++;
    }
    
    private void calculaPontuacaoDosGols(int golsFeitos, int golsSofridos){
        this.pontos += ((golsFeitos * 1) - (golsSofridos * 0.5));
    }
    
    private void computaGolsProGolsContra(int golsFeitos, int golsSofridos){
        this.golsAFavor += golsFeitos;
        this.golsContra += golsSofridos;
    }
    
    public void calculaPontuacaoEStats(int golsFeitos, int golsSofridos){
        this.computaGolsProGolsContra(golsFeitos, golsSofridos);
        
        if (golsFeitos == golsSofridos) {
            this.empatou();
            this.calculaPontuacaoDosGols(golsFeitos, golsSofridos);
        }else if (golsFeitos > golsSofridos) {
            this.ganhou();
            this.calculaPontuacaoDosGols(golsFeitos, golsSofridos);
            this.pontos += 3.0;
        }else{
            this.perdeu();
            this.calculaPontuacaoDosGols(golsFeitos, golsSofridos);
        }
    }
    
    public void zerarStats(){
        this.setDerrotas(0);
        this.setEmpates(0);
        this.setVitorias(0);
        this.setNumJogos(0);
        this.setGolsAFavor(0);
        this.setGolsContra(0);
        this.setPontos(0);
    }

}
