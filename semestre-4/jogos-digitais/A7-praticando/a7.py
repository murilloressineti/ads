# ----------------PARTE 1-------------------------------------

# Importa as bibliotecas utilizadas
from random import *
import sys
import pygame
from pygame.locals import *

# Inicializa a biblioteca pygame
pygame.init()

# Cria a surface 
size = (800, 600)
screen = pygame.display.set_mode(size)

# Define um titulo para a janela
pygame.display.set_caption("Papa Bolinhas - Versão Modificada")

# Carrega a imagem de fundo (com tratamento de exceção caso o arquivo não exista)
try:
    imagem = pygame.image.load("img/imagem_fundo.png")
except Exception:
    # Fundo padrão preto caso a imagem não esteja no diretório
    imagem = pygame.Surface(size)
    imagem.fill((20, 20, 30))

# Define as cores em RGB
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)
YELLOW = (255, 255, 0)
RED = (255, 0, 0)
BLUE = (0, 150, 255)

# Declarando a fonte do placar e variável contadora
font = pygame.font.SysFont('sans', 40)
placar = 0

# Declara o vetor que controla a posicao X e Y do circulo 
posicaoPapaBolinhas = [400, 300]

# Armazena num vetor a Velocidade de movimentacao do circulo 
velocidadePapaBolinhas = [6, 6]

# Variável para iniciar a posição do círculo vermelho
criar = True

# Variáveis de posição do círculo vermelho
X_vermelho = 0
Y_vermelho = 0

# Variável para contagem de tempo
clock = pygame.time.Clock()

# Criando objeto Clock
CLOCKTICK = pygame.USEREVENT + 1
pygame.time.set_timer(
    CLOCKTICK, 1000
)  # Timer do Pygame para execução a cada 1 segundo
temporizador = 60

# ----------------PARTE 2-------------------------------------

# Loop principal do jogo
while True:

    # Verifica se algum evento aconteceu
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()

        # Atualiza a contagem regressiva a cada 1 segundo
        if event.type == CLOCKTICK:
            temporizador = temporizador - 1

    # Finalizando o jogo ao esgotar o tempo
    if temporizador <= 0:
        break

    # Captura as teclas pressionadas para movimentação
    pressed = pygame.key.get_pressed()

    # Movimentação do Papa Bolinhas pelas setas direcionais
    if pressed[pygame.K_UP] and posicaoPapaBolinhas[1] > 20:
        posicaoPapaBolinhas[1] -= velocidadePapaBolinhas[1]
    if pressed[pygame.K_DOWN] and posicaoPapaBolinhas[1] < 580:
        posicaoPapaBolinhas[1] += velocidadePapaBolinhas[1]
    if pressed[pygame.K_LEFT] and posicaoPapaBolinhas[0] > 20:
        posicaoPapaBolinhas[0] -= velocidadePapaBolinhas[0]
    if pressed[pygame.K_RIGHT] and posicaoPapaBolinhas[0] < 780:
        posicaoPapaBolinhas[0] += velocidadePapaBolinhas[0]

    # Desenha o fundo na tela
    screen.blit(imagem, (0, 0))

    # ALTERAÇÃO DE COR 1: Papa Bolinhas desenhado com a cor AMARELA (YELLOW)
    pygame.draw.circle(screen, YELLOW, posicaoPapaBolinhas, 20)

    # MECÂNICA DE QUEDA: Posição inicial da bola vermelha no TOPO da tela
    if criar == True:
        X_vermelho = randint(40, 760)
        Y_vermelho = -10  # Inicia logo acima do limite visível do topo
        criar = False

    # Incremento da posição Y (Velocidade de queda do topo para a base)
    Y_vermelho += 5

    # Atualiza vetor de posição do círculo vermelho
    posicaoBolasVermelhas = [X_vermelho, Y_vermelho]

    # Desenha o círculo vermelho
    pygame.draw.circle(screen, RED, posicaoBolasVermelhas, 10)

    # Reinicia a bolinha no topo se ultrapassar a base da tela (Y > 600)
    if Y_vermelho > 600:
        criar = True

    # Detecção de Colisão entre o Papa Bolinhas e a Bola Vermelha
    if (
        posicaoPapaBolinhas[1] + 20 >= Y_vermelho - 10
        and posicaoPapaBolinhas[1] - 20 <= Y_vermelho + 10
    ) and (
        posicaoPapaBolinhas[0] + 20 >= X_vermelho - 10
        and posicaoPapaBolinhas[0] - 20 <= X_vermelho + 10
    ):
        criar = True
        placar = placar + 1
        try:
            pygame.mixer.music.load('som/catch.mp3')
            pygame.mixer.music.play(0)
        except Exception:
            pass  # Ignora som caso o arquivo de áudio não esteja disponível

    # Renderizando a pontuação (Placar)
    score1 = font.render('Placar: ' + str(placar), True, WHITE)
    screen.blit(score1, (600, 50))

    # Renderizando o cronômetro (Tempo)
    timer1 = font.render('Tempo: ' + str(temporizador), True, YELLOW)
    screen.blit(timer1, (50, 50))

    # Atualiza a tela visível
    pygame.display.flip()

    # Limita o framerate a 60 FPS
    clock.tick(60)

# ----------------TELA DE FIM DE JOGO-------------------------------------

# Preenche a tela de fim de jogo
screen.fill(BLACK)

textofinal = font.render(
    'Fim de Jogo! Placar final: ' + str(placar), True, RED
)
size_text = font.size('Fim de Jogo! Placar final: ' + str(placar))
screen.blit(
    textofinal, (400 - size_text[0] / 2.0, 300 - size_text[1] / 2.0)
)

pygame.display.flip()

# Loop para manter a tela final aberta até o usuário fechar a janela
while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()