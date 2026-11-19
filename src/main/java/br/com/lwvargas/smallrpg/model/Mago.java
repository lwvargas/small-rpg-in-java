package br.com.lwvargas.smallrpg.model;

public abstract class Mago extends Personagem{
    private int manaAtual;
    private int manaMaxima;

    protected Mago(String nome, int vidaMaxima, int danoMinimo, int danoMaximo, int defesa, int vigor, int manaMaxima) {
        super(nome, vidaMaxima, danoMinimo, danoMaximo, defesa, vigor);
        this.manaAtual = manaMaxima;
        this.manaMaxima = manaMaxima;
    }
    public int getManaAtual() {
        return manaAtual;
    }
    public int getManaMaxima() {
        return manaMaxima;
    }

    public boolean gastarMana(int custo){
        if(custo<=0){
            throw new IllegalArgumentException("O custo de mana deve ser maior que zero");
        }
        if(manaAtual<custo){
            return false;
        }
        manaAtual -= custo;
        return true;
    }
    public void carregarMana(int manaSalva) {
        if (manaSalva < 0 || manaSalva > manaMaxima) {
            throw new IllegalArgumentException("A mana salva e invalida");
        }
        manaAtual = manaSalva;
    }
    @Override
    public boolean podeUsarHabilidade(){
        return getManaAtual() > 0;
    }
}
