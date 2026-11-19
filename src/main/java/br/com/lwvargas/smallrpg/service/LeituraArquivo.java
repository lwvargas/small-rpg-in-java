package br.com.lwvargas.smallrpg.service;

import br.com.lwvargas.smallrpg.model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

            List<String> dados = separarCsv(primeiraLinha);
            if (dados.size() < 4) {
                throw new IllegalArgumentException("Arquivo de salvamento incompleto");
            }
            Personagem personagem = criarPersonagem(dados.get(0), dados.get(1));
            personagem.carregarProgresso(Integer.parseInt(dados.get(2)), Integer.parseInt(dados.get(3)));

            if (dados.size() > 4 && personagem instanceof Mago mago && !dados.get(4).isBlank()) {
                int manaSalva = Integer.parseInt(dados.get(4));
                if (manaSalva >= 0) {
                    mago.carregarMana(manaSalva);
                }
            }
            if (dados.size() > 5 && !dados.get(5).isBlank()) {
                int flechasSalvas = Integer.parseInt(dados.get(5));
                if (flechasSalvas >= 0 && personagem instanceof ArqueiroFlechaExplosiva arqueiro) {
                    arqueiro.carregarFlechasEspeciais(flechasSalvas);
                } else if (flechasSalvas >= 0 && personagem instanceof ArqueiroFlechasVenenosas arqueiro) {
                    arqueiro.carregarFlechasEspeciais(flechasSalvas);
                }
            }

            String linha = leitor.readLine();
            int i = 0;
            while (linha != null && i < missoes.length) {
                List<String> missao = separarCsv(linha);
                if (missao.size() > 1 && Boolean.parseBoolean(missao.get(1))) {
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

    private List<String> separarCsv(String linha) {
        List<String> campos = new ArrayList<>();
        StringBuilder campo = new StringBuilder();
        boolean entreAspas = false;

        for (int i = 0; i < linha.length(); i++) {
            char caractere = linha.charAt(i);

            if (caractere == '"') {
                if (entreAspas && i + 1 < linha.length() && linha.charAt(i + 1) == '"') {
                    campo.append('"');
                    i++;
                } else {
                    entreAspas = !entreAspas;
                }
            } else if (caractere == ',' && !entreAspas) {
                campos.add(campo.toString());
                campo.setLength(0);
            } else {
                campo.append(caractere);
            }
        }

        if (entreAspas) {
            throw new IllegalArgumentException("Campo CSV com aspas incompletas");
        }

        campos.add(campo.toString());
        return campos;
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
            case "ArqueiroFlechasMultiplas": return new ArqueiroFlechasMultiplas(nome);
            default: throw new IllegalArgumentException("Classe salva desconhecida: " + classe);
        }
    }
}
