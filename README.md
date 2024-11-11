# Snake Game

<div align="center">

<img src="resources/img/telaInicial.png" alt="Tela inicial" height="400
"/>

</div>>

## 📝 Descrição

O Jogo da Cobrinha é uma versão clássica e divertida do famoso jogo de computador onde você controla uma cobra que se move pelo tabuleiro, com o objetivo de crescer cada vez mais ao comer obstáculos espalhados pelo cenário. O jogo se torna mais desafiador à medida que a cobra cresce e a velocidade aumenta.

## 🕹️ Imagens do Jogo

## 🎮 Regras

1. ## Movimento da cobra:

    - A cobra se move em uma direção constante: para a direita, para a esquerda, para cima ou para baixo.
    - Você pode mudar a direção da cobra usando as setas do teclado (←, →, ↑, ↓) ou as teclas (w, a, s, d).

2. ## Crescimento da cobra:

    - A a cada colisão com o obstaculo (quadrado vermelho), a cobra cresce de tamanho.
    - Após comer o obstáculo, a posição do obstáculo é aleatoriamente realocada em uma nova parte do tabuleiro.

3. ## Colisões:

    - Se a cobra colidir com as paredes do tabuleiro, ou se a cabeça da cobra colidir com seu próprio corpo o jogo termina.

4. ## Aumento de velocidade:

    - Cada vez que a cobra come um obstáculo, o tempo de atualização do jogo diminui (o que aumenta a velocidade do movimento da cobra), tornando o jogo mais desafiador.

5. ## Menu:

    - Botão "Iniciar": Para começar ou reiniciar o jogo.
    - Botão "Pausar": Para pausar o jogo a qualquer momento.
    - Botão "Reiniciar": Para reiniciar o jogo após a colisão.

6. ## Pontuação:

    - A pontuação é incrementada a cada vez que a cobra come um obstáculo. O placar é exibido na parte superior da tela.

## 🖥️ Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

### Bibliotecas utilizadas

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Color;
```

## 🖥️ Requisitos do Sistema

-   `Java 17` ou superior
-   Sistema operacional: `Windows`, `macOS` ou `Linux`

## ⚙️ Como executar o código

Na IDE Visual Studio Code

1. Dar clone no repositorio.
2. Navegar ate src/SnakeGame
3. Dar run em Tabuleiro.java

Ou apenas executar o executavel encontrado em src/SnakeGame/SnakeGame.jar.

## Diagramas
