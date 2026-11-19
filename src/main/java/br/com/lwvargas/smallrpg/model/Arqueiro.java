package br.com.lwvargas.smallrpg.model;

public abstract class Arqueiro extends Personagem {
    protected Arqueiro(String nome, int vidaMaxima, int danoMinimo, int danoMaximo, int defesa, int vigor) {
        super(nome, vidaMaxima, danoMinimo, danoMaximo, defesa, vigor);
    }
}
