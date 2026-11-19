package br.com.lwvargas.smallrpg.model;

public class MagoGelo extends Mago{
    private static final int CUSTO_MANA = 5;
    private static final int DURACAO_LENTIDAO = 2;
    private static final int REDUCAO_DANO_INIMIGO = 3;

    public MagoGelo(String nome){
        super(nome,
                110,
                7,
                12,
                9,
                9,
                50);
    }
    @Override
    public String getNomeHabilidade(){
        return "FICA FRIO AI";
    }
    @Override
    public String getDescricaoHabilidade(){
        return "Causa dano e reduz o dano no inimigo";
    }
    @Override
    public int usarHabilidade(Entidade alvo){
        if(!gastarMana(CUSTO_MANA)){
            return 0;
        }
        return rolarAtaque();
    }
    @Override
    public void aplicarEfeitoHabilidade(Entidade alvo) {
        if (!alvo.possuiEfeito(TipoEfeito.REDUCAO_DANO_CAUSADO)) {
            alvo.adicionarEfeito(new EfeitoStatus(TipoEfeito.REDUCAO_DANO_CAUSADO, DURACAO_LENTIDAO, REDUCAO_DANO_INIMIGO));
        }
    }
    @Override
    public boolean podeUsarHabilidade() {
        return getManaAtual() >= CUSTO_MANA;
    }
}
