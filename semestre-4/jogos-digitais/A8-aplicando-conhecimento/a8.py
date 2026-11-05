import json
import math
import os
import random
import sys
import pygame

# Inicialização do Pygame e Áudio
pygame.init()
pygame.mixer.init()

# Configurações Globais
LARGURA, ALTURA = 800, 600
tela = pygame.display.set_mode((LARGURA, ALTURA))
pygame.display.set_caption("Nave Espacial - Edição Final")
relogio = pygame.time.Clock()

# Cores (RGB)
BRANCO = (255, 255, 255)
PRETO = (10, 10, 25)
CINZA = (150, 150, 150)
AZUL = (0, 180, 255)
VERDE = (0, 230, 80)
VERMELHO = (255, 50, 50)
AMARELO = (255, 215, 0)


# ==========================================
# GERENCIADOR DE CONFIGURAÇÕES E RANKING
# ==========================================
ARQUIVO_RANKING = "ranking.json"


def carregar_ranking():
    if os.path.exists(ARQUIVO_RANKING):
        try:
            with open(ARQUIVO_RANKING, "r", encoding="utf-8") as f:
                return json.load(f)
        except Exception:
            pass
    # Ranking padrão inicial
    return [
        {"nome": "ACE", "pontos": 1500},
        {"nome": "BOB", "pontos": 1000},
        {"nome": "NEO", "pontos": 500},
    ]


def salvar_pontuacao(nome, pontos):
    ranking = carregar_ranking()
    ranking.append({"nome": nome.upper(), "pontos": pontos})
    # Ordena por maior pontuação e mantém o Top 5
    ranking = sorted(ranking, key=lambda x: x["pontos"], reverse=True)[:5]
    with open(ARQUIVO_RANKING, "w", encoding="utf-8") as f:
        json.dump(ranking, f, indent=4)


# ==========================================
# CARREGAMENTO DE SONS
# ==========================================
class GerenciadorSom:
    def __init__(self):
        self.ligado = True
        self.sons = {}
        self.carregar_recursos()

    def carregar_recursos(self):
        try:
            pygame.mixer.music.load("som/musica_fundo.wav")
            pygame.mixer.music.set_volume(0.3)
        except Exception:
            pass

        try:
            self.sons["tiro"] = pygame.mixer.Sound("som/som_tiro.wav")
            self.sons["tiro"].set_volume(0.5)
        except Exception:
            self.sons["tiro"] = None

        try:
            self.sons["explosao"] = pygame.mixer.Sound("som/som_explosao.wav")
            self.sons["explosao"].set_volume(0.7)
        except Exception:
            self.sons["explosao"] = None

    def tocar_musica(self):
        if self.ligado:
            try:
                pygame.mixer.music.play(-1)
            except Exception:
                pass

    def parar_musica(self):
        pygame.mixer.music.stop()

    def tocar_efeito(self, chave):
        if self.ligado and self.sons.get(chave):
            self.sons[chave].play()

    def alternar_som(self):
        self.ligado = not self.ligado
        if self.ligado:
            self.tocar_musica()
        else:
            self.parar_musica()


gerenciador_som = GerenciadorSom()


# ==========================================
# SPRITES (TIRO, ASTEROIDE, NAVE)
# ==========================================
class Tiro(pygame.sprite.Sprite):
    def __init__(self, x, y):
        super().__init__()
        self.image = pygame.Surface((4, 15))
        self.image.fill(AMARELO)
        self.rect = self.image.get_rect(center=(x, y))
        self.speed = 10

    def update(self):
        self.rect.y -= self.speed
        if self.rect.bottom < 0:
            self.kill()


