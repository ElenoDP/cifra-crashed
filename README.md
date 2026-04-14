Explique com suas próprias palavras:
● O que significa fazer “saltos de 5”?

R: Significa avançar 5 posições a partir do índice atual a cada passo.
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
● O que significa o percurso ser “circular”?

R: Significa que, ao chegar no final da string, o percurso continua do início, formando um ciclo.
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
● Por que usamos o operador de módulo % na expressão (indice + 5) % 47?

R: Garante que o índice volte ao início quando ultrapassa o tamanho da string.
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
● Por que, com tamanho 47 e salto 5, todos os índices de 0 a 46 são visitados exatamente
uma vez?

R: Porque o MDC ( Máximo Divisor Comum ) entre 47 e 5 é 1.
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
● Apresente os 10 primeiros índices visitados pela regra de salto 5 sobre a string de 47
caracteres, começando do índice 0.

R: 0, 5, 10, 15, 20, 25, 30, 35, 40, 45.
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
● Em quais valores de salto a cifra é reversível?

R: MDC(tamanho, passo) == 1
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
● Qual a relação necessária entre o tamanho da string (47) e o valor do salto para que o
algoritmo funcione corretamente (visite todos os índices sem repetir antes do fim)
R: O motivo é a divisibilidade comum.Quando o (MDC(K, N) = E) e esse (E) é maior que 1, significa que tanto o seu passo (K)
  quanto o tamanho do círculo (N) são múltiplos desse mesmo número (E). Imagine o círculo de 10 posições e passos de 2: Os
  dois números são divisíveis por 2. Cada vez que você pula 2, você está somando uma "unidade de (D)". Como o círculo tam
  bém é feito de "unidades de (D)" (10 = 5 * 2), você voltará ao zero (completará o ciclo) assim que somar (D) vezes o su
  ficiente para atingir (N). Você nunca tocará nos números ímpares porque não existe uma combinação de somas de 2 que resulte em um número ímpar.
  O "buraco" entre os múltiplos de (D) é intransponível.
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Questão Complementar:
  Como você faria para "restaurar" os espaços e a pontuação original a partir da string limpa?
  Descreva a lógica (não é necessário codificar esta parte).
R: Para recuperar espaço e pontuação original, precisaria de uma chave que mapeia quais caracteres vão ter acentos de acordo com uma metodologia escolhida. Pode ser também uma permutação com uma lista dos caracteres, um XOR bit por bit da mensagem com a chave, tem muitas opções e maneiras diferentes.
