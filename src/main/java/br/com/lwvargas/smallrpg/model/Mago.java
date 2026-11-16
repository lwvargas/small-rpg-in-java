package br.com.lwvargas.smallrpg.model;

public abstract class Mago extends Personagem{
    private int manaAtual;
    private int manaMaxima;

    protected Mago(String nome, int vidaMaxima, int danoMinimo, int danoMaximo, int defesa, int vigor, int manaMaxima) {
        super(nome, vidaMaxima, danoMinimo, danoMaximo, defesa, vigor);
        this.manaAtual = manaMaxima;
        this.manaMaxima = manaMaxima;
    }
    public int getManaAtual() {
        return manaAtual;
    }
    public int getManaMaxima() {
        return manaMaxima;
    }
}
