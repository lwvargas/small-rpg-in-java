package br.com.lwvargas.smallrpg.model;

public abstract class Tanque extends Personagem{
    protected Tanque(String nome, int vidaMaxima, int danoBase, int defesa, int vigor) {
        super(nome, vidaMaxima, danoBase, defesa, vigor);
    }
}
