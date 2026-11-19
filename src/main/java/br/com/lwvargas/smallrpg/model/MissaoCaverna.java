package br.com.lwvargas.smallrpg.model;

import br.com.lwvargas.smallrpg.ui.TelaCombate;
import java.util.Scanner;

public class MissaoCaverna extends Missao {

    public MissaoCaverna() {
        super("Média: Monstros da caverna", "Morcegos e goblins saem da caverna à noite e assustam a vila.", 7);
    }

    @Override
    public boolean iniciar(Personagem personagem, Scanner keyboard) {
        System.out.printf("%s, você acende uma tocha e entra na caverna.%n", personagem.getNome());

        TelaCombate combate = new TelaCombate();

        Morcego morcego = new Morcego();
        Cenario.CAVERNA.mostrar(morcego);
        System.out.println("Um morcego desce do teto e voa na sua direção!");

        if (!combate.lutar(personagem, morcego, keyboard)) {
            return false;
        }

        Goblin goblin = new Goblin();
        Cenario.CAVERNA.mostrar(goblin);
        System.out.println("Mais adiante, um goblin bloqueia o caminho.");

        boolean venceu = combate.lutar(personagem, goblin, keyboard);

        if (venceu) {
            System.out.println("A caverna está livre. Os moradores podem dormir em paz.");
        }
        return venceu;
    }
}
