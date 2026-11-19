package br.com.lwvargas.smallrpg.model;

import br.com.lwvargas.smallrpg.ui.TelaCombate;
import java.util.Scanner;

public class MissaoChefeCaverna extends Missao {

    public MissaoChefeCaverna() {
        super("Difícil: O chefe da caverna", "Um ogro enorme tomou o fundo da caverna e ameaça a vila.", 2);
    }

    @Override
    public boolean iniciar(Personagem personagem, Scanner keyboard) {
        System.out.printf("%s, você desce até a parte mais funda da caverna.%n", personagem.getNome());

        Ogro ogro = new Ogro();
        Cenario.CAVERNA.mostrar(ogro);
        System.out.println("O chão treme. O ogro levanta a clava e ruge!");

        TelaCombate combate = new TelaCombate();
        boolean venceu = combate.lutar(personagem, ogro, keyboard);

        if (venceu) {
            System.out.println("O ogro cai. A vila está livre da maior ameaça da caverna.");
        }
        return venceu;
    }
}
