package br.com.lwvargas.smallrpg.service;

import java.util.Random;

import br.com.lwvargas.smallrpg.model.Entidade;
import br.com.lwvargas.smallrpg.model.Mago;
import br.com.lwvargas.smallrpg.model.Monstro;
import br.com.lwvargas.smallrpg.model.Personagem;
import br.com.lwvargas.smallrpg.model.Tanque;
import br.com.lwvargas.smallrpg.model.EfeitoStatus;
import br.com.lwvargas.smallrpg.model.TipoEfeito;


public class Battle {
    private final Random random = new Random();

    public resultadoAtaque attackSolution(Entidade atacante, Entidade defensor, DefensiveAction defensiveAction) {
        int ataque = atacante.rolarAtaque();

        return attackSolution(atacante, defensor, defensiveAction, ataque);
    }

    public resultadoAtaque attackSolution(Entidade atacante, Entidade defensor, DefensiveAction defensiveAction, int ataque){
        return attackSolution(atacante, defensor, defensiveAction, ataque, 0);
    }

    private resultadoAtaque attackSolution(Entidade atacante, Entidade defensor, DefensiveAction defensiveAction, int ataque, int danoNoAtacante){
        if (ataque <= 0) {
            return new resultadoAtaque(atacante, defensor, defensiveAction, ataque, 0, 0, false, danoNoAtacante);
        }
        EfeitoStatus reducaoDanoCausado = atacante.buscarEfeito(TipoEfeito.REDUCAO_DANO_CAUSADO);
        if (reducaoDanoCausado != null) {
            ataque = Math.max(1, ataque - reducaoDanoCausado.getIntensidade());
        }
        int resposta;
        int defesaRolada = 0;

        if(defensor.possuiEfeito(TipoEfeito.SEM_DEFESA_PROXIMO_ATAQUE) || defensor.possuiEfeito(TipoEfeito.ATORDOAMENTO)){
            resposta = 0;
        }else if(defensiveAction == DefensiveAction.DEFENDER){
            defesaRolada = defensor.rolarDefesa();
            resposta = defesaRolada;
        }else{
            resposta = defensor.rolarEsquiva();
        }
        int danoRecebido;

        if(resposta>ataque){
            if(defensiveAction == DefensiveAction.DEFENDER){
                danoRecebido = defensor.receberAtaqueBloqueado(atacante, ataque, defesaRolada);
            }else{
                danoRecebido = 0;
            }
        }else{
            danoRecebido = defensor.receberAtaque(atacante, ataque, defesaRolada);
        }
        return new resultadoAtaque(
                atacante,
                defensor,
                defensiveAction,
                ataque,
                resposta,
                danoRecebido,
                ataque >= resposta,
                danoNoAtacante
        );
    }

    public resultadoAtaque resolverAcaoJogador(
            Personagem jogador,
            Monstro monstro,
            AcaoJogador acao
    ){
        if(acao == null){
            throw new IllegalArgumentException("A acao nao pode ser nula");
        }
        if (jogador instanceof Tanque tanque){
            tanque.desativarPassivaNaRodada();
        }
        int vidaAntesDaHabilidade = jogador.getVidaAtual();
        int ataque;

        if(acao == AcaoJogador.ATACAR_NORMAL){
            ataque = jogador.rolarAtaque();

            if(jogador instanceof Mago){
                ataque = Math.max(1, ataque * 20/100);
            }
        }else{
            ataque = jogador.usarHabilidade(monstro);
        }
        int danoNoAtacante = vidaAntesDaHabilidade - jogador.getVidaAtual();
        DefensiveAction defesaMonstro = randomMonsterDefensive(monstro);

        resultadoAtaque resultado = attackSolution(
                jogador,
                monstro,
                defesaMonstro,
                ataque,
                danoNoAtacante
        );
        if (acao == AcaoJogador.USAR_HABILIDADE && ataque > 0 && resultado.isAtaqueSuperouDefesa()) {
            jogador.aplicarEfeitoHabilidade(monstro);
        }
        return resultado;
    }

    public boolean podeAgir(Entidade entidade){
        return !entidade.possuiEfeito(TipoEfeito.ATORDOAMENTO);
    }
    public int processarInicioDoTurno(Entidade entidade){
        return entidade.processarDanoContinuo();
    }
    public void finalizarTurno(Entidade entidade){
        entidade.avancarRodadaEfeitos();
    }
    public DefensiveAction randomMonsterDefensive(Monstro monstro){
        int defesa = monstro.getDefesa();
        int vigor = monstro.getVigor();
        int vantagem = defesa - vigor;
        int diferenca = Math.abs(vantagem);
        int chanceFavorecida = Math.min(50+diferenca*5,90);
        int sorteio = random.nextInt(100)+1;

        if(vantagem>0){
            if(sorteio<=chanceFavorecida){
                return DefensiveAction.DEFENDER;
            }
            return DefensiveAction.ESQUIVAR;
        }
        if(vantagem<0){
            if(sorteio<=chanceFavorecida){
                return DefensiveAction.ESQUIVAR;
            }
            return DefensiveAction.DEFENDER;
        }
        if(sorteio <=50){
            return DefensiveAction.DEFENDER;
        }
        return DefensiveAction.ESQUIVAR;
    }
}
