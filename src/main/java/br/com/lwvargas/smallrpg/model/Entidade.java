package br.com.lwvargas.smallrpg.model;

public abstract class Entidade {
    private String nome;
    private int vidaAtual;
    private int vidaMaxima;
    private int danoBase;
    private int defesa;
    private int vigor;

    protected Entidade(String nome, int vidaMaxima, int danoBase, int defesa, int vigor){
        this.nome = nome;
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima;
        this.danoBase = danoBase;
        this.defesa = defesa;
        this.vigor = vigor;
    }

    public int getVigor() {
        return vigor;
    }
    public int getDefesa() {
        return defesa;
    }
    public int getDanoBase() {
        return danoBase;
    }
    public int getVidaMaxima() {
        return vidaMaxima;
    }
    public int getVidaAtual() {
        return vidaAtual;
    }
    public String getNome() {
        return nome;
    }
}
