n = int(input())
pares = 0
impares = 0
for _ in range(n):
    num = int(input())
    if num % 2 == 0:
        pares += 1
    else:
        impares += 1
print(pares)
print(impares)