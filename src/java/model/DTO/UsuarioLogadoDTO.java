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
public class UsuarioLogadoDTO {
    
    private int id;
    private String nome;
    private boolean ehAdm;

    public UsuarioLogadoDTO(int id, String nome, boolean ehAdm) {
        this.id = id;
        this.nome = nome;
        this.ehAdm = ehAdm;
    }

    public UsuarioLogadoDTO() {
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

    public boolean isEhAdm() {
        return ehAdm;
    }

    public void setEhAdm(boolean ehAdm) {
        this.ehAdm = ehAdm;
    }
}
