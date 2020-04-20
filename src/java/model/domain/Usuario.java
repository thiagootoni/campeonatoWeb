/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Thiago
 */
@Entity
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false, unique = true)
    private String login;
    
    @Column(nullable = false, unique = true)
    private String senha;
    
    @OneToOne
    @JoinColumn(name ="id_time")
    private Time time;
    
    @ManyToOne
    @JoinColumn(name ="id_campeonato")
    private Campeonato campeonato;
    
    private boolean adm = false;

    public Usuario() {
    }

    public Usuario(String nome, String login, String senha, Time time, Campeonato campeonato, boolean adm) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.time = time;
        this.campeonato = campeonato;
        this.adm = adm;
    }

    public Usuario(int id, String nome, String login, String senha, Time time, Campeonato campeonato, boolean adm) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.time = time;
        this.campeonato = campeonato;
        this.adm = adm;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public boolean isAdm() {
        return adm;
    }

    public void setAdm(boolean adm) {
        this.adm = adm;
    }
    
    public boolean validaSeExiste(String login){
    
       List<Usuario> usuarios = new ArrayList<>();
       
        for (Usuario usuario : usuarios) {
         if (usuario.getLogin().equalsIgnoreCase(login))
             return true;
        }
        
        return false;
    }
    
}
