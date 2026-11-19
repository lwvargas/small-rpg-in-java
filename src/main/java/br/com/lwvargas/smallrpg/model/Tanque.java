package br.com.lwvargas.smallrpg.model;

public abstract class Tanque extends Personagem{
    private boolean passivaAtivaNaRodada;

    protected Tanque(String nome, int vidaMaxima, int danoMinimo, int danoMaximo, int defesa, int vigor) {
        super(nome, vidaMaxima, danoMinimo, danoMaximo, defesa, vigor);
        this.passivaAtivaNaRodada = true;
    }

    protected void ativarPassivaNaRodada(){
        passivaAtivaNaRodada = true;
    }
    protected boolean isPassivaAtivaNaRodada(){
       return passivaAtivaNaRodada;
    }
    public void desativarPassivaNaRodada(){
        passivaAtivaNaRodada = false;
    }

}
