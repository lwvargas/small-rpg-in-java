package br.com.lwvargas.smallrpg.ui;

import br.com.lwvargas.smallrpg.model.Monstro;
import br.com.lwvargas.smallrpg.model.Entidade;
import br.com.lwvargas.smallrpg.model.Personagem;
import br.com.lwvargas.smallrpg.service.AcaoJogador;
import br.com.lwvargas.smallrpg.service.Battle;
import br.com.lwvargas.smallrpg.service.DefensiveAction;
import br.com.lwvargas.smallrpg.service.resultadoAtaque;

import java.util.Scanner;

public class TelaCombate {

    public boolean lutar(Personagem personagem, Monstro monstro, Scanner keyboard) {
        Battle battle = new Battle();

        do {
            int danoContinuoPersonagem = battle.processarInicioDoTurno(personagem);
            if (danoContinuoPersonagem > 0) {
                System.out.println(personagem.getNome() + " recebeu " + danoContinuoPersonagem + " de dano continuo");
            }
            if (!personagem.estaVivo()) {
                break;
            }
            if (battle.podeAgir(personagem)) {
                AcaoJogador acaoJogador = escolherAcao(keyboard, personagem);
                resultadoAtaque resultadoJogador = battle.resolverAcaoJogador(personagem, monstro, acaoJogador);

                mostrarResultadoAtaque(resultadoJogador, monstro, true);
                if (!personagem.estaVivo()) {
                    break;
                }
            } else {
                System.out.println(personagem.getNome() + " esta atordoado e perdeu o turno");
            }
            if (!monstro.estaVivo()) {
                battle.finalizarTurno(personagem);
                break;
            }
            int danoContinuoMonstro = battle.processarInicioDoTurno(monstro);
            if (danoContinuoMonstro > 0) {
                System.out.println(monstro.getNome() + " recebeu " + danoContinuoMonstro + " de dano continuo");
            }
            if(monstro.getVidaAtual() <= 0){
                battle.finalizarTurno(monstro);
                battle.finalizarTurno(personagem);
                break;
            }
            if (battle.podeAgir(monstro)) {
                DefensiveAction escolhaDoJogador = escolherDefesa(keyboard);
                resultadoAtaque resultadoMonstro = battle.attackSolution(monstro, personagem, escolhaDoJogador);
                mostrarResultadoAtaque(resultadoMonstro, personagem, false);
            } else {
                System.out.println(monstro.getNome() + " esta atordoado e perdeu o turno");
            }
            battle.finalizarTurno(monstro);
            battle.finalizarTurno(personagem);
        } while (personagem.estaVivo() && monstro.estaVivo());

        return personagem.estaVivo();
    }

    private AcaoJogador escolherAcao(Scanner keyboard, Personagem personagem) {
        if (!personagem.podeUsarHabilidade()) {
            System.out.println("Sua habilidade especial nao esta disponivel.");
            System.out.println("1. Ataque normal");
            int opcao;
            do {
                opcao = keyboard.nextInt();
                if (opcao != 1) {
                    System.out.println("Digite 1 para atacar normalmente.");
                }
            } while (opcao != 1);
            return AcaoJogador.ATACAR_NORMAL;
        }

        int opcaoAtaque;
        do {
            System.out.println("\nEscolha sua acao:");
            System.out.println("1. Ataque normal");
            System.out.println("2. " + personagem.getNomeHabilidade() + " - " + personagem.getDescricaoHabilidade());
            opcaoAtaque = keyboard.nextInt();

            if (opcaoAtaque != 1 && opcaoAtaque != 2) {
                System.out.println("Digite 1 ou 2");
            }
        } while (opcaoAtaque != 1 && opcaoAtaque != 2);

        if (opcaoAtaque == 1) {
            return AcaoJogador.ATACAR_NORMAL;
        }
        return AcaoJogador.USAR_HABILIDADE;
    }
    private DefensiveAction escolherDefesa(Scanner keyboard) {
        int opcaoDefesa;

        do {
            System.out.println("\nComo vai se defender?");
            System.out.println("1. Defender");
            System.out.println("2. Esquivar");
            opcaoDefesa = keyboard.nextInt();

            if (opcaoDefesa != 1 && opcaoDefesa != 2) {
                System.out.println("Digite 1 ou 2");
            }
        } while (opcaoDefesa != 1 && opcaoDefesa != 2);

        if (opcaoDefesa == 1) {
            return DefensiveAction.DEFENDER;
        }
        return DefensiveAction.ESQUIVAR;
    }
    private void mostrarResultadoAtaque(resultadoAtaque resultado, Entidade alvo, boolean ataqueDoJogador) {
        if (resultado.isAtaqueSuperouDefesa()) {
            System.out.println("=====ATAQUE BEM-SUCEDIDO=====");
        } else {
            System.out.println("=====ATAQUE FALHOU=====");
            if (resultado.getDanoNoAtacante() > 0) {
                System.out.println("A flecha explosiva atingiu você antes de alcançar o alvo.");
            } else if (resultado.getValorAtaque() <= 0) {
                System.out.println("A habilidade não causou dano ao alvo.");
            } else if (resultado.getAcaoDefensiva() == DefensiveAction.DEFENDER) {
                System.out.println(alvo.getNome() + " defendeu o ataque");
            } else {
                System.out.println(alvo.getNome() + " esquivou do ataque");
            }
        }

        if (resultado.getDanoNoAtacante() > 0) {
            System.out.println("Dano sofrido pelo personagem: " + resultado.getDanoNoAtacante());
            System.out.println("Vida do personagem: " + resultado.getAtacante().getVidaAtual() + "/" + resultado.getAtacante().getVidaMaxima());
        }

        String atacante = ataqueDoJogador ? "Dano causado" : "Dano causado pelo monstro";
        String vidaAlvo = ataqueDoJogador ? "Vida do inimigo" : "Vida do personagem";
        System.out.println(atacante + ": " + resultado.getDanoRecebido());
        System.out.println(vidaAlvo + ": " + alvo.getVidaAtual() + "/" + alvo.getVidaMaxima());
    }
}
