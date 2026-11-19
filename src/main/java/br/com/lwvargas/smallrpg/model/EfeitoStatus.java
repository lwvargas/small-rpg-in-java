package br.com.lwvargas.smallrpg.model;

public class EfeitoStatus {
    private TipoEfeito tipo;
    private int intensidade;
    private int rodadasRestantes;

    public EfeitoStatus(TipoEfeito tipo, int rodadasRestantes, int intensidade) {
        if(intensidade <= 0){
            throw new IllegalArgumentException("Intensidade nao deve ser 0 ou negativo");
        }
        if(tipo == null){
            throw new IllegalArgumentException("O tipo nao deve ser nulo");
        }
        if(rodadasRestantes <= 0){
            throw new IllegalArgumentException("Rodadas restantes devem ser maior que zero");
        }
        this.tipo = tipo;
        this.intensidade = intensidade;
        this.rodadasRestantes = rodadasRestantes;

    }
    public TipoEfeito getTipo() {
        return tipo;
    }
    public int getIntensidade() {
        return intensidade;
    }
    public int getRodadasRestantes() {
        return rodadasRestantes;
    }

    public void avancarRodada(){
        if(rodadasRestantes > 0){
            rodadasRestantes--;
        }
    }
    public boolean estaExpirado(){
        return rodadasRestantes == 0;
    }
}


