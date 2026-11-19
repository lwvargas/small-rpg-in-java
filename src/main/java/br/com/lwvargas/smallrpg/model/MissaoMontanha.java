package br.com.lwvargas.smallrpg.model;

import br.com.lwvargas.smallrpg.ui.TelaCombate;
import java.util.Scanner;

public class MissaoMontanha extends Missao {

    public MissaoMontanha() {
        super("Final: O dragão da montanha", "Um dragão atacou a vila e voltou para o seu ninho na montanha.", 3);
    }

    @Override
    public boolean estaLiberada(Missao[] missoes) {
        for (Missao missao : missoes) {
            if (missao != this && !missao.isConcluida()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean iniciar(Personagem personagem, Scanner keyboard) {
        System.out.printf("%s, você sobe a trilha da montanha seguindo o rastro de fogo.%n", personagem.getNome());

        Dragao dragao = new Dragao();
        Cenario.MONTANHA.mostrar(dragao);
        System.out.println("O dragão abre as asas e solta um rugido que ecoa pelo vale!");

        TelaCombate combate = new TelaCombate();
        boolean venceu = combate.lutar(personagem, dragao, keyboard);

        if (venceu) {
            System.out.println("O dragão cai. A vila está salva e você se torna o herói!");
        }
        return venceu;
    }
}
