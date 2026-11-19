package br.com.lwvargas.smallrpg.model;

public abstract class Personagem extends Entidade{
    private int nivel;
    private int experiencia;

    protected Personagem(String nome, int vidaMaxima, int danoMinimo, int danoMaximo, int defesa, int vigor) {
        super(nome, vidaMaxima, danoMinimo, danoMaximo, defesa, vigor);
        this.nivel = 1;
        this.experiencia = 0;
    }
    public int getNivel(){
        return nivel;
    }
    public int getExperiencia(){
        return experiencia;
    }
    public abstract String getNomeHabilidade();
    public abstract String getDescricaoHabilidade();
    public abstract int usarHabilidade(Entidade alvo);

    public boolean podeUsarHabilidade(){
        return true;
    }

    public void aplicarEfeitoHabilidade(Entidade alvo) {
    }

    public void ganharExperiencia(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("A experiencia nao pode ser negativa");
        }

        experiencia += quantidade;
        while (experiencia >= experienciaNecessariaParaProximoNivel()) {
            experiencia -= experienciaNecessariaParaProximoNivel();
            nivel++;
            aumentarAtributosAoSubirNivel();
            System.out.println(getNome() + " subiu para o nivel " + nivel + "!");
        }
    }

    public void carregarProgresso(int nivelSalvo, int experienciaSalva) {
        if (nivelSalvo < 1 || experienciaSalva < 0) {
            throw new IllegalArgumentException("O progresso salvo e invalido");
        }

        nivel = 1;
        experiencia = 0;
        while (nivel < nivelSalvo) {
            nivel++;
            aumentarAtributosAoSubirNivel();
        }
        ganharExperiencia(experienciaSalva);
    }

    public void mostrarStatus() {
        System.out.println("Nome: " + getNome());
        System.out.println("Nivel: " + nivel);
        System.out.println("Experiencia: " + experiencia + "/" + experienciaNecessariaParaProximoNivel());
        System.out.println("Vida: " + getVidaAtual() + "/" + getVidaMaxima());
        System.out.println("Dano: " + getDanoMinimo() + "-" + getDanoMaximo());
        System.out.println("Defesa: " + getDefesa());
        System.out.println("Vigor: " + getVigor());

        if (this instanceof Mago mago) {
            System.out.println("Mana: " + mago.getManaAtual() + "/" + mago.getManaMaxima());
        } else if (this instanceof ArqueiroFlechaExplosiva arqueiro) {
            System.out.println("Flechas especiais: " + arqueiro.getFlechasEspeciais());
        } else if (this instanceof ArqueiroFlechasVenenosas arqueiro) {
            System.out.println("Flechas especiais: " + arqueiro.getFlechasEspeciais());
        }
    }

    private int experienciaNecessariaParaProximoNivel() {
        return 10 + (nivel - 1) * 5;
    }
}
