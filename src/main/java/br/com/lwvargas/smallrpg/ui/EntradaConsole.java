package br.com.lwvargas.smallrpg.ui;

import java.util.NoSuchElementException;
import java.util.Scanner;

public final class EntradaConsole {

    private EntradaConsole() {
    }

    public static int lerInteiro(Scanner teclado) {
        while (true) {
            if (teclado.hasNextInt()) {
                return teclado.nextInt();
            }
            if (!teclado.hasNext()) {
                throw new NoSuchElementException("A entrada foi encerrada.");
            }
            System.out.println("Entrada inválida. Digite um número.");
            teclado.next();
        }
    }
}
