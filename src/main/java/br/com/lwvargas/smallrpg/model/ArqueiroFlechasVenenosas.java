package br.com.lwvargas.smallrpg.model;

public class ArqueiroFlechasVenenosas extends Arqueiro{
    private static final int QUANTIDADE_INICIAL_FLECHAS = 5;
    private static final int DURACAO_VENENO = 3;
    private static final int DANO_VENENO = 2;
    private static final int REDUCAO_DEFESA = 1;

    private int flechasEspeciais = QUANTIDADE_INICIAL_FLECHAS;

    public ArqueiroFlechasVenenosas(String nome){
        super(nome,
                90,
                8,
                13,
                5,
                16);
    }
    public int getFlechasEspeciais(){
        return flechasEspeciais;
    }
    public void carregarFlechasEspeciais(int quantidade) {
        if (quantidade < 0 || quantidade > QUANTIDADE_INICIAL_FLECHAS) {
            throw new IllegalArgumentException("A quantidade de flechas salva e invalida");
        }
        flechasEspeciais = quantidade;
    }
    @Override
    public String getNomeHabilidade(){
        return "FLECHA DO VENENO DE ESCORPIAO";
    }
    @Override
    public String getDescricaoHabilidade(){
        return "Aplica veneno, causando dano continuo e reduzindo a armadura do inimigo";
    }
    @Override
    public int usarHabilidade(Entidade alvo){
        if(flechasEspeciais<=0){
            return 0;
        }
        flechasEspeciais--;

        return rolarAtaque();
    }
    @Override
    public void aplicarEfeitoHabilidade(Entidade alvo) {
        if (!alvo.possuiEfeito(TipoEfeito.DANO_CONTINUO)) {
            alvo.adicionarEfeito(new EfeitoStatus(TipoEfeito.DANO_CONTINUO, DURACAO_VENENO, DANO_VENENO));
        }
        if (!alvo.possuiEfeito(TipoEfeito.REDUCAO_DEFESA)) {
            alvo.adicionarEfeito(new EfeitoStatus(TipoEfeito.REDUCAO_DEFESA, DURACAO_VENENO, REDUCAO_DEFESA));
        }
    }
    @Override
    public boolean podeUsarHabilidade() {
        return flechasEspeciais > 0;
    }
}
