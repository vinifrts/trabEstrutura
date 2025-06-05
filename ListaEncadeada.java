public class ListaEncadeada<T> {

    private No primeiro;

    public ListaEncadeada() {
        this.primeiro = null;
    }

    public class No {
        public T dado;
        public No proximo;

        public No(T dado) {
            this.dado = dado;
            this.proximo = null;
        }
    }

    public No getPrimeiro() {
        return primeiro;
    }

    public void inserir(T novoDado) {
        No novoNo = new No(novoDado);
        novoNo.proximo = primeiro;
        primeiro = novoNo;
    }

    public boolean contem(T valor) {
        No atual = primeiro;
        while (atual != null) {
            if (atual.dado.equals(valor)) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public void imprimir() {
        No atual = primeiro;
        while (atual != null) {
            System.out.print(atual.dado + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }
}