class Asteroide(pygame.sprite.Sprite):
    def __init__(self):
        super().__init__()
        self.raio = random.randint(15, 40)
        largura_total = self.raio * 2
        self.image = pygame.Surface((largura_total, largura_total), pygame.SRCALPHA)
        pygame.draw.circle(self.image, (140, 140, 140), (self.raio, self.raio), self.raio)
        pygame.draw.circle(self.image, (80, 80, 80), (self.raio, self.raio), self.raio, 2)

        x = random.randint(20, LARGURA - 20)
        y = -largura_total
        self.rect = self.image.get_rect(center=(x, y))
        self.speed_x = random.uniform(-1.5, 1.5)
        self.speed_y = random.uniform(2.0, 4.5)

    def update(self):
        self.rect.x += self.speed_x
        self.rect.y += self.speed_y
        if self.rect.top > ALTURA or self.rect.right < -20 or self.rect.left > LARGURA + 20:
            self.kill()


class NaveEspacial(pygame.sprite.Sprite):
    def __init__(self, name, x, y, grupo_tiros):
        super().__init__()
        self.name = name
        self.alive = True
        self.position = [x, y]
        self.speed = 6
        self.shield = 100
        self.energy = 100
        self.ammo = 40
        self.score = 0
        self.grupo_tiros = grupo_tiros

        try:
            self.image = pygame.image.load("nave.png").convert_alpha()
            self.image = pygame.transform.scale(self.image, (50, 50))
        except Exception:
            self.image = pygame.Surface((50, 50), pygame.SRCALPHA)
            pygame.draw.polygon(self.image, (0, 255, 128), [(25, 0), (50, 50), (25, 38), (0, 50)])

        self.rect = self.image.get_rect(center=(self.position[0], self.position[1]))

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

        self.position[0] = max(25, min(LARGURA - 25, self.position[0]))
        self.position[1] = max(25, min(ALTURA - 25, self.position[1]))
        self.rect.center = (self.position[0], self.position[1])

    def atirar(self):
        if self.alive and self.ammo > 0:
            novo_tiro = Tiro(self.rect.centerx, self.rect.top)
            self.grupo_tiros.add(novo_tiro)
            self.ammo -= 1
            gerenciador_som.tocar_efeito("tiro")

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
            gerenciador_som.tocar_efeito("explosao")


# ==========================================
# INTERFACES E MENUS DO JOGO
# ==========================================
fonte_titulo = pygame.font.SysFont("Arial", 42, bold=True)
fonte_sub = pygame.font.SysFont("Arial", 24, bold=True)
fonte_menu = pygame.font.SysFont("Arial", 20)


