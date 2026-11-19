package br.com.lwvargas.smallrpg.service;

import br.com.lwvargas.smallrpg.model.*;
import br.com.lwvargas.smallrpg.service.Battle;

import java.util.ResourceBundle;

public class resultadoAtaque {
    private Entidade atacante;
    private Entidade defensor;
    private DefensiveAction acaoDefensiva;
    private int valorAtaque;
    private int valorResposta;
    private int danoRecebido;
    private boolean ataqueSuperouDefesa;
    private int danoNoAtacante;


    public resultadoAtaque(Entidade atacante,
                           Entidade defensor,
                           DefensiveAction acaoDefensiva,
                           int valorAtaque,
                           int valorResposta,
                           int danoRecebido,
                           boolean ataqueSuperouDefesa,
                           int danoNoAtacante) {
        this.atacante = atacante;
        this.defensor = defensor;
        this.acaoDefensiva = acaoDefensiva;
        this.valorAtaque = valorAtaque;
        this.valorResposta = valorResposta;
        this.danoRecebido = danoRecebido;
        this.ataqueSuperouDefesa = ataqueSuperouDefesa;
        this.danoNoAtacante = danoNoAtacante;
    }


    public Entidade getAtacante() {
        return atacante;
    }
    public Entidade getDefensor() {
        return defensor;
    }
    public DefensiveAction getAcaoDefensiva() {
        return acaoDefensiva;
    }
    public int getValorAtaque() {
        return valorAtaque;
    }
    public int getValorResposta() {
        return valorResposta;
    }
    public int getDanoRecebido() {
        return danoRecebido;
    }
    public boolean isAtaqueSuperouDefesa() {
        return ataqueSuperouDefesa;
    }
    public int getDanoNoAtacante() {
        return danoNoAtacante;
    }


}
