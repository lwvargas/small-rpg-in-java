package br.com.lwvargas.smallrpg.service;

import br.com.lwvargas.smallrpg.model.Missao;
import br.com.lwvargas.smallrpg.model.Mago;
import br.com.lwvargas.smallrpg.model.Personagem;
import br.com.lwvargas.smallrpg.model.ArqueiroFlechaExplosiva;
import br.com.lwvargas.smallrpg.model.ArqueiroFlechasVenenosas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EscritaArquivo {
    private static final String ARQUIVO = "save.csv";

    public void salvar(Personagem personagem, Missao[] missoes) {
        try {
            FileWriter writer = new FileWriter(ARQUIVO);
            PrintWriter saida = new PrintWriter(writer);

            int manaAtual = personagem instanceof Mago mago ? mago.getManaAtual() : -1;
            int flechasEspeciais = -1;
            if (personagem instanceof ArqueiroFlechaExplosiva arqueiro) {
                flechasEspeciais = arqueiro.getFlechasEspeciais();
            } else if (personagem instanceof ArqueiroFlechasVenenosas arqueiro) {
                flechasEspeciais = arqueiro.getFlechasEspeciais();
            }

            saida.println(personagem.getClass().getSimpleName() + ","
                    + personagem.getNome() + ","
                    + personagem.getNivel() + ","
                    + personagem.getExperiencia() + ","
                    + manaAtual + ","
                    + flechasEspeciais);

            for (Missao missao : missoes) {
                saida.println(missao.getTitulo() + "," + missao.isConcluida());
            }

            saida.close();
            writer.close();
            System.out.println("Jogo salvo.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o jogo.");
        }
    }
}
