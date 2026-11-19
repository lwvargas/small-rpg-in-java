package br.com.lwvargas.smallrpg.model;

public class GuerreiroBarbaro extends Guerreiro {
    private static final int BONUS_DANO_HABILIDADE = 8;
    private static final int REDUCAO_DEFESA = 2;
    private static final int DURACAO_REDUCAO_DEFESA = 1;

    public GuerreiroBarbaro(String nome) {
        super(nome,
                110,
                14,
                22,
                4,
                10);
    }
    @Override
    public String getNomeHabilidade(){
        return "Furia Barbara";
    }
    @Override
    public String getDescricaoHabilidade(){
        return "Aumenta o dano do ataque, mas reduz a defesa por uma rodada";
    }
    @Override
    public int usarHabilidade(Entidade alvo){
        if(!possuiEfeito(TipoEfeito.REDUCAO_DEFESA)){
            adicionarEfeito(new EfeitoStatus(TipoEfeito.REDUCAO_DEFESA, DURACAO_REDUCAO_DEFESA, REDUCAO_DEFESA));
        }
        return rolarAtaque()+BONUS_DANO_HABILIDADE;
    }

}
