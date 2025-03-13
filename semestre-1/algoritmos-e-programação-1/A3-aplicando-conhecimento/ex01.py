peso = float(input())
limite = 50  
excesso = max(0, peso - limite)  
multa = excesso * 4  
bonificacao = peso * 1  

if excesso > 0:  
    print(f"{excesso:.2f}")  
    print(f"{multa:.2f}")  
else:  
    print(f"{bonificacao:.2f}")  
