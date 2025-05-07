def eh_triangulo(a, b, c):
    """Verifica se três lados podem formar um triângulo."""
    if a + b > c and a + c > b and b + c > a:
        return True
    else:
        return False

def tipo_triangulo(a, b, c):
    """Identifica o tipo de um triângulo com base nos seus lados."""
    if a == b == c:
        return "Equilátero"
    elif a == b or b == c or a == c:
        return "Isósceles"
    else:
        return "Escaleno"

def main():
    """Função principal para coletar as entradas e exibir a saída."""
    try:
        a = int(input())
        b = int(input())
        c = int(input())

        if a <= 0 or b <= 0 or c <= 0:
            print("Os lados não formam um triângulo")
        elif eh_triangulo(a, b, c):
            print("Os lados formam um triângulo")
            tipo = tipo_triangulo(a, b, c)
            print(tipo)
        else:
            print("Os lados não formam um triângulo")

    except ValueError:
        print("Entrada inválida. Por favor, digite números inteiros.")

if __name__ == "__main__":
    main()