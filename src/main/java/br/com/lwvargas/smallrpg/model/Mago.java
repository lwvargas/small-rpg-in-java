package br.com.lwvargas.smallrpg.model;

public abstract class Mago extends Personagem{
    private int manaAtual;
    private int manaMaxima;

    protected Mago(String nome, int vidaMaxima, int danoBase, int defesa, int vigor, int manaMaxima) {
        super(nome, vidaMaxima, danoBase, defesa, vigor);
        this.manaAtual = manaAtual;
        this.manaMaxima = manaMaxima;
    }
    protected int getManaAtual() {
        return manaAtual;
    }
    protected int getManaMaxima() {
        return manaMaxima;
    }
}
