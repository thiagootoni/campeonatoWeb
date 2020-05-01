/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

/**
 *
 * @author Thiago
 */
public class TimePainelAdmDTO {
    
    private int id;
    private String nome;
    private int qtdJogadores;

    public TimePainelAdmDTO() {
    }

    public TimePainelAdmDTO(int id, String nome, int qtdJogadores) {
        this.id = id;
        this.nome = nome;
        this.qtdJogadores = qtdJogadores;
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

    public int getQtdJogadores() {
        return qtdJogadores;
    }

    public void setQtdJogadores(int qtdJogadores) {
        this.qtdJogadores = qtdJogadores;
    }
    
    
}
