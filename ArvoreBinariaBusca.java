public class ArvoreBinariaBusca {

    class Nodo {
        public Palavra elemento;
        public Nodo esquerdo;
        public Nodo direito;

        public Nodo(Palavra elemento) {
            this.elemento = elemento;
            this.esquerdo = null;
            this.direito = null;
        }
    }

    private Nodo raiz;
    private int totalElementos;

    public ArvoreBinariaBusca() {
        this.raiz = null;
        this.totalElementos = 0;
    }

    public boolean estaVazia() {
        return raiz == null;
    }

    public void insere(Palavra palavraNova) {
        this.raiz = insereRec(palavraNova, this.raiz);
    }

    private Nodo insereRec(Palavra palavraNova, Nodo nodoAtual) {
        if (nodoAtual == null) {
            totalElementos++;
            return new Nodo(palavraNova);
        }

        int comparacao = palavraNova.palavra.compareTo(nodoAtual.elemento.palavra);

        if (comparacao < 0) {
            nodoAtual.esquerdo = insereRec(palavraNova, nodoAtual.esquerdo);
        } else if (comparacao > 0) {
            nodoAtual.direito = insereRec(palavraNova, nodoAtual.direito);
        }

        return nodoAtual;
    }

    public Palavra busca(String chave) {
        return buscaRec(chave, this.raiz);
    }

    private Palavra buscaRec(String chave, Nodo nodoAtual) {
        if (nodoAtual == null) return null;

        int comparacao = chave.compareTo(nodoAtual.elemento.palavra);

        if (comparacao < 0) {
            return buscaRec(chave, nodoAtual.esquerdo);
        } else if (comparacao > 0) {
            return buscaRec(chave, nodoAtual.direito);
        } else {
            return nodoAtual.elemento;
        }
    }

    public void imprimeEmOrdem() {
        imprimeOrdem(this.raiz);
    }

    private void imprimeOrdem(Nodo nodoAtual) {
        if (nodoAtual == null) return;

        imprimeOrdem(nodoAtual.esquerdo);
        System.out.print(nodoAtual.elemento.palavra + ": ");
        nodoAtual.elemento.imprimirOcorrencias();
        imprimeOrdem(nodoAtual.direito);
    }

    public Nodo getRaiz() {
        return this.raiz;
    }
}
