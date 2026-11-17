package br.com.lwvargas.smallrpg;

import br.com.lwvargas.smallrpg.model.*;
import br.com.lwvargas.smallrpg.service.Battle;
import br.com.lwvargas.smallrpg.service.resultadoAtaque;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);


        System.out.print("Digite o nome do seu personagem: ");
        String nomePersonagem = keyboard.nextLine();

        Personagem personagem;

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

        Monstro monstro = new Ogro();

        Battle battle = new Battle();
            do {
                int opcaoAtaque;
                do{
                    System.out.println("1. Atacar");
                    opcaoAtaque = keyboard.nextInt();

                    if(opcaoAtaque != 1){
                        System.out.println("Por enquanto voce so pode atacar");
                    }
                }while(opcaoAtaque != 1);
                resultadoAtaque resultadoJogador = battle.attackMonster(personagem, monstro);
                if(resultadoJogador.isAtaqueSuperouDefesa()){
                    System.out.println("=============SUCESSO=============");
                }else if(resultadoJogador.getAcaoDefensiva() == Battle.DefensiveAction.DEFENDER){
                    System.out.println("=============FALHA=============");
                    System.out.println("O inimigo defendeu");
                }else{
                    System.out.println("=============FALHA=============");
                    System.out.println("O inimigo esquivou");
                }

                System.out.println("Dano causado: "+resultadoJogador.getDanoRecebido());
                System.out.println("Vida do inimigo: "+resultadoJogador.getDefensor().getVidaAtual()
                + "/" + resultadoJogador.getDefensor().getVidaMaxima());
                if (monstro.getVidaAtual() > 0) {
                    Battle.DefensiveAction escolhaDoJogador;
                    int opcaoDefesa;

                    do {
                        System.out.println("Como vai se defender?");
                        System.out.println("1. Defender");
                        System.out.println("2. Esquivar");
                        opcaoDefesa = keyboard.nextInt();

                        if (opcaoDefesa != 1 && opcaoDefesa != 2) {
                            System.out.println("Opcao invalida. Digite 1 ou 2");
                        }
                    } while (opcaoDefesa != 1 && opcaoDefesa != 2);

                    if (opcaoDefesa == 1) {
                        escolhaDoJogador = Battle.DefensiveAction.DEFENDER;
                    } else {
                        escolhaDoJogador = Battle.DefensiveAction.ESQUIVAR;
                    }

                   resultadoAtaque resultadoMonstro = battle.attackSolution(monstro, personagem, escolhaDoJogador);
                    if(resultadoMonstro.isAtaqueSuperouDefesa()){
                        System.out.println("=====FALHA=====");
                        System.out.println("Voce tomara dano");
                    }else if(resultadoMonstro.getAcaoDefensiva() == Battle.DefensiveAction.DEFENDER){
                        System.out.println("=============SUCESSO=============");
                        System.out.println("Sua defesa superou o ataque");
                    }else{
                        System.out.println("=============SUCESSO=============");
                        System.out.println("Voce conseguiu se esquivar");
                    }

                    System.out.println("Dano recebido: "+resultadoMonstro.getDanoRecebido());
                    System.out.println("Vida atual: "+resultadoMonstro.getDefensor().getVidaAtual()
                            + "/" + resultadoMonstro.getDefensor().getVidaMaxima());
                }
            }while (personagem.getVidaAtual() > 0 && monstro.getVidaAtual() > 0) ;
    }
}
