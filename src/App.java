import java.util.Random;

public class App {

    static final int[] tamanhosTesteGrande = { 31_250_000, 62_500_000, 125_000_000, 250_000_000, 500_000_000 };
    static final int[] tamanhosTesteMedio = { 12_500, 25_000, 50_000, 100_000, 200_000 };
    static final int[] tamanhosTestePequeno = { 3, 6, 12, 24, 48 };

    static Random aleatorio = new Random(42);
    static long operacoes;
    static double nanoToMilli = 1.0 / 1_000_000;

    static int codigo1(int[] vetor) {
        int resposta = 0;

        for (int i = 0; i < vetor.length; i += 2) {
            operacoes++; // operação do loop
            resposta += vetor[i] % 2;
            operacoes++; // operação do cálculo
        }

        return resposta;
    }


    static int codigo2(int[] vetor) {

        int contador = 0;

        for (int k = (vetor.length - 1); k > 0; k /= 2) {
            operacoes++;

            for (int i = 0; i <= k; i++) {
                operacoes++;
                contador++;
            }

        }

        return contador;
    }


    static void codigo3(int[] vetor) {

        for (int i = 0; i < vetor.length - 1; i++) {
            operacoes++;

            int menor = i;

            for (int j = i + 1; j < vetor.length; j++) {
                operacoes++;

                if (vetor[j] < vetor[menor]) {
                    operacoes++;
                    menor = j;
                }
            }

            int temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
            operacoes += 3;
        }
    }


    static int codigo4(int n) {

        operacoes++;

        if (n <= 2)
            return 1;
        else
            return codigo4(n - 1) + codigo4(n - 2);
    }

    static int[] gerarVetor(int tamanho) {

        int[] vetor = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, tamanho / 2);
        }

        return vetor;
    }


    public static void main(String[] args) {

        System.out.println("===== CODIGO 1 =====");

        for (int tamanho : tamanhosTesteGrande) {

            int[] vetor = gerarVetor(tamanho);

            operacoes = 0;

            long inicio = System.nanoTime();

            codigo1(vetor);

            long fim = System.nanoTime();

            double tempo = (fim - inicio) * nanoToMilli;

            System.out.println("Tamanho: " + tamanho +
                    " | Operacoes: " + operacoes +
                    " | Tempo(ms): " + tempo);
        }

        System.out.println("\n===== CODIGO 2 =====");

        for (int tamanho : tamanhosTesteGrande) {

            int[] vetor = gerarVetor(tamanho);

            operacoes = 0;

            long inicio = System.nanoTime();

            codigo2(vetor);

            long fim = System.nanoTime();

            double tempo = (fim - inicio) * nanoToMilli;

            System.out.println("Tamanho: " + tamanho +
                    " | Operacoes: " + operacoes +
                    " | Tempo(ms): " + tempo);
        }

        System.out.println("\n===== CODIGO 3 =====");

        for (int tamanho : tamanhosTesteMedio) {

            int[] vetor = gerarVetor(tamanho);

            operacoes = 0;

            long inicio = System.nanoTime();

            codigo3(vetor);

            long fim = System.nanoTime();

            double tempo = (fim - inicio) * nanoToMilli;

            System.out.println("Tamanho: " + tamanho +
                    " | Operacoes: " + operacoes +
                    " | Tempo(ms): " + tempo);
        }

        System.out.println("\n===== CODIGO 4 =====");

        for (int n : tamanhosTestePequeno) {

            operacoes = 0;

            long inicio = System.nanoTime();

            codigo4(n);

            long fim = System.nanoTime();

            double tempo = (fim - inicio) * nanoToMilli;

            System.out.println("n: " + n +
                    " | Operacoes: " + operacoes +
                    " | Tempo(ms): " + tempo);
        }
    }
}