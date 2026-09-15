import pygame

pygame.init()
LARGURA, ALTURA = 800, 600
tela = pygame.display.set_mode((LARGURA, ALTURA))
pygame.display.set_caption("Desafio: Lerp de Cores com Mouse")


# Função de Lerp descrita no material
def lerp(value1, value2, factor):
    return value1 + (value2 - value1) * factor

def lerp_cor(cor1, cor2, fator):
    # Interpola cada componente RGB individualmente
    r = int(lerp(cor1[0], cor2[0], fator))
    g = int(lerp(cor1[1], cor2[1], fator))
    b = int(lerp(cor1[2], cor2[2], fator))
    return (r, g, b)


# Divisão da tela em 6 retângulos (2 linhas x 3 colunas)
LARGURA_RET = LARGURA // 3
ALTURA_RET = ALTURA // 2

retangulos = []
for linha in range(2):
    for coluna in range(3):
        x = coluna * LARGURA_RET
        y = linha * ALTURA_RET
        rect = pygame.Rect(x, y, LARGURA_RET, ALTURA_RET)
        retangulos.append(rect)

# Cores base para a transição (Início e Fim)
COR_INICIAL = (40, 40, 80)    # Azul escuro
COR_FINAL = (230, 80, 50)     # Laranja vibrante

clock = pygame.time.Clock()
rodando = True

while rodando:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            rodando = False

    mouse_x, mouse_y = pygame.mouse.get_pos()

    for rect in retangulos:
        # Desafio 2: Verifica se o mouse está sobre o retângulo atual
        if rect.collidepoint(mouse_x, mouse_y):
            # Calcula o fator (0.0 a 1.0) baseado na posição relativa do mouse dentro do retângulo
            fator_x = (mouse_x - rect.x) / rect.width
            fator_y = (mouse_y - rect.y) / rect.height
            fator_combinado = (fator_x + fator_y) / 2

            # Altera a cor usando a função lerp em relação à posição do mouse
            cor_atual = lerp_cor(COR_INICIAL, COR_FINAL, fator_combinado)
        else:
            cor_atual = COR_INICIAL

        # Desenha o retângulo com a cor calculada
        pygame.draw.rect(tela, cor_atual, rect)
        # Desenha a borda para separação visual
        pygame.draw.rect(tela, (200, 200, 200), rect, 2)

    pygame.display.flip()
    clock.tick(60)

pygame.quit()