public class Main {
    public static void main(String[] args) {
        String[] palavrasChave = LeitorArquivo.carregarChavesDeArquivo("/Users/Usuario/IdeaProjects/av3_EstruturaDeDados/palavrasChave_EstruturaDeDados.txt");

        LeitorArquivo leitor = new LeitorArquivo(palavrasChave);
        leitor.analisarArquivo("/Users/Usuario/IdeaProjects/av3_EstruturaDeDados/texto.txt");
        leitor.mostrar();
        leitor.salvarEmArquivo("/Users/Usuario/IdeaProjects/av3_EstruturaDeDados/saidaarquivo_EstruturaDeDados.txt");
    }
}
