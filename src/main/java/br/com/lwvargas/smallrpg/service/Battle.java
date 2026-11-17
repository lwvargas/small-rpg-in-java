package br.com.lwvargas.smallrpg.service;

import java.util.Random;

import br.com.lwvargas.smallrpg.model.Entidade;
import br.com.lwvargas.smallrpg.model.Monstro;


public class Battle {

    public enum DefensiveAction {
        DEFENDER,
        ESQUIVAR
    }

    public resultadoAtaque attackSolution(Entidade atacante, Entidade defensor, DefensiveAction defensiveAction) {
        int attack = atacante.rolarAtaque();
        int danoRecebido;
        int resposta;
        int defesaRolada = 0;

        if (defensiveAction == DefensiveAction.DEFENDER) {
            defesaRolada = defensor.rolarDefesa();
            resposta = defesaRolada;
        } else {
            resposta = defensor.rolarEsquiva();
        }
        if (resposta > attack) {
            if (defensiveAction == DefensiveAction.DEFENDER) {
                danoRecebido = defensor.receberAtaqueBloqueado(atacante, attack, defesaRolada);
            } else {
                danoRecebido = 0;
            }
        } else {
            danoRecebido = defensor.receberAtaque(atacante, attack, defesaRolada);
        }
        return new resultadoAtaque(atacante,
                defensor,
                defensiveAction,
                attack,
                resposta,
                danoRecebido,
                attack >= resposta);
    }

    public resultadoAtaque attackMonster(Entidade atacante, Monstro monstro){
        DefensiveAction randomCase = randomMonsterDefensive(monstro);
        return attackSolution(atacante, monstro, randomCase);
    }

    private final Random random = new Random();

    public DefensiveAction randomMonsterDefensive(Monstro monstro) {
        int defesa = monstro.getDefesa();
        int vigor = monstro.getVigor();
        int vantagem = defesa - vigor;
        int diferenca = Math.abs(vantagem);
        int chanceFavorecida = Math.min(50 + diferenca * 5, 90);
        int sorteio = random.nextInt(100) + 1;
        if (vantagem > 0) {
            if (sorteio <= chanceFavorecida) {
                return DefensiveAction.DEFENDER;
            }
            return DefensiveAction.ESQUIVAR;
        }
        if (vantagem < 0) {
            if (sorteio <= chanceFavorecida) {
                return DefensiveAction.ESQUIVAR;
            }
            return DefensiveAction.DEFENDER;
        }
        if (sorteio <= 50) {
            return DefensiveAction.DEFENDER;
        }
        return DefensiveAction.ESQUIVAR;
    }
}