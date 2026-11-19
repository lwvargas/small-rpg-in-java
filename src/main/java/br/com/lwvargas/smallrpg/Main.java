package br.com.lwvargas.smallrpg;

import br.com.lwvargas.smallrpg.model.*;
import br.com.lwvargas.smallrpg.service.LeituraArquivo;
import br.com.lwvargas.smallrpg.ui.Vila;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        Vila vila = new Vila();
        LeituraArquivo leituraArquivo = new LeituraArquivo();

        System.out.println("===== Bem vindo ao Java RPG =====");
        System.out.println("1. Novo jogo");
        System.out.println("2. Carregar jogo");
        System.out.println("3. Sair");

        String opcao;
        do {
            System.out.print("Escolha uma opção: ");
            opcao = keyboard.nextLine();

            switch (opcao) {
                case "1":
                    System.out.println("Novo jogo selecionado");
                    break;
                case "2":
                    Personagem personagemSalvo = leituraArquivo.carregar(vila.getMissoes());
                    if (personagemSalvo != null) {
                        vila.entrar(personagemSalvo, keyboard);
                        return;
                    }
                    System.out.println("Nenhum jogo salvo encontrado.");
                    break;
                case "3":
                    System.out.println("Saindo do jogo...");
                    System.exit(0);
                default:
                    System.out.println("Opcao invalida. Digite 1, 2 ou 3.");
            }
        } while (!opcao.equals("1"));

        Personagem personagem;
        System.out.print("Digite o nome do seu personagem: ");
        String nomePersonagem = keyboard.nextLine();

        System.out.println("========== Escolha uma das opcoes de classes ==========");
        System.out.println("                1. Guerreiro");
        System.out.println("                2. Tanque");
        System.out.println("                3. Mago");
        System.out.println("                4. Arqueiro");
        int opcaoClasse = keyboard.nextInt();

        switch (opcaoClasse){
            case 1:
                System.out.println("========== Escolha uma das opcoes de Guerreiro ==========");
                System.out.println("                1. Barbaro");
                System.out.println("                2. Guerreiro de duas espadas");
                System.out.println("                3. Guerreiro de espada e escudo");
                int opcaoSubclasseG = keyboard.nextInt();
                switch (opcaoSubclasseG) {
                    case 1:
                        personagem = new GuerreiroBarbaro(nomePersonagem);
                        break;
                    case 2:
                        personagem = new GuerreiroDualSword(nomePersonagem);
                        break;
                    case 3:
                        personagem = new GuerreiroEscudoEspada(nomePersonagem);
                        break;
                    default:
                        System.out.println("Opcao invalida");
                        return;
                }
                break;
            case 2:
                System.out.println("========== Escolha uma das opcoes de Tanque ==========");
                System.out.println("                1. Tanque Refletor de dano");
                System.out.println("                2. Tanque Redutor de dano");
                int opcaoSubclasseT = keyboard.nextInt();
                switch (opcaoSubclasseT) {
                    case 1:
                        personagem = new TanqueRefletor(nomePersonagem);
                        break;
                    case 2:
                        personagem = new TanqueRedutor(nomePersonagem);
                        break;
                    default:
                        System.out.println("Opcao invalida");
                        return;
                }
                break;
            case 3:
                System.out.println("========== Escolha uma das opcoes de Mago ==========");
                System.out.println("                1. Mago de fogo");
                System.out.println("                2. Mago de gelo");
                System.out.println("                3. Mago Eletrico");
                int opcaoSubclasseM = keyboard.nextInt();
                switch(opcaoSubclasseM){
                    case 1:
                        personagem = new MagoFogo(nomePersonagem);
                        break;
                    case 2:
                        personagem = new MagoGelo(nomePersonagem);
                        break;
                    case 3:
                        personagem = new MagoEletrico(nomePersonagem);
                        break;
                    default:
                        System.out.println("Opcao invalida");
                        return;
                }
                break;
            case 4:
                System.out.println("========== Escolha uma das opcoes de Arqueiro ==========");
                System.out.println("                1. Arqueiro com flechas explosivas");
                System.out.println("                2. Arqueiro com flechas venenosas");
                System.out.println("                3. Arqueiro com flechas Multiplas");
                int opcaoSubclasseA = keyboard.nextInt();
                switch (opcaoSubclasseA){
                    case 1:
                        personagem = new ArqueiroFlechaExplosiva(nomePersonagem);
                        break;
                    case 2:
                        personagem = new ArqueiroFlechasVenenosas(nomePersonagem);
                        break;
                    case 3:
                        personagem = new ArqueiroFlechasMultiplas(nomePersonagem);
                        break;
                    default:
                        System.out.println("Opcao invalida");
                        return;
                }
                break;
            default:
                System.out.println("Opcao invalida");
                return;
        }

        vila.entrar(personagem, keyboard);

    }
}
