package br.com.lwvargas.smallrpg.service;

import br.com.lwvargas.smallrpg.model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LeituraArquivo {
    private static final String ARQUIVO = "save.csv";

    public Personagem carregar(Missao[] missoes) {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            return null;
        }

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String primeiraLinha = leitor.readLine();
            if (primeiraLinha == null) {
                return null;
            }

            String[] dados = primeiraLinha.split(",", -1);
            if (dados.length < 4) {
                throw new IllegalArgumentException("Arquivo de salvamento incompleto");
            }
            Personagem personagem = criarPersonagem(dados[0], dados[1]);
            personagem.carregarProgresso(Integer.parseInt(dados[2]), Integer.parseInt(dados[3]));

            if (dados.length > 4 && personagem instanceof Mago mago && !dados[4].isBlank()) {
                int manaSalva = Integer.parseInt(dados[4]);
                if (manaSalva >= 0) {
                    mago.carregarMana(manaSalva);
                }
            }
            if (dados.length > 5 && !dados[5].isBlank()) {
                int flechasSalvas = Integer.parseInt(dados[5]);
                if (flechasSalvas >= 0 && personagem instanceof ArqueiroFlechaExplosiva arqueiro) {
                    arqueiro.carregarFlechasEspeciais(flechasSalvas);
                } else if (flechasSalvas >= 0 && personagem instanceof ArqueiroFlechasVenenosas arqueiro) {
                    arqueiro.carregarFlechasEspeciais(flechasSalvas);
                }
            }

            String linha = leitor.readLine();
            int i = 0;
            while (linha != null && i < missoes.length) {
                String[] missao = linha.split(",", -1);
                if (missao.length > 1 && Boolean.parseBoolean(missao[1])) {
                    missoes[i].concluir();
                }
                i++;
                linha = leitor.readLine();
            }

            return personagem;
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Erro ao carregar o jogo.");
            return null;
        }
    }

    private Personagem criarPersonagem(String classe, String nome) {
        switch (classe) {
            case "GuerreiroBarbaro": return new GuerreiroBarbaro(nome);
            case "GuerreiroDualSword": return new GuerreiroDualSword(nome);
            case "GuerreiroEscudoEspada": return new GuerreiroEscudoEspada(nome);
            case "TanqueRefletor": return new TanqueRefletor(nome);
            case "TanqueRedutor": return new TanqueRedutor(nome);
            case "MagoFogo": return new MagoFogo(nome);
            case "MagoGelo": return new MagoGelo(nome);
            case "MagoEletrico": return new MagoEletrico(nome);
            case "ArqueiroFlechaExplosiva": return new ArqueiroFlechaExplosiva(nome);
            case "ArqueiroFlechasVenenosas": return new ArqueiroFlechasVenenosas(nome);
            default: throw new IllegalArgumentException("Classe salva desconhecida: " + classe);
        }
    }
}
