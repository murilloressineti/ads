# Exercícios

## Exercício 1
n1 = int(input("Digite o primeiro número inteiro: "))
n2 = int(input("Digite o segundo número inteiro: "))

-Operações
soma = n1 + n2
subtracao = n1 - n2
multiplicacao = n1 * n2
divisao = n1 / n2
divisao_truncada = n1 // n2
resto = n1 % n2
exponenciacao = n1 ** n2

-Exibir os resultados
print(f"Soma: {soma}")
print(f"Subtração: {subtracao}")
print(f"Multiplicação: {multiplicacao}")
print(f"Divisão: {divisao}")
print(f"Divisão Truncada: {divisao_truncada}")
print(f"Resto: {resto}")
print(f"Exponenciação: {exponenciacao}")

## Exercício 2
n1 = int(input("Digite o primeiro número inteiro: "))
n2 = int(input("Digite o segundo número inteiro: "))

-Calcular a diferença entre o primeiro e o segundo número
diferenca = n1 - n2

-Calcular o quadrado da diferença
quadrado_da_diferenca = diferenca ** 2

-Exibir o resultado
print(f"O quadrado da diferença entre {n1} e {n2} é: {quadrado_da_diferenca}")

## Exercício 3
-Valor total do prêmio
premio_total = 780000.00

-Cálculo do valor para o primeiro ganhador (46%)
primeiro_colocado = premio_total * 0.46

-Cálculo do valor para o segundo ganhador (32%)
segundo_colocado = premio_total * 0.32

-Cálculo do valor para o terceiro ganhador (restante do prêmio)
terceiro_colocado = premio_total - (primeiro_colocado + segundo_colocado)

-Exibir os resultados
print(f"Primeiro colocado: R$ {primeiro_colocado:.2f}")
print(f"Segundo colocado: R$ {segundo_colocado:.2f}")
print(f"Terceiro colocado: R$ {terceiro_colocado:.2f}")