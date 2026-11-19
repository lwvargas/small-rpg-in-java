package br.com.lwvargas.smallrpg.model;

import java.util.Random;

public class ArqueiroFlechaExplosiva extends Arqueiro{
    private static final int QUANTIDADE_INICIAL_FLECHAS = 5;
    private static final int CHANCE_EXPLODIR_ANTES_DO_ALVO = 50;
    private static final int DANO_EXTRA = 5;

    private final Random random = new Random();
    private int flechasEspeciais = QUANTIDADE_INICIAL_FLECHAS;

    public ArqueiroFlechaExplosiva(String nome){
        super(nome,
                85,
                12,
                17,
                4,
                15);
    }
    public int getFlechasEspeciais() {
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
        return "FLECHA COM DINAMITE";
    }
    @Override
    public String getDescricaoHabilidade(){
        return "Flechas com dinamite podem explodir antes do alvo, mas se acertar causa dano extra e atordoamento por uma rodada";
    }
    @Override
    public int usarHabilidade(Entidade alvo){
        if(flechasEspeciais<=0){
            return 0;
        }
        flechasEspeciais--;

        int danoDaFlecha = rolarAtaque() + DANO_EXTRA;
        int sorteio = random.nextInt(100)+1;

        if(sorteio<=CHANCE_EXPLODIR_ANTES_DO_ALVO){
            receberDano(danoDaFlecha);
            if (!possuiEfeito(TipoEfeito.SEM_DEFESA_PROXIMO_ATAQUE)) {
                adicionarEfeito(new EfeitoStatus(TipoEfeito.SEM_DEFESA_PROXIMO_ATAQUE, 1, 1));
            }
            return 0;
        }
        return danoDaFlecha;
    }
    @Override
    public void aplicarEfeitoHabilidade(Entidade alvo) {
        if (!alvo.possuiEfeito(TipoEfeito.ATORDOAMENTO)) {
            alvo.adicionarEfeito(new EfeitoStatus(TipoEfeito.ATORDOAMENTO, 1, 1));
        }
    }
    @Override
    public boolean podeUsarHabilidade(){
        return flechasEspeciais > 0;
    }
}
