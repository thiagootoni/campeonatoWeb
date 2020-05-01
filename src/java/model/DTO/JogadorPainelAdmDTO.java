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
public class JogadorPainelAdmDTO {
    private int id;
    private String nome;
    private String clube;

    public JogadorPainelAdmDTO() {
    }

    public JogadorPainelAdmDTO(int id, String nome, String clube) {
        this.id = id;
        this.nome = nome;
        this.clube = clube;
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

    public String getClube() {
        return clube;
    }

    public void setClube(String clube) {
        this.clube = clube;
    }
}
