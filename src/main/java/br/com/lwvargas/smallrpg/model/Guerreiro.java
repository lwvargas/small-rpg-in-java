package br.com.lwvargas.smallrpg.model;

public abstract class Guerreiro extends Personagem{
    protected Guerreiro(String nome, int vidaMaxima, int danoBase, int defesa, int vigor) {
        super(nome, vidaMaxima, danoBase, defesa, vigor);
    }
}
