saldo = 1000.00

while True:
    opcao = input()
    if opcao == '1':
        print(f'{saldo:.2f}')
    elif opcao == '2':
        valor = float(input())
        if valor > 0 and saldo >= valor:
            saldo -= valor
            print(f'{saldo:.2f}')
        elif valor <= 0:
            pass
        else:
            print(f'{saldo:.2f}')
    elif opcao == '3':
        valor = float(input())
        if valor > 0:
            saldo += valor
            print(f'{saldo:.2f}')
    elif opcao == '4':
        break