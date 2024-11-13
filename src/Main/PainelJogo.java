package Main;

import javax.swing.*;

import entities.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PainelJogo extends JPanel {
    private Cobra cobra;
    private Maca maca;
    private boolean jogoPausado = false;

    public PainelJogo(Cobra cobra, Maca maca) {
        this.cobra = cobra;
        this.maca = maca;

        // Adiciona o KeyListener para capturar as teclas pressionadas
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!jogoPausado) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                        case KeyEvent.VK_A:
                            if (!cobra.getDirecao().equals("direita")) {
                                cobra.setDirecao("esquerda");
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                        case KeyEvent.VK_D:
                            if (!cobra.getDirecao().equals("esquerda")) {
                                cobra.setDirecao("direita");
                            }
                            break;
                        case KeyEvent.VK_UP:
                        case KeyEvent.VK_W:
                            if (!cobra.getDirecao().equals("baixo")) {
                                cobra.setDirecao("cima");
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                        case KeyEvent.VK_S:
                            if (!cobra.getDirecao().equals("cima")) {
                                cobra.setDirecao("baixo");
                            }
                            break;
                    }
                }
            }
        });
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Metodo para colorir tabuleiro
        for (int i = 0; i < 400 / 20; i++) {
            for (int j = 0; j < 400 / 20; j++) {
                if ((i + j) % 2 == 0) {
                    g.setColor(new Color(0, 213, 0));
                } else {
                    g.setColor(new Color(0, 184, 0));
                }
                g.fillRect(i * 20, j * 20, 20, 20);
            }
        }

        // Percorre a cobra e desenha ela
        for (int i = 0; i < cobra.getCorpo().size(); i++) {
            Quadrado quadrado = cobra.getCorpo().get(i);

            // Desenha a cobra
            g.setColor(cobra.getCores().get(i));
            g.fillRect(quadrado.getX(), quadrado.getY(), quadrado.getLargura(), quadrado.getAltura());

            // Desenha o olho na cabeça da cobra
            if (i == 0) {
                g.drawImage(cobra.getOlhoImagem().getImage(), quadrado.getX() + 5,
                        quadrado.getY() + 5, 15, 15, null);
            }
        }

        // Desenha a maçã
        maca.desenharMaca(g);

        // Pinta a borda do tabuleiro
        g.setColor(Color.GREEN);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    public void pausarJogo() {
        jogoPausado = !jogoPausado;
    }

    public boolean isJogoPausado() {
        return jogoPausado;
    }

    public void atualizarJogo() {
        repaint();
    }

}