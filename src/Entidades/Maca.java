package Entidades;

import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;

public class Maca {

    private int x, y;
    private ImageIcon imageIcon;
    private final int largura = 10;
    private final int altura = 10;

    public Maca() {
        imageIcon = new ImageIcon(getClass().getResource("/resources/img/macaVermelha.png"));
        reposicionar();
    }

    public void reposicionar() {
        // Gera a maça em um lugar aleatorio
        Random rand = new Random();
        this.x = rand.nextInt(40 - 1) * 10;
        this.y = rand.nextInt(40 - 1) * 10;
    }

    public void desenharMaca(Graphics g) {
        // Desenha a imagem da maça
        g.drawImage(imageIcon.getImage(), x, y, largura, altura, null);
    }

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
