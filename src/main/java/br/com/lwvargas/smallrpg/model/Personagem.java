package br.com.lwvargas.smallrpg.model;

public abstract class Personagem extends Entidade{
    private int nivel;
    private int experiencia;

    protected Personagem(String nome, int vidaMaxima, int danoMinimo, int danoMaximo, int defesa, int vigor) {
        super(nome, vidaMaxima, danoMinimo, danoMaximo, defesa, vigor);
        this.nivel = 1;
        this.experiencia = 0;
    }
    public int getNivel(){
        return nivel;
    }
    public int getExperiencia(){
        return experiencia;
    }
}