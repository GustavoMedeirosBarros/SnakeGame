package entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class Maca {

    private int x, y;
    private ImageIcon imageIcon;
    private final int largura = 20;
    private final int altura = 20;

    public Maca() {
        imageIcon = new ImageIcon(getClass().getResource("/resources/img/macaVermelha.png"));
    }

    public void reposicionar(ArrayList<Quadrado> corpo) {
        Random random = new Random();
        boolean colisao = true;

        // Enquanto a maçã gerar uma posição que colida com o corpo da cobra
        while (colisao) {
            colisao = false;
            this.x = random.nextInt(20) * 20;
            this.y = random.nextInt(20) * 20;

            // Verifica se a nova posição colide com a cobra
            for (Quadrado quadrado : corpo) {
                if (quadrado.x == this.x && quadrado.y == this.y) {
                    colisao = true;
                    break; // Se colidir, sai do loop e tenta outra posição
                }
            }
        }
    }

    public void desenharMaca(Graphics g) {
        // Desenha a imagem da maça
        g.drawImage(imageIcon.getImage(), x, y, largura, altura, null);
    }

    ////////////////////////// GETTER E SETTERS ////////////////////////////////////
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }
}
