import math
import random
import sys
import pygame

# Inicialização do Pygame
pygame.init()

# Configurações da tela
LARGURA, ALTURA = 800, 600
tela = pygame.display.set_mode((LARGURA, ALTURA))
pygame.display.set_caption("Nave Espacial vs Asteroides")
relogio = pygame.time.Clock()


# ==========================================
# CLASSE TIRO (PROJÉTIL)
# ==========================================
class Tiro(pygame.sprite.Sprite):

    def __init__(self, x, y):
        super().__init__()
        self.image = pygame.Surface((4, 15))
        self.image.fill((255, 255, 0))  # Amarelo
        self.rect = self.image.get_rect(center=(x, y))
        self.speed = 10

    def update(self):
        self.rect.y -= self.speed
        if self.rect.bottom < 0:
            self.kill()


# ==========================================
# 1. CLASSE ASTEROIDE
# ==========================================
class Asteroide(pygame.sprite.Sprite):

    def __init__(self):
        super().__init__()

        # Tamanhos variados (raio entre 15 e 45 pixels)
        self.raio = random.randint(15, 45)
        largura_total = self.raio * 2

        # Representação visual do asteroide (Círculo cinza estilizado)
        self.image = pygame.Surface(
            (largura_total, largura_total), pygame.SRCALPHA
        )
        pygame.draw.circle(
            self.image, (140, 140, 140), (self.raio, self.raio), self.raio
        )
        pygame.draw.circle(
            self.image, (80, 80, 80), (self.raio, self.raio), self.raio, 3
        )

        # Posição inicial aleatória (surgem no topo ou nas laterais da tela)
        if random.choice([True, False]):
            x = random.randint(0, LARGURA)
            y = -largura_total
        else:
            x = -largura_total if random.choice([True, False]) else LARGURA + largura_total
            y = random.randint(0, ALTURA // 2)

        self.rect = self.image.get_rect(center=(x, y))

        # Velocidades e direções variadas
        self.speed_x = random.uniform(-2.5, 2.5)
        self.speed_y = random.uniform(1.5, 4.5)

    def update(self):
        # Movimentação pelo cenário
        self.rect.x += self.speed_x
        self.rect.y += self.speed_y

        # Elimina o asteroide quando ele sai completamente da tela
        if (
            self.rect.top > ALTURA
            or self.rect.right < -50
            or self.rect.left > LARGURA + 50
        ):
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
        self.speed = 5
        self.shield = 100
        self.energy = 100
        self.grupo_tiros = grupo_tiros

        # Carregamento de imagem com fallback
        try:
            self.image = pygame.image.load("nave.png").convert_alpha()
            self.image = pygame.transform.scale(self.image, (50, 50))
        except Exception:
            self.image = pygame.Surface((50, 50), pygame.SRCALPHA)
            pygame.draw.polygon(
                self.image,
                (0, 255, 128),
                [(25, 0), (50, 50), (25, 38), (0, 50)],
            )

        self.rect = self.image.get_rect(
            center=(self.position[0], self.position[1])
        )

    def update(self):
        if not self.alive:
            return

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
        self.rect.center = (self.position[0], self.position[1])

    def atirar(self):
        if self.alive:
            novo_tiro = Tiro(self.rect.centerx, self.rect.top)
            self.grupo_tiros.add(novo_tiro)

    # Método de colisão que reduz escudo/energia
    def receber_dano(self, quantidade):
        if self.shield > 0:
            self.shield -= quantidade
            if self.shield < 0:
                self.energy += self.shield  # Transfere o dano excedente para a energia
                self.shield = 0
        else:
            self.energy -= quantidade

        if self.energy <= 0:
            self.energy = 0
            self.alive = False


# ==========================================
# SETUP E LOOP PRINCIPAL
# ==========================================
grupo_tiros = pygame.sprite.Group()
grupo_asteroides = pygame.sprite.Group()

minha_nave = NaveEspacial(
    name="Enterprise", x=LARGURA // 2, y=ALTURA - 80, grupo_tiros=grupo_tiros
)

grupo_sprites = pygame.sprite.Group()
grupo_sprites.add(minha_nave)

fonte = pygame.font.SysFont("Arial", 18)
fonte_game_over = pygame.font.SysFont("Arial", 48, bold=True)

# Evento personalizado para fazer surgir asteroides periodicamente
EVENTO_CRIAR_ASTEROIDE = pygame.USEREVENT + 1
pygame.time.set_timer(EVENTO_CRIAR_ASTEROIDE, 800)  # Gera um asteroide a cada 0.8s

rodando = True
while rodando:
    for evento in pygame.event.get():
        if evento.type == pygame.QUIT:
            rodando = False

        if evento.type == pygame.KEYDOWN:
            if evento.key == pygame.K_SPACE:
                minha_nave.atirar()

        # Gera novos asteroides aleatoriamente pelo evento de tempo
        if evento.type == EVENTO_CRIAR_ASTEROIDE e minha_nave.alive:
            novo_asteroide = Asteroide()
            grupo_asteroides.add(novo_asteroide)

    if minha_nave.alive:
        # Atualizações
        grupo_sprites.update()
        grupo_tiros.update()
        grupo_asteroides.update()

        # 1. Colisão: Tiros acertando Asteroides
        colisoes_tiro_asteroide = pygame.sprite.groupcollide(
            grupo_tiros, grupo_asteroides, True, True
        )

        # 2. Colisão: Asteroides atingindo a Nave
        asteroides_atingidos = pygame.sprite.spritecollide(
            minha_nave, grupo_asteroides, True
        )
        for asteroide in asteroides_atingidos:
            # Dano proporcional ao tamanho do asteroide
            dano = asteroide.raio
            minha_nave.receber_dano(dano)

    # Renderização
    tela.fill((10, 10, 25))

    grupo_sprites.draw(tela)
    grupo_tiros.draw(tela)
    grupo_asteroides.draw(tela)

    # HUD (Status da Nave)
    hud_texto = [
        f"Nave: {minha_nave.name} | Estado: {'VIVA' if minha_nave.alive else 'DESTRUÍDA'}",
        f"Escudo: {minha_nave.shield} | Energia: {minha_nave.energy}",
        f"Asteroides Ativos: {len(grupo_asteroides)}",
    ]
    for i, linha in enumerate(hud_texto):
        superficie_texto = fonte.render(linha, True, (200, 220, 255))
        tela.blit(superficie_texto, (10, 10 + (i * 22)))

    # Mensagem de Fim de Jogo caso a nave seja destruída
    if not minha_nave.alive:
        txt_over = fonte_game_over.render("GAME OVER", True, (255, 50, 50))
        rect_over = txt_over.get_rect(center=(LARGURA // 2, ALTURA // 2))
        tela.blit(txt_over, rect_over)

    pygame.display.flip()
    relogio.tick(60)

pygame.quit()
sys.exit()