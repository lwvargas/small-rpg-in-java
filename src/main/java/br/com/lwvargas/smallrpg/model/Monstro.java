package br.com.lwvargas.smallrpg.model;

public abstract class Monstro extends Entidade{
    protected Monstro(String nome, int vidaMaxima, int danoBase, int defesa, int vigor) {
        super(nome, vidaMaxima, danoBase, defesa, vigor);
    }
}