def desenhar_texto_centralizado(texto, fonte, cor, y):
    superficie = fonte.render(texto, True, cor)
    rect = superficie.get_rect(center=(LARGURA // 2, y))
    tela.blit(superficie, rect)


def desenhar_hud(nave, tempo_segundos):
    painel = pygame.Surface((LARGURA, 60), pygame.SRCALPHA)
    painel.fill((0, 0, 0, 180))
    tela.blit(painel, (0, 0))

    # Barra de Escudo
    pygame.draw.rect(tela, (50, 50, 50), (15, 12, 120, 14))
    if nave.shield > 0:
        pygame.draw.rect(tela, AZUL, (15, 12, nave.shield * 1.2, 14))
    pygame.draw.rect(tela, BRANCO, (15, 12, 120, 14), 1)

    # Barra de Energia
    pygame.draw.rect(tela, (50, 50, 50), (15, 32, 120, 14))
    if nave.energy > 0:
        pygame.draw.rect(tela, VERDE, (15, 32, nave.energy * 1.2, 14))
    pygame.draw.rect(tela, BRANCO, (15, 32, 120, 14), 1)

    txt_vida = fonte_menu.render(f"ESCUDO: {nave.shield}% | ENERGIA: {nave.energy}%", True, BRANCO)
    tela.blit(txt_vida, (145, 20))

    cor_muni = AMARELO if nave.ammo > 5 else VERMELHO
    txt_tiros = fonte_menu.render(f"TIROS: {nave.ammo}", True, cor_muni)
    tela.blit(txt_tiros, (450, 20))

    minutos, segundos = divmod(tempo_segundos, 60)
    txt_tempo = fonte_menu.render(f"TEMPO: {minutos:02d}:{segundos:02d}", True, BRANCO)
    tela.blit(txt_tempo, (570, 20))

    txt_pontos = fonte_menu.render(f"PONTOS: {nave.score}", True, AMARELO)
    tela.blit(txt_pontos, (680, 20))


# ------------------------------------------
# TELAS DO MENU
# ------------------------------------------
def tela_instrucoes():
    rodando = True
    while rodando:
        tela.fill(PRETO)
        desenhar_texto_centralizado("INSTRUÇÕES DE JOGO", fonte_titulo, AZUL, 100)

        linhas = [
            "• Use as SETAS DO TECLADO para mover a nave.",
            "• Pressione ESPAÇO para atirar nos asteroides.",
            "• Destrua asteroides para acumular PONTOS.",
            "• Evite colidir para preservar seu ESCUDO e ENERGIA.",
            "• Sua munição é LIMITADA (40 tiros). Use com sabedoria!",
            "",
            "Pressione ESC ou ENTER para voltar ao Menu Principal.",
        ]

        for i, linha in enumerate(linhas):
            cor = AMARELO if i == len(linhas) - 1 else BRANCO
            desenhar_texto_centralizado(linha, fonte_menu, cor, 200 + (i * 35))

        pygame.display.flip()
        for ev in pygame.event.get():
            if ev.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            if ev.type == pygame.KEYDOWN and ev.key in (pygame.K_ESCAPE, pygame.K_RETURN):
                rodando = False


def tela_configuracoes():
    rodando = True
    while rodando:
        tela.fill(PRETO)
        desenhar_texto_centralizado("CONFIGURAÇÕES", fonte_titulo, AZUL, 120)

        status_som = "LIGADO" if gerenciador_som.ligado else "DESLIGADO"
        cor_som = VERDE if gerenciador_som.ligado else VERMELHO

        desenhar_texto_centralizado(f"EFEITOS E MÚSICA DE SOM: {status_som}", fonte_sub, cor_som, 250)
        desenhar_texto_centralizado("[ S ] - Alternar Som (Ligar / Desligar)", fonte_menu, BRANCO, 330)
        desenhar_texto_centralizado("Pressione ESC ou ENTER para voltar", fonte_menu, AMARELO, 450)

        pygame.display.flip()
        for ev in pygame.event.get():
            if ev.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            if ev.type == pygame.KEYDOWN:
                if ev.key == pygame.K_s:
                    gerenciador_som.alternar_som()
                elif ev.key in (pygame.K_ESCAPE, pygame.K_RETURN):
                    rodando = False


def tela_ranking():
    rodando = True
    ranking = carregar_ranking()
    while rodando:
        tela.fill(PRETO)
        desenhar_texto_centralizado("RANKING DE PONTUAÇÃO (TOP 5)", fonte_titulo, AMARELO, 100)

        for i, item in enumerate(ranking):
            pos_texto = f"{i + 1}º.  {item['nome']}  -----------  {item['pontos']} PTS"
            desenhar_texto_centralizado(pos_texto, fonte_sub, BRANCO, 200 + (i * 45))

        desenhar_texto_centralizado("Pressione ESC ou ENTER para voltar", fonte_menu, AZUL, 500)

        pygame.display.flip()
        for ev in pygame.event.get():
            if ev.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            if ev.type == pygame.KEYDOWN and ev.key in (pygame.K_ESCAPE, pygame.K_RETURN):
                rodando = False


def menu_principal():
    gerenciador_som.tocar_musica()
    opcoes = ["1. Iniciar Jogo", "2. Instruções", "3. Configurações", "4. Ranking", "5. Sair"]
    selecionado = 0

    while True:
        tela.fill(PRETO)
        desenhar_texto_centralizado("BEYOND THE STARS", fonte_titulo, AZUL, 100)
        desenhar_texto_centralizado("--- MENU PRINCIPAL ---", fonte_sub, BRANCO, 160)

        for i, opcao in enumerate(opcoes):
            cor = AMARELO if i == selecionado else CINZA
            desenhar_texto_centralizado(opcao, fonte_sub, cor, 250 + (i * 45))

        desenhar_texto_centralizado("Use as setas ↑ ↓ e PRESSIONE ENTER", fonte_menu, (100, 100, 100), 520)
        pygame.display.flip()

        for ev in pygame.event.get():
            if ev.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            if ev.type == pygame.KEYDOWN:
                if ev.key == pygame.K_UP:
                    selecionado = (selecionado - 1) % len(opcoes)
                elif ev.key == pygame.K_DOWN:
                    selecionado = (selecionado + 1) % len(opcoes)
                elif ev.key == pygame.K_RETURN:
                    if selecionado == 0:
                        loop_jogo()
                    elif selecionado == 1:
                        tela_instrucoes()
                    elif selecionado == 2:
                        tela_configuracoes()
                    elif selecionado == 3:
                        tela_ranking()
                    elif selecionado == 4:
                        pygame.quit()
                        sys.exit()


# ==========================================
# LOOP DO JOGO PRINCIPAL
# ==========================================
def loop_jogo():
    grupo_tiros = pygame.sprite.Group()
    grupo_asteroides = pygame.sprite.Group()

    nave = NaveEspacial(name="Enterprise", x=LARGURA // 2, y=ALTURA - 80, grupo_tiros=grupo_tiros)
    grupo_sprites = pygame.sprite.Group(nave)

    EVENTO_ASTEROIDE = pygame.USEREVENT + 1
    pygame.time.set_timer(EVENTO_ASTEROIDE, 700)

    tempo_inicio_ms = pygame.time.get_ticks()
    rodando = True

    while rodando:
        for ev in pygame.event.get():
            if ev.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            if ev.type == pygame.KEYDOWN:
                if ev.key == pygame.K_SPACE:
                    nave.atirar()
                elif ev.key == pygame.K_ESCAPE:
                    rodando = False  # Voltar ao menu

            if ev.type == EVENTO_ASTEROIDE and nave.alive:
                grupo_asteroides.add(Asteroide())

        if nave.alive:
            tempo_decorrido = (pygame.time.get_ticks() - tempo_inicio_ms) // 1000
            grupo_sprites.update()
            grupo_tiros.update()
            grupo_asteroides.update()

            # Colisão: Tiro atinge Asteroide
            colisoes = pygame.sprite.groupcollide(grupo_tiros, grupo_asteroides, True, True)
            for _ in colisoes:
                nave.score += 100
                gerenciador_som.tocar_efeito("explosao")

            # Colisão: Asteroide atinge Nave
            atingidos = pygame.sprite.spritecollide(nave, grupo_asteroides, True)
            for ast in atingidos:
                nave.receber_dano(ast.raio)

        # Desenho da Tela
        tela.fill(PRETO)
        grupo_sprites.draw(tela)
        grupo_tiros.draw(tela)
        grupo_asteroides.draw(tela)

        desenhar_hud(nave, tempo_decorrido if nave.alive else tempo_decorrido)

        if not nave.alive:
            desenhar_texto_centralizado("GAME OVER", fonte_titulo, VERMELHO, ALTURA // 2 - 30)
            desenhar_texto_centralizado(
                f"Pontuação Final: {nave.score}", fonte_sub, AMARELO, ALTURA // 2 + 20
            )
            desenhar_texto_centralizado(
                "Pressione ENTER para Salvar e Voltar", fonte_menu, BRANCO, ALTURA // 2 + 70
            )

            pygame.display.flip()

            # Aguarda o jogador pressionar Enter no Game Over
            esperando = True
            while esperando:
                for event in pygame.event.get():
                    if event.type == pygame.QUIT:
                        pygame.quit()
                        sys.exit()
                    if event.type == pygame.KEYDOWN and event.key == pygame.K_RETURN:
                        salvar_pontuacao(nave.name[:3], nave.score)
                        esperando = False
                        rodando = False
        else:
            pygame.display.flip()

        relogio.tick(60)


# Iniciar o jogo pelo Menu Principal
if __name__ == "__main__":
    menu_principal()