package br.com.lwvargas.smallrpg.ui;

import br.com.lwvargas.smallrpg.model.Cenario;
import br.com.lwvargas.smallrpg.model.Missao;
import br.com.lwvargas.smallrpg.model.MissaoBosque;
import br.com.lwvargas.smallrpg.model.MissaoCaverna;
import br.com.lwvargas.smallrpg.model.MissaoChefeCaverna;
import br.com.lwvargas.smallrpg.model.MissaoMontanha;
import br.com.lwvargas.smallrpg.model.Personagem;
import br.com.lwvargas.smallrpg.service.EscritaArquivo;

import java.util.Scanner;

public class Vila {

    private Missao[] missoes = { new MissaoBosque(), new MissaoCaverna(), new MissaoChefeCaverna(), new MissaoMontanha() };
    private EscritaArquivo escritaArquivo = new EscritaArquivo();

    public Missao[] getMissoes() {
        return missoes;
    }

    public void entrar(Personagem personagem, Scanner keyboard) {
        Cenario.VILA.mostrar();

        System.out.printf("Boas-vindas à vila, %s!%n", personagem.getNome());
        System.out.println("Na praça, um morador acaba de prender um pedido de ajuda no mural.");

        mostrarMenu(personagem, keyboard);
    }

    private void mostrarMenu(Personagem personagem, Scanner keyboard) {
        while(true) {
            System.out.println("1. Selecionar missão");
            System.out.println("2. Visualizar status");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = keyboard.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\n=== Mural de missões ===");
                    
                    for (int i = 0; i < missoes.length; i++) {
                        System.out.println((i + 1) + ". " + missoes[i].getTitulo() + " - " + missoes[i].getDescricao());
                        
                        if (missoes[i].isConcluida()) {
                            System.out.println("   (Concluída)");
                        } else if (!missoes[i].estaLiberada(missoes)) {
                            System.out.println("   (Bloqueada: conclua as outras missões)");
                        } System.out.println();
                    }

                    System.out.println("0. Voltar.");
                    System.out.print("Escolha uma opção: ");

                    int escolhaMissao = keyboard.nextInt();

                    if (escolhaMissao >= 1 && escolhaMissao <= missoes.length) {
                        Missao missao = missoes[escolhaMissao - 1];

                        if (missao.isConcluida()) {
                            System.out.println("Você já concluiu essa missão.");
                        } else if (!missao.estaLiberada(missoes)) {
                            System.out.println("Essa missão ainda não está liberada.");
                        } else {    
                            boolean venceu = missao.iniciar(personagem, keyboard);
                            voltarDaMissao(personagem, missao, venceu);
                        }
                    } else if (escolhaMissao != 0) {
                        System.out.println("Opção inválida.");
                    }
                    break;
                case 2:
                    personagem.mostrarStatus();
                    break;
                case 3:
                    escritaArquivo.salvar(personagem, missoes);
                    System.out.println("Saindo da vila...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void voltarDaMissao(Personagem personagem, Missao missao, boolean venceu) {
        if (venceu) {
            System.out.println("\nMissão concluída! Você retorna à vila.");
            missao.concluir();
            personagem.ganharExperiencia(missao.getXp());
        } else {
            System.out.println("\nVocê foi derrotado... Os moradores te carregam de volta à vila.");
        }

        personagem.restaurarVida();
        escritaArquivo.salvar(personagem, missoes);
        Cenario.VILA.mostrar();
        System.out.println("Você descansa na vila e recupera toda a sua vida.");
    }
}
