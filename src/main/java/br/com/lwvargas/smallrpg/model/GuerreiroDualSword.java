package br.com.lwvargas.smallrpg.model;

import java.util.Random;

public class GuerreiroDualSword extends Guerreiro{
    private static final int CHANCE_DANO_DUPLO = 50;
    private final Random random = new Random();

    public GuerreiroDualSword(String nome) {
        super(nome,
                95,
                11,
                16,
                8,
                10);
    }
    @Override
    public String getNomeHabilidade(){
        return "Ataque duplo";
    }
    @Override
    public String getDescricaoHabilidade(){
        return "Tem 50% de chance de causar o dobro de dano, se falhar causa metade";
    }
    @Override
    public int usarHabilidade(Entidade alvo){
        int dano = rolarAtaque();
        int sorteio = random.nextInt(100)+1;
        if(sorteio <= CHANCE_DANO_DUPLO){
            return dano*2;
        }
        return dano/2;
    }
}
