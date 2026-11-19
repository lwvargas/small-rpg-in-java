package br.com.lwvargas.smallrpg.model;

public class TanqueRedutor extends Tanque{
    public TanqueRedutor(String nome){
        super(nome,
                180,
                3,
                7,
                20,
                3
        );
    }
    @Override
    public String getNomeHabilidade(){
        return "Defesa Total";
    }
    @Override
    public String getDescricaoHabilidade(){
        return "Diminui o dano do ataque na rodada porem reduz consideravelmente o dano recebido";
    }
    @Override
    public int usarHabilidade(Entidade alvo){
        ativarPassivaNaRodada();
        return Math.max(1, rolarAtaque()/2);
    }
    @Override
    public int receberAtaque(Entidade atacante, int dano, int defesaRolada){
        if(!isPassivaAtivaNaRodada() || defesaRolada == 0){
            receberDano(dano);
            return dano;
        }
        int danoRecebido = Math.max(1, dano - defesaRolada);

        receberDano(danoRecebido);
        return danoRecebido;
    }
}
