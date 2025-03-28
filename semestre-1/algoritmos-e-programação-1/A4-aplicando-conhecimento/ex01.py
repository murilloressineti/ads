valor_compra = float(input())
codigo_pagamento = int(input())

if codigo_pagamento == 1:
    valor_final = valor_compra * 0.9
elif codigo_pagamento == 2:
    valor_final = valor_compra * 0.95
elif codigo_pagamento == 3:
    valor_final = valor_compra
elif codigo_pagamento == 4:
    valor_final = valor_compra * 1.15

if codigo_pagamento == 3:
    parcela = valor_final / 5
elif codigo_pagamento == 4:
    parcela = valor_final / 10
else:
    parcela = valor_final

print(f"{parcela:.2f}")
