import random
import sys
import pygame

# Inicialização do Pygame
pygame.init()

# Configurações da tela
LARGURA, ALTURA = 800, 600
tela = pygame.display.set_mode((LARGURA, ALTURA))
pygame.display.set_caption("Nave Espacial - Interface de Jogo (HUD)")
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
# CLASSE ASTEROIDE
# ==========================================
class Asteroide(pygame.sprite.Sprite):

    def __init__(self):
        super().__init__()
        self.raio = random.randint(15, 40)
        largura_total = self.raio * 2

        self.image = pygame.Surface(
            (largura_total, largura_total), pygame.SRCALPHA
        )
        pygame.draw.circle(
            self.image, (140, 140, 140), (self.raio, self.raio), self.raio
        )
        pygame.draw.circle(
            self.image, (80, 80, 80), (self.raio, self.raio), self.raio, 2
        )

        x = random.randint(20, LARGURA - 20)
        y = -largura_total
        self.rect = self.image.get_rect(center=(x, y))

        self.speed_x = random.uniform(-1.5, 1.5)
        self.speed_y = random.uniform(2.0, 5.0)

    def update(self):
        self.rect.x += self.speed_x
        self.rect.y += self.speed_y
        if (
            self.rect.top > ALTURA
            or self.rect.right < -20
            or self.rect.left > LARGURA + 20
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
        self.speed = 6
        self.shield = 100
        self.energy = 100
        self.ammo = 30  # Quantidade total de tiros disponíveis
        self.score = 0  # Pontuação do jogador
        self.grupo_tiros = grupo_tiros

        # Carregamento de imagem da nave com fallback
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
        # Só dispara se estiver viva e possuir munição restante
        if self.alive and self.ammo > 0:
            novo_tiro = Tiro(self.rect.centerx, self.rect.top)
            self.grupo_tiros.add(novo_tiro)
            self.ammo -= 1

    def receber_dano(self, quantidade):
        if self.shield > 0:
            self.shield -= quantidade
            if self.shield < 0:
                self.energy += self.shield
                self.shield = 0
        else:
            self.energy -= quantidade

        if self.energy <= 0:
            self.energy = 0
            self.alive = False


# ==========================================
# FUNÇÃO DA INTERFACE DE JOGO (HUD)
# ==========================================
def desenhar_interface(tela, nave, tempo_segundos, fonte):
    # Painel superior semi-transparente para o HUD
    painel = pygame.Surface((LARGURA, 60), pygame.SRCALPHA)
    painel.fill((0, 0, 0, 180))
    tela.blit(painel, (0, 0))

    # 1. Nível de Vida (Barra visual para Escudo e Energia)
    # Escudo (Azul)
    pygame.draw.rect(tela, (50, 50, 50), (15, 12, 120, 14))
    if nave.shield > 0:
        pygame.draw.rect(tela, (0, 180, 255), (15, 12, nave.shield * 1.2, 14))
    pygame.draw.rect(tela, (255, 255, 255), (15, 12, 120, 14), 1)

    # Energia / Vida (Verde)
    pygame.draw.rect(tela, (50, 50, 50), (15, 32, 120, 14))
    if nave.energy > 0:
        pygame.draw.rect(tela, (0, 230, 80), (15, 32, nave.energy * 1.2, 14))
    pygame.draw.rect(tela, (255, 255, 255), (15, 32, 120, 14), 1)

    txt_vida = fonte.render(
        f"ESCUDO: {nave.shield}%  ENERGIA: {nave.energy}%", True, (255, 255, 255)
    )
    tela.blit(txt_vida, (145, 22))

    # 2. Quantidade de Tiros
    cor_muni = (255, 255, 0) if nave.ammo > 5 else (255, 50, 50)
    txt_tiros = fonte.render(f"TIROS: {nave.ammo}", True, cor_muni)
    tela.blit(txt_tiros, (420, 22))

    # 3. Tempo de Jogo (Formatado em MM:SS)
    minutos = tempo_segundos // 60
    segundos = tempo_segundos % 60
    txt_tempo = fonte.render(
        f"TEMPO: {minutos:02d}:{segundos:02d}", True, (200, 220, 255)
    )
    tela.blit(txt_tempo, (550, 22))

    # 4. Pontuação
    txt_pontos = fonte.render(f"PONTOS: {nave.score}", True, (255, 215, 0))
    tela.blit(txt_pontos, (680, 22))


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

fonte_hud = pygame.font.SysFont("Arial", 16, bold=True)
fonte_game_over = pygame.font.SysFont("Arial", 48, bold=True)

# Eventos de tempo
EVENTO_CRIAR_ASTEROIDE = pygame.USEREVENT + 1
pygame.time.set_timer(EVENTO_CRIAR_ASTEROIDE, 700)

tempo_inicio_ms = pygame.time.get_ticks()

rodando = True
while rodando:
    for evento in pygame.event.get():
        if evento.type == pygame.QUIT:
            rodando = False

        if evento.type == pygame.KEYDOWN:
            if evento.key == pygame.K_SPACE:
                minha_nave.atirar()

        if evento.type == EVENTO_CRIAR_ASTEROIDE e minha_nave.alive:
            grupo_asteroides.add(Asteroide())

    # Atualização do tempo decorrido do jogo em segundos
    if minha_nave.alive:
        tempo_decorrido_s = (pygame.time.get_ticks() - tempo_inicio_ms) // 1000

        grupo_sprites.update()
        grupo_tiros.update()
        grupo_asteroides.update()

        # Colisão: Tiro atinge Asteroide (+100 Pontos)
        colisoes = pygame.sprite.groupcollide(
            grupo_tiros, grupo_asteroides, True, True
        )
        for colisao in colisoes:
            minha_nave.score += 100

        # Colisão: Asteroide atinge a Nave
        asteroides_atingidos = pygame.sprite.spritecollide(
            minha_nave, grupo_asteroides, True
        )
        for asteroide in asteroides_atingidos:
            minha_nave.receber_dano(asteroide.raio)

    # Renderização
    tela.fill((10, 10, 25))

    grupo_sprites.draw(tela)
    grupo_tiros.draw(tela)
    grupo_asteroides.draw(tela)

    # Renderiza a Interface de Jogo (HUD)
    desenhar_interface(tela, minha_nave, tempo_decorrido_s, fonte_hud)

    # Mensagem de Fim de Jogo caso a energia chegue a zero
    if not minha_nave.alive:
        txt_over = fonte_game_over.render("GAME OVER", True, (255, 50, 50))
        rect_over = txt_over.get_rect(center=(LARGURA // 2, ALTURA // 2))
        tela.blit(txt_over, rect_over)

    pygame.display.flip()
    relogio.tick(60)

pygame.quit()
sys.exit()