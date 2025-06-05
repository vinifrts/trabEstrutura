import java.io.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class LeitorArquivo {
    private TabelaHash hashTable;
    private String[] termosChave;

    public LeitorArquivo(String[] palavrasImportantes) {
        this.hashTable = new TabelaHash();
        this.termosChave = new String[palavrasImportantes.length];

        for (int i = 0; i < palavrasImportantes.length; i++) {
            String limpinha = Normalizer.normalize(palavrasImportantes[i], Normalizer.Form.NFD);
            limpinha = limpinha.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
            this.termosChave[i] = limpinha.toLowerCase();
        }
    }

    public static String[] carregarChavesDeArquivo(String caminho) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {
            String linhaLida;
            List<String> listaTemp = new ArrayList<>();

            while ((linhaLida = leitor.readLine()) != null) {
                linhaLida = Normalizer.normalize(linhaLida, Normalizer.Form.NFD);
                linhaLida = linhaLida.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                linhaLida = linhaLida.toLowerCase();
                String[] palavras = linhaLida.split("[,\\s]+");

                for (String palavra : palavras) {
                    if (!palavra.isEmpty()) {
                        listaTemp.add(palavra);
                    }
                }
            }

            return listaTemp.toArray(new String[0]);
        } catch (IOException e) {
            System.err.println("Erro ao ler chaves do arquivo: " + e.getMessage());
            return new String[0];
        }
    }


    public void analisarArquivo(String caminhoArquivo) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linhaAtual;
            int contadorLinha = 1;

            while ((linhaAtual = leitor.readLine()) != null) {
                String tratada = Normalizer.normalize(linhaAtual, Normalizer.Form.NFD);
                tratada = tratada.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                tratada = tratada.toLowerCase().replaceAll("[^a-z\\- ]", "");

                String[] palavrasSeparadas = tratada.split("\\s+");

                for (String palavraAtual : palavrasSeparadas) {
                    for (String chave : termosChave) {
                        if (palavraAtual.equals(chave)) {
                            Palavra achada = hashTable.localizar(chave);
                            if (achada == null) {
                                Palavra nova = new Palavra(chave);
                                nova.adicionarOcorrencia(contadorLinha);
                                hashTable.adicionar(nova);
                            } else {
                                achada.adicionarOcorrencia(contadorLinha);
                            }
                        }
                    }
                }

                contadorLinha++;
            }

            System.out.println("Leitura concluída com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }

    public void mostrar() {
        hashTable.exibir();
    }

    public void salvarEmArquivo(String destino) {
        try (BufferedWriter gravador = new BufferedWriter(new FileWriter(destino))) {
            for (int i = 0; i < 26; i++) {
                ArvoreBinariaBusca arvore = hashTable.acessarArvore(i);
                escreverOrdem(arvore.getRaiz(), gravador);
            }
            System.out.println("Conteúdo exportado para " + destino);
        } catch (IOException e) {
            System.err.println("Erro ao exportar: " + e.getMessage());
        }
    }

    private void escreverOrdem(ArvoreBinariaBusca.Nodo noAtual, BufferedWriter gravador) throws IOException {
        if (noAtual == null) return;

        escreverOrdem(noAtual.esquerdo, gravador);
        gravador.write(noAtual.elemento.palavra + ": ");
        noAtual.elemento.exportarOcorrencias(gravador);
        gravador.newLine();
        escreverOrdem(noAtual.direito, gravador);
    }
}
