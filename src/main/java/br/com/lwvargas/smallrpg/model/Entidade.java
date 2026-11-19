package br.com.lwvargas.smallrpg.model;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Entidade {
    private String nome;
    private int vidaAtual;
    private int vidaMaxima;
    private int danoMinimo;
    private int danoMaximo;
    private int defesa;
    private int vigor;
    private final List<EfeitoStatus> efeitosAtivos = new ArrayList<>();

    private final Random random = new Random();

    protected Entidade(String nome, int vidaMaxima, int danoMinimo, int danoMaximo, int defesa, int vigor){
        this.nome = nome;
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima;
        this.danoMinimo = danoMinimo;
        this.danoMaximo = danoMaximo;
        this.defesa = defesa;
        this.vigor = vigor;
    }

    public int getVigor() {
        return vigor;
    }
    public int getDefesa() {
        return defesa;
    }
    public int getDanoMinimo() {
        return danoMinimo;
    }
    public int getDanoMaximo(){
        return danoMaximo;
    }
    public int getVidaMaxima() {
        return vidaMaxima;
    }
    public int getVidaAtual() {
        return vidaAtual;
    }
    public String getNome() {
        return nome;
    }

    public void adicionarEfeito(EfeitoStatus efeito){
        if(efeito == null){
            throw new IllegalArgumentException("O efeito nao pode ser nulo");
        }
        efeitosAtivos.add(efeito);
    }
    public boolean possuiEfeito(TipoEfeito tipo) {
        return buscarEfeito(tipo) != null;
    }
    public EfeitoStatus buscarEfeito(TipoEfeito tipo){
        for(EfeitoStatus efeito : efeitosAtivos){
            if(efeito.getTipo()==tipo){
                return efeito;
            }
        }
        return null;
    }
    public int processarDanoContinuo(){
        int danoTotal = 0;

        for(EfeitoStatus efeito : efeitosAtivos){
            if(efeito.getTipo()==TipoEfeito.DANO_CONTINUO){
                int dano = efeito.getIntensidade();
                receberDano(dano);
                danoTotal+=dano;
            }
        }
        return danoTotal;
    }
    public void avancarRodadaEfeitos(){
        Iterator<EfeitoStatus>iterator=efeitosAtivos.iterator();

        while(iterator.hasNext()){
            EfeitoStatus efeito = iterator.next();
            efeito.avancarRodada();

            if(efeito.estaExpirado()){
                iterator.remove();
            }
        }
    }
    public void receberDano(int dano){
        if (dano <= 0) {
            return;
        }
        vidaAtual -= dano;
        if(vidaAtual<0){
            vidaAtual=0;
        }
    }
    public void restaurarVida() {
        vidaAtual = vidaMaxima;
    }
    protected void aumentarAtributosAoSubirNivel() {
        vidaMaxima += 10;
        vidaAtual += 10;
        danoMinimo++;
        danoMaximo++;
        defesa++;
        vigor++;
    }
    public boolean estaVivo(){
        return vidaAtual > 0;
    }
    public int rolarAtaque(){
        return random.nextInt(danoMaximo - danoMinimo + 1) + danoMinimo;
    }
    public int rolarDefesa(){
        EfeitoStatus reducaoDefesa = buscarEfeito(TipoEfeito.REDUCAO_DEFESA);
        int defesaEfetiva = defesa;

        if(reducaoDefesa != null){
            defesaEfetiva = Math.max(1, defesa - reducaoDefesa.getIntensidade());
        }
        return random.nextInt(defesaEfetiva)+1;
    }
    public int rolarEsquiva(){
        return random.nextInt(vigor)+1;
    }

    public int receberAtaque(Entidade atacante, int dano, int defesaRolada){
        EfeitoStatus reducaoDano = buscarEfeito(TipoEfeito.REDUCAO_DANO);
        int danoFinal = dano;

        if(reducaoDano != null){
            danoFinal = Math.max(0, dano- reducaoDano.getIntensidade());
        }
        receberDano(danoFinal);
        return danoFinal;
    }
    public int receberAtaqueBloqueado(Entidade atacante, int attack, int defesaRolada){
        return 0;
    }
}

