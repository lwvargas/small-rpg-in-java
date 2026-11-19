package br.com.lwvargas.smallrpg.model;

import br.com.lwvargas.smallrpg.ui.TelaCombate;
import java.util.Scanner;

public class MissaoBosque extends Missao {

    public MissaoBosque() {
        super("Fácil: A cobra do bosque", "Uma cobra está levando as galinhas de um morador da vila.", 1);
    }

    @Override
    public boolean iniciar(Personagem personagem, Scanner keyboard) {
        System.out.printf("%s, você segue as pegadas das galinhas até o bosque.%n", personagem.getNome());

        Cobra cobra = new Cobra();
        Cenario.BOSQUE.mostrar(cobra);

        System.out.println("A cobra está enroscada no meio da trilha.");
        System.out.println("Quando você se aproxima, ela ergue a cabeça.");

        TelaCombate combate = new TelaCombate();
        boolean venceu = combate.lutar(personagem, cobra, keyboard);

        if (venceu) {
            System.out.println("O morador agradece: as galinhas estão a salvo!");
        }
        return venceu;
    }
}
