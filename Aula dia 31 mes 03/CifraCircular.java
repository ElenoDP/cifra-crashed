import java.util.Scanner;
import java.text.Normalizer;

public class CifraCircular {
    public static void main(String[]args) {
        Scanner teclado = new Scanner(System.in);
        boolean continuar = true;
        String mensagem;
        String mensagem_final;

        while(continuar) { // Lopp principal
            System.out.print(gerarLinha());
            System.out.print(" Digite sua mensagem a ser processada: ");
            mensagem = teclado.nextLine(); // Pega a mensagem que o usuário informou

            mensagem = formatarTexto(mensagem); // Formata a mensagem
            int tamanho = mensagem.length(); // Pega o tamanho da mensagem formatada

            if (tamanho == 0) { // Verifica se o tamanho é 0, ou seja, inválido pra continuar
                System.out.println(" Mensagem invalida.");
                continue;
            }

            System.out.print(" Valores disponíveis de passo para tamanho " + tamanho + "\n");
            for(int i = 1; i < tamanho; i ++) { // Exibe todos os valores de passo para criptografar ou decriptografar
                if(mdc(i, tamanho) == 1) {
                    System.out.print(i + ", ");
                }
            }

            System.out.print("\n Digite o valor do passo ( maior que 0): ");
            int passo = Integer.parseInt(teclado.nextLine()); // Pega o valor do passo informado

            if (passo < 1) { // Verifica se é menor que 1, ou seja, inválido
                System.out.println(" Digite um inteiro maior que zero.");
                continue;
            }

            if (mdc(tamanho, passo) != 1) { // Verifica se o passo vai passar por toda a mensagem
                System.out.println(" Passo invalido: nao percorre toda a mensagem.");
                continue;
            }

            System.out.print("\n Quadro de opcoes\n 1- Criptografar\n 2- Descriptografar\n 3- Sair\n Escolha: ");
            char opcao = teclado.nextLine().charAt(0); // Pega a opcao que o usuário informou
            System.out.print("\n");
            
            switch(opcao) { // Opcoes
                case '1': // Criptografar
                    mensagem_final = criptografar(mensagem, tamanho, passo); // Metodo criptografar
                    System.out.print(" Mensagem original: " + mensagem + "\n");
                    System.out.print(" Passo utilizado: " + passo + "\n");
                    System.out.print(" Método utilizado: Criptografar\n");
                    System.out.print(" Mensagem final: " + mensagem_final + "\n");
                    break;

                case '2': // Decriptografar
                    mensagem_final = decriptografar(mensagem, tamanho, passo); // Metodo decriptografar
                    System.out.print(" Mensagem original: " + mensagem + "\n");
                    System.out.print(" Passo utilizado: " + passo + "\n");
                    System.out.print(" Método utilizado: Decriptografar\n");
                    System.out.print(" Mensagem final: " + mensagem_final + "\n");
                    break;

                case '3': // Sair
                    System.out.print(" Saindo...");
                    continuar = false; // Cancela o próximo lopp
                    break;

                default: //  Opcao diferente de [ 1, 2, 3 ]
                    System.out.print(" Opcao invalida.");
            } // Fim opcoes
        }
        teclado.close(); // Fecha o Scanner teclado, como o Marcos recomendou a exatamente 1 ano atrás
    }

    // Funções
    public static String gerarLinha() { //  Gera uma linha muito bonita e de tamanho fixo
        return "\n--------------------------------------------------\n";
    }

    public static String formatarTexto(String texto) { // Formata a mensagem
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);          // 1. Decompõe caracteres acentuados (ex: 'ã' -> 'a' + '~')
        texto = texto.replaceAll("\\p{InCombiningDiacriticalMarks}+", ""); // 2. Remove os acentos gerados na etapa anterior
        texto = texto.replaceAll("[^A-Za-z]", "");                         // 3. Apenas letras ASCII ( A-Z ) E ( a-z )
        texto = texto.toUpperCase();                                       // 4. Padronização para maiúsculas
        return texto; // Retorna a mensagem formatada
    }
    
    public static int mdc(int a, int b) { // Calcula o Máximo Divisor Comum
        if (a < 1 || b < 1) { // Verifica se são menores que 1 ( não pode isso )
            throw new IllegalArgumentException("Valores devem ser >= 1");
        }

        while(b != 0) { // Calcula o MDC
            int resto = a % b;
            a = b;
            b = resto;
        }
        return a; 	
    }

    public static String criptografar(String mensagem, int tamanho, int passo) { // Método da criptografia
        char[] resultado = new char[tamanho];
        int indice = 0;
        
        for(int i = 0; i < tamanho; i++) {
            resultado[i] = mensagem.charAt(indice);
            indice = (indice + passo) % tamanho;
        }
        return new String(resultado);
    }
    
    public static String decriptografar(String mensagem, int tamanho, int passo) { // Método do oposto de criptografia... que é decriptografia
        char[] original = new char[tamanho];
        int indice = 0;
        
        for (int i = 0; i < tamanho; i++) {
            original[indice] = mensagem.charAt(i);
            indice = (indice + passo) % tamanho;
        }
        return new String(original);
    }
}