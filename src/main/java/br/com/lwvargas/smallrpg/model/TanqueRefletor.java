package br.com.lwvargas.smallrpg.model;

public class TanqueRefletor extends Tanque{
    public TanqueRefletor(String nome) {
        super(nome, 150, 4, 8, 14, 3);
    }

    @Override
    public int receberAtaque(Entidade atacante, int dano, int defesaRolada){
        int diferenca = dano - defesaRolada;

        if(defesaRolada == 0){
            receberDano(dano);
            return dano;
        }else{
            int porcentagem = diferenca <= 3 ? 50:25;
            int danoRefletido = Math.max(1, dano*porcentagem/100);

            atacante.receberDano(danoRefletido);
        }
        receberDano(dano);
        return dano;
    }

    @Override
    public int receberAtaqueBloqueado(Entidade atacante, int dano, int defesaRolada){
        int danoRecebido = Math.max(1, dano/2);
        int danoRefletido = Math.max(1, dano*75/100);

        receberDano(danoRecebido);
        atacante.receberDano(danoRefletido);

        return danoRecebido;
    }
}
