package br.com.lwvargas.smallrpg.model;

public abstract class Tanque extends Personagem{
    protected Tanque(String nome, int vidaMaxima, int danoMinimo, int danoMaximo, int defesa, int vigor) {
        super(nome, vidaMaxima, danoMinimo, danoMaximo, defesa, vigor);
    }
}
