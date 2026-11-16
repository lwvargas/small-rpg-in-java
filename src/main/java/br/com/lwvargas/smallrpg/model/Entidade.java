package br.com.lwvargas.smallrpg.model;

import java.util.Random;

public abstract class Entidade {
    private String nome;
    private int vidaAtual;
    private int vidaMaxima;
    private int danoMinimo;
    private int danoMaximo;
    private int defesa;
    private int vigor;

    protected Entidade(String nome, int vidaMaxima, int danoMinimo, int danoMaximo, int defesa, int vigor){
        this.nome = nome;
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima;
        this.danoMinimo = danoMinimo;
        this.danoMaximo = danoMaximo;
        this.defesa = defesa;
        this.vigor = vigor;
    }

    public int getVigor() {
        return vigor;
    }
    public int getDefesa() {
        return defesa;
    }
    public int getDanoMinimo() {
        return danoMinimo;
    }
    public int getDanoMaximo(){
        return danoMaximo;
    }
    public int getVidaMaxima() {
        return vidaMaxima;
    }
    public int getVidaAtual() {
        return vidaAtual;
    }
    public String getNome() {
        return nome;
    }

    public void receberDano(int dano){
        vidaAtual -= dano;
        if(vidaAtual<0){
            vidaAtual=0;
        }
    }
    public boolean estaVivo(){
        return vidaAtual > 0;
    }

    private final Random random = new Random();
    public int rolarAtaque(){
        return random.nextInt(danoMaximo - danoMinimo + 1) + danoMinimo;
    }
    public int rolarDefesa(){
        return random.nextInt(defesa)+1;
    }
    public int rolarEsquiva(){
        return random.nextInt(vigor)+1;
    }

    public int receberAtaque(Entidade atacante, int dano, int defesaRolada){
        receberDano(dano);
        return dano;
    }
    public int receberAtaqueBloqueado(Entidade atacante, int attack, int defesaRolada){
        return 0;
    }
}

