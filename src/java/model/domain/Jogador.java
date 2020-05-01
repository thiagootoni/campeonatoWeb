/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author hugo.alexandre e thiago.otoni e thiago.otoni
 */
@Entity
public class Jogador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    
    private String nome;
    
    @ManyToOne
    @JoinColumn(name = "id_time")
    private Time time;
    
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Gol> golsFeitos;
    

    public Jogador() {
    }

    public Jogador(int id, String nome, Time time, List<Gol> golsFeitos) {
        this.id = id;
        this.nome = nome;
        this.time = time;
        this.golsFeitos = golsFeitos;
    }

    public Jogador(String nome, Time time, List<Gol> golsFeitos) {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
    
    public List<Gol> getGolsFeitos() {
        return golsFeitos;
    }

    public void setGolsFeitos(List<Gol> golsFeitos) {
        this.golsFeitos = golsFeitos;
    }

}
