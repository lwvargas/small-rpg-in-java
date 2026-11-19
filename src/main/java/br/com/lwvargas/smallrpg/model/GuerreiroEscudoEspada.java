package br.com.lwvargas.smallrpg.model;

public class GuerreiroEscudoEspada extends Guerreiro{
    private static final int REDUCAO_DANO = 3;
    private static final int DURACAO_REDUCAO_DANO = 1;

    public GuerreiroEscudoEspada(String nome) {
        super(nome,
                120,
                8,
                13,
                9,
                10);
    }
    @Override
    public String getNomeHabilidade(){
        return "Postura de guarda";
    }
    @Override
    public String getDescricaoHabilidade(){
        return "Ataca e reduz o dano recebido durante a rodada";
    }
    @Override
    public int usarHabilidade(Entidade alvo){
        if(!possuiEfeito(TipoEfeito.REDUCAO_DANO)){
            adicionarEfeito(new EfeitoStatus(TipoEfeito.REDUCAO_DANO, DURACAO_REDUCAO_DANO, REDUCAO_DANO));
        }
        return rolarAtaque();
    }
}
