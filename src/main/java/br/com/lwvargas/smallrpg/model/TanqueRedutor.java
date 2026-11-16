package br.com.lwvargas.smallrpg.model;

public class TanqueRedutor extends Tanque{
    public TanqueRedutor(String nome){
        super(nome, 180, 3, 7, 17, 2);
    }

    @Override
    public int receberAtaque(Entidade atacante, int dano, int defesaRolada){
        int danoRecebido = dano - defesaRolada;

        if(danoRecebido < 1) {
            danoRecebido = 1;
        }
        receberDano(danoRecebido);
        return danoRecebido;
    }
}
