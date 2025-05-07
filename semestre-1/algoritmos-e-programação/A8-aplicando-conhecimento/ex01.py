produtos = []
vendas = []

for _ in range(5):
    nome_produto = input()
    quantidade = int(input())
    produtos.append(nome_produto)
    vendas.append(quantidade)

if vendas:
    mais_vendido_quantidade = max(vendas)
    indice_mais_vendido = vendas.index(mais_vendido_quantidade)
    produto_mais_vendido = produtos[indice_mais_vendido]
    print(f"{produto_mais_vendido} {mais_vendido_quantidade}")

    menos_vendido_quantidade = min(vendas)
    indice_menos_vendido = vendas.index(menos_vendido_quantidade)
    produto_menos_vendido = produtos[indice_menos_vendido]
    print(f"{produto_menos_vendido} {menos_vendido_quantidade}")