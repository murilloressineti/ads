# Leitura dos dados
valor_por_hora = float(input())
horas_trabalhadas = int(input())

# Cálculo do salário bruto
salario_bruto = valor_por_hora * horas_trabalhadas

# Cálculo da contribuição ao INSS (8%)
contribuicao_inss = salario_bruto * 0.08

# Cálculo da taxa sindical (5%)
taxa_sindical = salario_bruto * 0.05

# Cálculo do salário líquido
salario_liquido = salario_bruto - contribuicao_inss - taxa_sindical

# Exibição dos resultados formatados
print(f"{salario_bruto:.2f}")
print(f"{contribuicao_inss:.2f}")
print(f"{taxa_sindical:.2f}")
print(f"{salario_liquido:.2f}")
