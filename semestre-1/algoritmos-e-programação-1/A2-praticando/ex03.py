# Valor total do prêmio
premio_total = 780000.00

# Cálculo do valor para o primeiro ganhador (46%)
primeiro_colocado = premio_total * 0.46

# Cálculo do valor para o segundo ganhador (32%)
segundo_colocado = premio_total * 0.32

# Cálculo do valor para o terceiro ganhador (restante do prêmio)
terceiro_colocado = premio_total - (primeiro_colocado + segundo_colocado)

# Exibir os resultados
print(f"Primeiro colocado: R$ {primeiro_colocado:.2f}")
print(f"Segundo colocado: R$ {segundo_colocado:.2f}")
print(f"Terceiro colocado: R$ {terceiro_colocado:.2f}")