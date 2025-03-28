tipo_investimento = int(input())
valor_inicial = float(input())
prazo = int(input())

if tipo_investimento == 1:
    taxa_juros = 0.005
elif tipo_investimento == 2:
    taxa_juros = 0.008
elif tipo_investimento == 3:
    taxa_juros = 0.01 
else:
    taxa_juros = 0

valor_futuro = valor_inicial * (1 + taxa_juros) ** prazo

print(f"{valor_futuro:.2f}")
