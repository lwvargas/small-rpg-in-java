package br.com.lwvargas.smallrpg.model;

public abstract class Arqueiro extends Personagem {
    protected Arqueiro(String nome, int vidaMaxima, int danoBase, int defesa, int vigor) {
        super(nome, vidaMaxima, danoBase, defesa, vigor);
    }
}
