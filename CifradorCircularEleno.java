import java.util.Scanner;
import java.text.Normalizer;

public class CifradorCircularEleno {
    public static void main(String[]args) {
        Scanner teclado = new Scanner(System.in);
        boolean continuar = true;
        String mensagem;
        String mensagem_final;

        while(continuar) { // Lopp principal
            System.out.print(gerarLinha());
            System.out.print(" Digite sua mensagem a ser processada: ");
            mensagem = teclado.nextLine();

            mensagem = formatarTexto(mensagem);
            int tamanho = mensagem.length();

            if (tamanho == 0) {
                System.out.println(" Mensagem invalida.");
                continue;
            }

            System.out.print("\n Digite o valor do passo ( maior que 0): ");
            int passo = Integer.parseInt(teclado.nextLine());

            if (passo < 1) {
                System.out.println(" Digite um inteiro maior que zero.");
                continue;
            }

            if (mdc(tamanho, passo) != 1) {
                System.out.println(" Passo invalido: nao percorre toda a mensagem.");
                continue;
            }

            System.out.print("\n Quadro de opcoes\n 1- Criptografar\n 2- Descriptografar\n 3- Sair\n Escolha: ");
            char opcao = teclado.nextLine().charAt(0);
            System.out.print("\n");
            
            switch(opcao) {
                case '1':
                    mensagem_final = criptografar(mensagem, tamanho, passo);
                    System.out.print(" Mensagem original: " + mensagem + "\n");
                    System.out.print(" Passo utilizado: " + passo + "\n");
                    System.out.print(" Método utilizado: Criptografar\n");
                    System.out.print(" Mensagem final: " + mensagem_final + "\n");
                    break;

                case '2':
                    mensagem_final = decriptografar(mensagem, tamanho, passo);
                    System.out.print(" Mensagem original: " + mensagem + "\n");
                    System.out.print(" Passo utilizado: " + passo + "\n");
                    System.out.print(" Método utilizado: Decriptografar\n");
                    System.out.print(" Mensagem final: " + mensagem_final + "\n");
                    break;

                case '3':
                    System.out.print(" Saindo...");
                    continuar = false;
                    break;

                default:
                    System.out.print(" Opcao invalida.");
            }
        }
        teclado.close();
    }
    // Funções
    public static String gerarLinha() {
        return "\n--------------------------------------------------\n";
    }

    public static String formatarTexto(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        texto = texto.replaceAll("[^A-Za-z]", "");
        texto = texto.toUpperCase();
        return texto;
    }
    
    public static int mdc(int a, int b) { // Calcula o máximo divisor comum entre o tamanho da String da mensagem e o valor do passo.
        if (a < 1 || b < 1) {
            throw new IllegalArgumentException("Valores devem ser >= 1");
        }

        while(b != 0) {
            int resto = a % b;
            a = b;
            b = resto;
        }
        return a; 	
    }

    public static String criptografar(String mensagem, int tamanho, int passo) {
        char[] resultado = new char[tamanho];
        int indice = 0;
        
        for(int i = 0; i < tamanho; i++) {
            resultado[i] = mensagem.charAt(indice);
            indice = (indice + passo) % tamanho;
        }
        return new String(resultado);
    }
    
    public static String decriptografar(String mensagem, int tamanho, int passo) {
        char[] original = new char[tamanho];
        int indice = 0;
        
        for (int i = 0; i < tamanho; i++) {
            original[indice] = mensagem.charAt(i);
            indice = (indice + passo) % tamanho;
        }
        return new String(original);
    }
}