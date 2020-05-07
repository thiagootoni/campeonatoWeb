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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
public class Campeonato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "campeonato")
    private List<Usuario> participantes;

    @OneToMany(mappedBy = "campeonato", cascade = CascadeType.REMOVE)
    private List<Jogo> jogos;

    @OneToOne (cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_jogador_artilheiro")
    private Jogador artilheiro;

    @OneToOne (cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_usuario_campeao")
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

    public Campeonato(String nome, int qtdUsuarios) {
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

    private ArrayList<Jogo> criaJogos() {
        ArrayList<Jogo> jogosGerados = new ArrayList<>();

        for (int i = 0; i < this.participantes.size(); i++) {
            for (int j = i + 1; j < this.participantes.size(); j++) {
                Jogo jogo = new Jogo();
                jogo.setDesafiante(this.participantes.get(i).getTime());
                jogo.setDesafiado(this.participantes.get(j).getTime());
                jogosGerados.add(jogo);
            }
        }

        return jogosGerados;
    }

    public ArrayList<Jogo> criaTabela() {
        int[] dados = dadosEstruturaisDoCameponato();
        int jogosPorRodada, qtdRodadas, qtdTotalDeJogoso;

        jogosPorRodada = dados[0];
        qtdRodadas = dados[1];
        qtdTotalDeJogoso = dados[2];

        ArrayList<Jogo> jogosDesordenados = criaJogos();
        ArrayList<Jogo> jogosOrdenados = new ArrayList<>();

        for (int i = 0; i < qtdRodadas; i++) {
            ArrayList<Time> timesNaRodada = new ArrayList<>();
            
            for (int j = 0; j < jogosPorRodada; j++) {
                
                for (Jogo jogoDesordenado : jogosDesordenados) {
                    
                    if (!verificaTimesNalistaDaRodada(timesNaRodada, jogoDesordenado)) {
                        
                        if (!verificaJogoNaLista(jogosOrdenados, jogoDesordenado)) {
                            jogoDesordenado.setRodada((i+1));
                            jogosOrdenados.add(jogoDesordenado);
                            jogosDesordenados.remove(jogoDesordenado);
                            timesNaRodada.add(jogoDesordenado.getDesafiante());
                            timesNaRodada.add(jogoDesordenado.getDesafiado());
                            break;
                        }
                    }

                }

            }
        }        
        return jogosOrdenados;
    }

    private boolean nParDeParticipanetes() {
        return (this.participantes.size() % 2 == 0);
    }

    private int[] dadosEstruturaisDoCameponato() {
        int[] dados = new int[3];
        int qtdParticipantes = this.participantes.size();
        int jogosPorRodada, qtdRodadas, qtdTotalDeJogoso;

        if (nParDeParticipanetes()) {
            jogosPorRodada = (qtdParticipantes / 2);
            qtdRodadas = qtdParticipantes - 1;
            qtdTotalDeJogoso = jogosPorRodada * qtdRodadas;
        } else {
            jogosPorRodada = ((qtdParticipantes - 1) / 2);
            qtdRodadas = qtdParticipantes;
            qtdTotalDeJogoso = jogosPorRodada * qtdRodadas;
        }

        dados[0] = jogosPorRodada;
        dados[1] = qtdRodadas;
        dados[2] = qtdTotalDeJogoso;

        return dados;
    }

    private boolean verificaJogoNaLista(List<Jogo> jogos, Jogo j) {

        for (Jogo jogo : jogos) {
            if (j.equals(jogo)) {
                return true;
            }
        }

        return false;
    }

    private boolean verificaTimesNalistaDaRodada(List<Time> times, Jogo jogo) {
        Time timeA = jogo.getDesafiante();
        Time timeB = jogo.getDesafiado();

        for (Time time : times) {
            if ((time.equals(timeA)) || time.equals(timeB)) {
                return true;
            }
        }

        return false;
    }
    
    public int calculaQtdJogos(){
        int qtdParticipantes = this.participantes.size();
        int jogosPorRodada, qtdRodadas, qtdTotalDeJogoso;

        if (nParDeParticipanetes()) {
            jogosPorRodada = (qtdParticipantes / 2);
            qtdRodadas = qtdParticipantes - 1;
            qtdTotalDeJogoso = jogosPorRodada * qtdRodadas;
        } else {
            jogosPorRodada = ((qtdParticipantes - 1) / 2);
            qtdRodadas = qtdParticipantes;
            qtdTotalDeJogoso = jogosPorRodada * qtdRodadas;
        }
        
        return qtdTotalDeJogoso;
    }

}
