package br.com.lwvargas.smallrpg.model;

public class TanqueRefletor extends Tanque{
    public TanqueRefletor(String nome) {
        super(nome,
                150,
                4,
                8,
                14,
                3
        );
    }
    @Override
    public String getNomeHabilidade(){
        return "Ataque com escudo";
    }
    @Override
    public String getDescricaoHabilidade(){
        return "Ataca com menos dano e ativa a reflexao de dano nesta rodada";
    }
    @Override
    public int usarHabilidade(Entidade alvo){
        ativarPassivaNaRodada();
        return  Math.max(1, rolarAtaque()/2);
    }
    @Override
    public int receberAtaque(Entidade atacante, int dano, int defesaRolada){
        if(!isPassivaAtivaNaRodada() || defesaRolada == 0){
            receberDano(dano);
            return dano;
        }

        int diferenca = dano - defesaRolada;
        int porcentagem = diferenca <= 3 ? 50:25;
        int danoRefletido = Math.max(1, dano*porcentagem/100);

        atacante.receberDano(danoRefletido);
        receberDano(dano);
        return dano;
    }

    @Override
    public int receberAtaqueBloqueado(Entidade atacante, int dano, int defesaRolada){
        if(!isPassivaAtivaNaRodada()){
            return 0;
        }

        int danoRecebido = Math.max(1, dano/2);
        int danoRefletido = Math.max(1, dano*75/100);

        receberDano(danoRecebido);
        atacante.receberDano(danoRefletido);

        return danoRecebido;
    }
}
