import math

area = float(input())
litros_necessarios = area / 3
latas = math.ceil(litros_necessarios / 18)
preco = latas * 120

print(latas)
print(f"{preco:.2f}")