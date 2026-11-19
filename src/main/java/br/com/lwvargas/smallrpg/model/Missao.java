package br.com.lwvargas.smallrpg.model;

import java.util.Scanner;

public abstract class Missao {
    private final String titulo;
    private final String descricao;
    private boolean concluida;
    private final int xp;

    protected Missao(String titulo, String descricao, int xp) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = false;
        this.xp = xp;
    }

    public String getTitulo() {
        return titulo;
    }
    public String getDescricao() {
        return descricao;
    }

    public int getXp() {
        return xp;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void concluir() {
        concluida = true;
    }

    public boolean estaLiberada(Missao[] missoes) {
        return true;
    }

    public abstract boolean iniciar(Personagem personagem, Scanner keyboard);
}
