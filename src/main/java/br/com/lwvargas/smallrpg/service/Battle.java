package br.com.lwvargas.smallrpg.service;

import java.util.Random;

import br.com.lwvargas.smallrpg.model.Entidade;
import br.com.lwvargas.smallrpg.model.Monstro;


public class Battle {

    public enum DefensiveAction {
        DEFENDER,
        ESQUIVAR
    }

    public int attackSolution(Entidade atacante, Entidade defensor, DefensiveAction defensiveAction) {
        int attack = atacante.rolarAtaque();
        int resposta;
        int defesaRolada = 0;

        if (defensiveAction == DefensiveAction.DEFENDER) {
            defesaRolada = defensor.rolarDefesa();
            resposta = defesaRolada;
        } else {
            resposta = defensor.rolarEsquiva();
        }
        if(resposta>attack) {
            if (defensiveAction == DefensiveAction.DEFENDER) {
                return defensor.receberAtaqueBloqueado(atacante, attack, defesaRolada);
            }
            return 0;
        }
        return defensor.receberAtaque(atacante, attack, defesaRolada);
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

    public int attackMonster(Entidade atacante, Monstro monstro) {
        DefensiveAction randomCase = randomMonsterDefensive(monstro);
        return attackSolution(atacante, monstro, randomCase);
    }
}