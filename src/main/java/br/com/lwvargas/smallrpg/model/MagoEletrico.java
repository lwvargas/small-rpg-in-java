package br.com.lwvargas.smallrpg.model;

import java.util.Random;

public class MagoEletrico extends Mago{
    private static final int CUSTO_MANA = 8;
    private static final int CHANCE_ATORDOAMENTO = 50;
    private static final int DURACAO_ATORDOAMENTO = 1;

    private final Random random = new Random();

    public MagoEletrico(String nome){
        super(nome,
                95,
                13,
                18,
                7,
                7,
                35);
    }
    @Override
    public String getNomeHabilidade(){
        return "DESCARGA ELETRICA";
    }
    @Override
    public String getDescricaoHabilidade(){
        return "Causa dano e tem 50% de chance de atordoar o inimigo por uma rodada";
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
        int sorteio = random.nextInt(100) + 1;
        if (sorteio <= CHANCE_ATORDOAMENTO && !alvo.possuiEfeito(TipoEfeito.ATORDOAMENTO)) {
            alvo.adicionarEfeito(new EfeitoStatus(TipoEfeito.ATORDOAMENTO, DURACAO_ATORDOAMENTO, 1));
        }
    }
    @Override
    public boolean podeUsarHabilidade() {
        return getManaAtual() >= CUSTO_MANA;
    }
}
