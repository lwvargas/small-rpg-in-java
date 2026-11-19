package br.com.lwvargas.smallrpg.model;

public class MagoFogo extends Mago{
    private static final int CUSTO_MANA = 5;
    private static final int DURACAO_QUEIMADURA = 3;
    private static final int DANO_QUEIMADURA = 4;
    private static final int BONUS_DANO = 3;

    public MagoFogo(String nome){
        super(nome,
                100,
                11,
                16,
                8,
                8,
                40);
    }
    @Override
    public String getNomeHabilidade(){
        return "EXPLOSAO DE FOGO";
    }
    @Override
    public String getDescricaoHabilidade(){
        return "Joga uma magia de fogo aplicando dano continuo";
    }
    @Override
    public int usarHabilidade(Entidade alvo){
        if(!gastarMana(CUSTO_MANA)){
            return 0;
        }
        return rolarAtaque()+BONUS_DANO;
    }
    @Override
    public void aplicarEfeitoHabilidade(Entidade alvo) {
        if (!alvo.possuiEfeito(TipoEfeito.DANO_CONTINUO)) {
            alvo.adicionarEfeito(new EfeitoStatus(TipoEfeito.DANO_CONTINUO, DURACAO_QUEIMADURA, DANO_QUEIMADURA));
        }
    }
    @Override
    public boolean podeUsarHabilidade() {
        return getManaAtual() >= CUSTO_MANA;
    }
}
