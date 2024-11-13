# Snake Game

<div align="center">

<img src="/src/resources/img/telaInicial.png" alt="Tela inicial" height="300
"/>

</div>

## 📝 Descrição

Snake game é uma versão clássica e divertida do famoso jogo de computador onde você controla uma cobra que se move pelo tabuleiro, com o objetivo de crescer cada vez mais ao comer obstáculos espalhados pelo cenário. O jogo se torna mais desafiador à medida que a cobra cresce e a velocidade aumenta.

## 👥 Integrantes

-   Gustavo Medeiros Barros dos Santos
-   Gustavo Galdino Alexandre Cavalcante

## 📷 Imagens do Jogo

<div align="center">

<img src="/src/resources/img/PrintModoFacil.png" alt="Print facil" height="300
"/>
<img src="/src/resources/img/ModoFacil.png" alt="Modo facil" height="300
"/>
<img src="/src/resources/img/printModoDificil.png" alt="Print Dificil" height="300
"/>
<img src="/src/resources/img/ModoDificil.png" alt="Modo dificil" height="300
"/>

</div>

## 🕹️ Regras

## 1. Movimento da cobra:

- A cobra se move em uma direção constante: para a direita, para a esquerda, para cima ou para baixo.
- Você pode mudar a direção da cobra usando as setas do teclado (←, →, ↑, ↓) ou as teclas (w, a, s, d).

## 2. Crescimento da cobra:

- A cada colisão com a maça, a cobra cresce de tamanho.
- Após comer a maça, a posição da maça é aleatoriamente realocada em uma nova parte do tabuleiro.

## 3. Modos de jogo:

## 1. Fácil:

- Neste modo, o único jeito de perder o jogo é bater a cabeça da cobra contra o próprio corpo. Caso a cobra colida com as bordas da tela, ela será teletransportada automaticamente para o lado oposto. Este modo é ideal para iniciantes, proporcionando uma experiência mais tranquila e com menos desafios.

## 2. Normal:

- Neste modo, além da colisão com a borda ou com o próprio corpo resultar em perda de jogo, a velocidade da cobra é ligeiramente maior, tornando o jogo mais desafiador em relação ao modo fácil. Recomendado para jogadores que já têm alguma experiência e buscam um desafio moderado.

## 4. Menu:

- Botão "Iniciar": Para começar ou reiniciar o jogo.
- Botão "Pausar": Para pausar o jogo a qualquer momento.
- Botão "Reiniciar": Para reiniciar o jogo após a colisão.
- Botão "Menu": Para voltar a tela inicial.

## 5. Pontuação:

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
import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;
```

## 🖥️ Requisitos do Sistema

-   `Java 17` ou superior
-   Sistema operacional: `Windows`, `macOS` ou `Linux`

## ⚙️ Como executar o código

Na IDE Visual Studio Code

1. Dar clone no repositorio.
2. Navegar ate src/SnakeGame
3. Dar run em Tabuleiro.java

Ou apenas executar o executavel encontrado em /SnakeGame/SnakeGame.jar.

## Diagrama de classes

<div align="center">

<img src="/src/resources/img/diagramaClasses.png alt="Diagrama de classes" height="300
"/>

</div>
