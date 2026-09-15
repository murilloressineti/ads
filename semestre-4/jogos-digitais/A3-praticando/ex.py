import pygame
import random

# Inicialização do Pygame
pygame.init()
LARGURA, ALTURA = 800, 600
tela = pygame.display.set_mode((LARGURA, ALTURA))
pygame.display.set_caption("Exercícios de Pygame - Formas e Cores")


# 1) Funções para retornar superfícies de Retângulos e Círculos
def criar_superficie_retangulo(largura, altura, cor):
    # Cria uma superfície com suporte a transparência (SRCALPHA)
    surf = pygame.Surface((largura, altura), pygame.SRCALPHA)
    surf.fill(cor)
    return surf

def criar_superficie_circulo(raio, cor):
    surf = pygame.Surface((raio * 2, raio * 2), pygame.SRCALPHA)
    pygame.draw.circle(surf, cor, (raio, raio), raio)
    return surf


# 2) Função para gerar tupla de cores aleatórias
def cor_aleatoria():
    return (random.randint(0, 255), random.randint(0, 255), random.randint(0, 255))


# 3) Desenhar formas em posições, tamanhos e cores aleatórias
# Lista para armazenar as superfícies e suas posições (x, y)
elementos = []

for _ in range(15):
    # Gera retângulos aleatórios
    w, h = random.randint(30, 100), random.randint(30, 100)
    rect_surf = criar_superficie_retangulo(w, h, cor_aleatoria())
    pos_x, pos_y = random.randint(0, LARGURA - w), random.randint(0, ALTURA - h)
    elementos.append((rect_surf, (pos_x, pos_y)))

    # Gera círculos aleatórios
    r = random.randint(15, 50)
    circ_surf = criar_superficie_circulo(r, cor_aleatoria())
    pos_x, pos_y = random.randint(0, LARGURA - (r * 2)), random.randint(0, ALTURA - (r * 2))
    elementos.append((circ_surf, (pos_x, pos_y)))


# Loop principal dos exercícios
rodando = True
while rodando:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            rodando = False

    tela.fill((30, 30, 30))  # Fundo escuro

    # Método Blitting: copiando as superfícies criadas para a tela principal
    for surf, pos in elementos:
        tela.blit(surf, pos)

    pygame.display.flip()

pygame.quit()