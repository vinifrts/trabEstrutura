public class TabelaHash {
    private static final int TAMANHO_TABELA = 26;
    private ArvoreBinariaBusca[] estrutura;

    public TabelaHash() {
        estrutura = new ArvoreBinariaBusca[TAMANHO_TABELA];
        for (int i = 0; i < TAMANHO_TABELA; i++) {
            estrutura[i] = new ArvoreBinariaBusca();
        }
    }

    private int obterIndice(String texto) {
        return Character.toLowerCase(texto.charAt(0)) - 'a';
    }

    public void adicionar(Palavra termo) {
        int indice = obterIndice(termo.palavra);
        if (indice >= 0 && indice < TAMANHO_TABELA) {
            estrutura[indice].insere(termo);
        }
    }

    public Palavra localizar(String termo) {
        int indice = obterIndice(termo);
        if (indice >= 0 && indice < TAMANHO_TABELA) {
            return estrutura[indice].busca(termo);
        }
        return null;
    }

    public void exibir() {
        for (int i = 0; i < TAMANHO_TABELA; i++) {
            if (!estrutura[i].estaVazia()) {
                estrutura[i].imprimeEmOrdem();
            }
        }
    }

    public ArvoreBinariaBusca acessarArvore(int posicao) {
        return estrutura[posicao];
    }
}
