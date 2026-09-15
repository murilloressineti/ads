import math
import pygame

# Inicialização do Pygame
pygame.init()

# Configurações da tela
LARGURA, ALTURA = 800, 600
tela = pygame.display.set_mode((LARGURA, ALTURA))
pygame.display.set_caption("Nave Espacial - POO com Pygame")
relogio = pygame.time.Clock()


# 1. Classe NaveEspacial herdando de pygame.sprite.Sprite
class NaveEspacial(pygame.sprite.Sprite):

    def __init__(self, name, x, y):
        super().__init__()

        # 2. Atributos exigidos
        self.name = name
        self.alive = True
        self.position = [x, y]
        self.direction = 0.0  # Direção em graus (0° = Direita, 90° = Cima, etc.)
        self.speed = 5
        self.shield = 100
        self.energy = 100

        # 4. Desenho na Tela (Representação visual via Surface do Pygame)
        # Criamos a imagem base da nave (um triângulo verde)
        self.base_image = pygame.Surface((40, 30), pygame.SRCALPHA)

        # Desenha o triângulo apontando para a direita (0 graus por padrão)
        pontos_nave = [(0, 0), (40, 15), (0, 30), (10, 15)]
        pygame.draw.polygon(self.base_image, (0, 255, 128), pontos_nave)
        pygame.draw.polygon(self.base_image, (255, 255, 255), pontos_nave, 2)

        # Atributos obrigatórios para trabalhar com Sprites e Grupos do Pygame
        self.image = self.base_image
        self.rect = self.image.get_rect(center=(self.position[0], self.position[1]))

    # 3 e 5. Movimentação e Atualização do Estado
    def update(self):
        if not self.alive:
            return

        # Leitura da entrada de teclado pelo usuário
        teclas = pygame.key.get_pressed()

        dx = 0
        dy = 0

        if teclas[pygame.K_LEFT]:
            dx -= 1
        if teclas[pygame.K_RIGHT]:
            dx += 1
        if teclas[pygame.K_UP]:
            dy -= 1
        if teclas[pygame.K_DOWN]:
            dy += 1

        # Atualiza a posição de acordo com a velocidade (speed)
        if dx != 0 or dy != 0:
            self.position[0] += dx * self.speed
            self.position[1] += dy * self.speed

            # Ajusta a direção em graus para corresponder ao movimento atual
            # atan2 calcula o ângulo em radianos, multiplicamos para converter em graus
            self.direction = math.degrees(math.atan2(-dy, dx)) % 360

            # Atualiza a rotação visual do Sprite com base na direção
            self.image = pygame.transform.rotate(
                self.base_image, self.direction
            )
            self.rect = self.image.get_rect(
                center=(self.position[0], self.position[1])
            )
        else:
            self.rect.center = (self.position[0], self.position[1])

        # Mantém a nave dentro dos limites visíveis da tela
        self.position[0] = max(20, min(LARGURA - 20, self.position[0]))
        self.position[1] = max(20, min(ALTURA - 20, self.position[1]))


# Instanciando o objeto da classe NaveEspacial
minha_nave = NaveEspacial(name="Enterprise", x=LARGURA // 2, y=ALTURA // 2)

# Criando grupo de sprites do Pygame
grupo_sprites = pygame.sprite.Group()
grupo_sprites.add(minha_nave)

# Fonte para exibição do HUD/Atributos
fonte = pygame.font.SysFont("Arial", 18)

# Loop principal do jogo
rodando = True
while rodando:
    for evento in pygame.event.get():
        if evento.type == pygame.QUIT:
            rodando = False

    # 5. Atualiza todos os sprites do grupo a cada frame
    grupo_sprites.update()

    # Desenho da cena
    tela.fill((10, 10, 25))  # Fundo azul-escuro/espacial

    # Desenha o grupo de sprites contendo a nave
    grupo_sprites.draw(tela)

    # Exibe informações dos atributos na tela para conferência
    hud_texto = [
        f"Nave: {minha_nave.name} | Estado: {'Vivo' if minha_nave.alive else 'Destruído'}",
        f"Posição: ({int(minha_nave.position[0])}, {int(minha_nave.position[1])})",
        f"Direção: {int(minha_nave.direction)}° | Velocidade: {minha_nave.speed}",
        f"Escudo: {minha_nave.shield} | Energia: {minha_nave.energy}",
    ]

    for i, linha in enumerate(hud_texto):
        superficie_texto = fonte.render(linha, True, (200, 220, 255))
        tela.blit(superficie_texto, (10, 10 + (i * 22)))

    pygame.display.flip()
    relogio.tick(60)

pygame.quit()