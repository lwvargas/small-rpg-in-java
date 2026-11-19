package br.com.lwvargas.smallrpg.model;

import java.util.Random;

public class ArqueiroFlechasMultiplas extends Arqueiro{
    private static final int QUANTIDADE_FLECHAS = 3;
    private static final int CHANCE_PRIMEIRA_FLECHA = 80;
    private static final int REDUCAO_CHANCE_POR_FLECHA = 20;

    private final Random random = new Random();

    public ArqueiroFlechasMultiplas(String nome){
        super(nome,
                80,
                5,
                10,
                4,
                18);
    }
    @Override
    public String getNomeHabilidade(){
        return "CHUVA DE FLECHAS";
    }
    @Override
    public String getDescricaoHabilidade(){
        return "Dispara 3 flechas, cada flecha adicional diminui a chance de acertar o alvo";
    }
    @Override
    public int usarHabilidade(Entidade alvo){
        int danoTotal=0;
        for(int i=0;i<QUANTIDADE_FLECHAS;i++){
            int chanceAcerto = CHANCE_PRIMEIRA_FLECHA-(i*REDUCAO_CHANCE_POR_FLECHA);
            int sorteio = random.nextInt(100)+1;

            if(sorteio<=chanceAcerto){
                danoTotal+=rolarAtaque();
            }
        }
        return danoTotal;
    }
}
