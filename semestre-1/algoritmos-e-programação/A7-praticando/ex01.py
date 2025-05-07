def atualiza_preco(valor):
    return valor * 1.10

def taxa(valor):
    return valor * 0.025

def main():
    try:
        valor_original = float(input("Digite o valor do produto: R$ "))
        valor_atualizado = atualiza_preco(valor_original)
        valor_taxa = taxa(valor_atualizado)

        print(f"\nValor atualizado com 10% de aumento: R$ {valor_atualizado:.2f}")
        print(f"Valor da taxa de 2.5% sobre o valor atualizado: R$ {valor_taxa:.2f}")
    except ValueError:
        print("Por favor, digite um valor numérico válido.")

main()
