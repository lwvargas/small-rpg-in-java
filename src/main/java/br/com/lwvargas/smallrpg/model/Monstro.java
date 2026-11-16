package br.com.lwvargas.smallrpg.model;

public abstract class Monstro extends Entidade{
    protected Monstro(String nome, int vidaMaxima, int danoMinimo, int danoMaximo, int defesa, int vigor) {
        super(nome, vidaMaxima, danoMinimo, danoMaximo, defesa, vigor);
    }
}

