total_compra = 0.0

while True:
    nome_produto = input()
    if nome_produto == 'fim':
        break
    quantidade = int(input())
    valor_unitario = float(input())

    valor_total_produto = quantidade * valor_unitario
    print(f'{valor_total_produto:.2f}')
    total_compra += valor_total_produto

print(f'{total_compra:.2f}')