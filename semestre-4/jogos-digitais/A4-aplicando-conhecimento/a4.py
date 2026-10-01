import pygame
import sys

# Inicialização do Pygame
pygame.init()

# Configurações da tela
LARGURA, ALTURA = 800, 600
tela = pygame.display.set_mode((LARGURA, ALTURA))
pygame.display.set_caption("Nave Espacial - Tiros e Carregamento de Imagem")
relogio = pygame.time.Clock()


# ==========================================
# CLASSE TIRO (PROJÉTIL)
# ==========================================
class Tiro(pygame.sprite.Sprite):
    def __init__(self, x, y):
        super().__init__()
        # Visual do tiro (retângulo amarelo rápido)
        self.image = pygame.Surface((4, 15))
        self.image.fill((255, 255, 0))
        self.rect = self.image.get_rect(center=(x, y))
        self.speed = 10  # Velocidade rápida para se mover para cima

    def update(self):
        # Move o tiro em linha reta para cima
        self.rect.y -= self.speed

        # Remove o tiro do grupo quando ele sai da tela (otimização de memória)
        if self.rect.bottom < 0:
            self.kill()


# ==========================================
# CLASSE NAVE ESPACIAL
# ==========================================
class NaveEspacial(pygame.sprite.Sprite):
    def __init__(self, name, x, y, grupo_tiros):
        super().__init__()

        self.name = name
        self.alive = True
        self.position = [x, y]
        self.direction = 0.0
        self.speed = 5
        self.shield = 100
        self.energy = 100
        self.grupo_tiros = grupo_tiros

        # 2. Carregamento de Imagem
        try:
            # Carrega a imagem da nave (substitua 'nave.png' pelo nome da sua imagem)
            self.image = pygame.image.load("nave.png").convert_alpha()
            # Redimensiona a imagem caso seja muito grande
            self.image = pygame.transform.scale(self.image, (50, 50))
        except Exception:
            # Fallback: cria uma imagem desenhada caso o arquivo 'nave.png' não seja encontrado
            print("⚠️ Imagem 'nave.png' não encontrada! Gerando nave padrão...")
            self.image = pygame.Surface((50, 50), pygame.SRCALPHA)
            pygame.draw.polygon(self.image, (0, 255, 128), [(25, 0), (50, 50), (25, 38), (0, 50)])

        # Centraliza a imagem na posição inicial da nave
        self.rect = self.image.get_rect(center=(self.position[0], self.position[1]))

    def update(self):
        if not self.alive:
            return

        # Movimentação pelas setas direcionais
        teclas = pygame.key.get_pressed()

        if teclas[pygame.K_LEFT]:
            self.position[0] -= self.speed
        if teclas[pygame.K_RIGHT]:
            self.position[0] += self.speed
        if teclas[pygame.K_UP]:
            self.position[1] -= self.speed
        if teclas[pygame.K_DOWN]:
            self.position[1] += self.speed

        # Limites da tela
        self.position[0] = max(25, min(LARGURA - 25, self.position[0]))
        self.position[1] = max(25, min(ALTURA - 25, self.position[1]))

        # Atualiza a posição central do retângulo da nave
        self.rect.center = (self.position[0], self.position[1])

    # 1. Método para atirar
    def atirar(self):
        if self.alive:
            # Cria o tiro a partir da parte superior/central da nave
            novo_tiro = Tiro(self.rect.centerx, self.rect.top)
            self.grupo_tiros.add(novo_tiro)


# ==========================================
# SETUP DA SIMULAÇÃO / JOGO
# ==========================================
grupos_tiros = pygame.sprite.Group()
minha_nave = NaveEspacial(name="Enterprise", x=LARGURA // 2, y=ALTURA - 80, grupo_tiros=grupos_tiros)

grupo_sprites = pygame.sprite.Group()
grupo_sprites.add(minha_nave)

fonte = pygame.font.SysFont("Arial", 18)

# Loop principal do jogo
rodando = True
while rodando:
    for evento in pygame.event.get():
        if evento.type == pygame.QUIT:
            rodando = False

        # Detecta o clique único na Barra de Espaço para disparar
        if evento.type == pygame.KEYDOWN:
            if evento.key == pygame.K_SPACE:
                minha_nave.atirar()

    # Atualizações
    grupo_sprites.update()
    grupos_tiros.update()

    # Renderização
    tela.fill((10, 10, 25))  # Fundo espacial escuro

    # Desenha a nave e os tiros
    grupo_sprites.draw(tela)
    grupos_tiros.draw(tela)

    # Exibe informações na tela
    hud_texto = [
        f"Nave: {minha_nave.name} | Posição: ({int(minha_nave.position[0])}, {int(minha_nave.position[1])})",
        f"Controles: Setas para mover | ESPAÇO para atirar",
        f"Tiros Ativos na Tela: {len(grupos_tiros)}"
    ]
    for i, linha in enumerate(hud_texto):
        superficie_texto = fonte.render(linha, True, (200, 220, 255))
        tela.blit(superficie_texto, (10, 10 + (i * 22)))

    pygame.display.flip()
    relogio.tick(60)

pygame.quit()
sys.exit()