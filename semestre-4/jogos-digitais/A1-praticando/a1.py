import random

class Tanque:
    def __init__(self, name):
        self.name = name
        self.position = [0, 0]  # x, y
        self.direction = "Norte"
        self.speed = 0
        self.armor = 100
        self.ammo = 5

    def move(self):
        self.speed = 10
        print(f"O tanque {self.name} moveu-se para frente.")

    def turn(self, direction):
        self.direction = direction
        print(f"O tanque {self.name} virou para {self.direction}.")

    def fire(self, target):
        if self.ammo > 0:
            self.ammo -= 1
            print(f"\n🚀 {self.name} disparou contra {target.name}! (Munição restante de {self.name}: {self.ammo})")
            target.hit()
        else:
            print(f"\n⚠️ {self.name} tentou atirar em {target.name}, mas está SEM MUNIÇÃO!")

    def hit(self):
        self.armor -= 50
        print(f"💥 {self.name} foi atingido! Blindagem atual: {self.armor}")
        if self.armor <= 0:
            self.explode()

    def explode(self):
        print(f"☠️ {self.name} EXPLODIU e foi destruído!")


# ==========================================
# SIMULAÇÃO DA ATIVIDADE
# ==========================================

# 1) Crie cinco objetos do tipo Tanque e armazene-os em um array (lista).
tanques = [
    Tanque("Bob"),
    Tanque("Jack"),
    Tanque("Alpha"),
    Tanque("Bravo"),
    Tanque("Charlie")
]

print("--- INÍCIO DA BATALHA DE TANQUES ---")
print(f"Tanques na arena: {[t.name for t in tanques]}\n")

rodada = 1

# 2c) Execute os itens 'a' e 'b' até que reste apenas um tanque.
while len(tanques) > 1:
    print(f"--- RODADA {rodada} ---")

    # a. Sortear um número entre 0 e o tamanho do array para selecionar o atacante
    idx_atacante = random.randint(0, len(tanques) - 1)
    atacante = tanques[idx_atacante]

    # b. Sortear o alvo (excluindo o tanque que já foi selecionado para atirar)
    # Criamos uma lista de índices disponíveis excluindo o atacante
    indices_disponiveis = [i for i in range(len(tanques)) if i != idx_atacante]
    idx_alvo = random.choice(indices_disponiveis)
    alvo = tanques[idx_alvo]

    # Executa a ação de combate
    atacante.fire(alvo)

    # Se o tanque alvo explodiu (blindagem <= 0), remove do array
    if alvo.armor <= 0:
        tanques.remove(alvo)

    print(f"Tanques restantes ({len(tanques)}): {[t.name for t in tanques]}\n")
    rodada += 1

# Fim da simulação
print("====================================")
print(f"🏆 O VENCEDOR DA SIMULAÇÃO É: {tanques[0].name}!")
print("====================================")