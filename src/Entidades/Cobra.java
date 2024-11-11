package Entidades;

import java.awt.Color;
import java.util.ArrayList;

public class Cobra extends Quadrado {
    private int placar = 0;
    private int incremento = 10;
    private String direcao = "direita";
    private ArrayList<Quadrado> corpo;
    private Maca maca;

    public Cobra(Maca maca) {
        super(10, 10, Color.MAGENTA);
        this.maca = maca;
        corpo = new ArrayList<>();
        corpo.add(this);
        this.x = 400 / 2;
        this.y = 400 / 2;
    }

    public void MovimentoCobra() {
        // Metodo para fazer o movimento da cobra

        // Setando o indice 0 da cobra como cabeça
        Quadrado cabeca = corpo.get(0);
        int cabecaX = cabeca.x;
        int cabecaY = cabeca.y;

        switch (direcao) {
            case "esquerda":
                // Move 10 pixels para esquerda
                cabecaX -= incremento;
                break;
            case "direita":
                // Move 10 pixels para direita
                cabecaX += incremento;
                break;
            case "cima":
                // Move 10 pixels para cima
                cabecaY -= incremento;
                break;
            case "baixo":
                // Move 10 pixels para baixo
                cabecaY += incremento;
                break;
        }

        // Uma nova parte do corpo é sempre criada no lugar da cabeça e se a cabeça nao
        // colidir com a maça
        // o corpo é decrementado so ficando uma nova parte do corpo quando se colidir
        // com a maça
        Quadrado novoCorpo = new Quadrado(10, 10, Color.MAGENTA);
        novoCorpo.x = cabecaX;
        novoCorpo.y = cabecaY;
        corpo.add(0, novoCorpo);
        if (novoCorpo.x == maca.getX() && novoCorpo.y == maca.getY()) {
            placar++;
            maca.reposicionar();
        } else {
            corpo.remove(corpo.size() - 1);
        }
    }

    public boolean VerificarColisoesCorpo() {
        // Metodo para verificar a colisão do corpo da cobra
        Quadrado cabeca = corpo.get(0);
        for (int i = 1; i < corpo.size(); i++) {
            Quadrado parteCorpo = corpo.get(i);
            // Se a cabeça estivar na mesma cordenada do corpo retorna como true a colisão
            if (cabeca.x == parteCorpo.x && cabeca.y == parteCorpo.y) {
                return true;
            }
        }
        return false;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public int getPlacar() {
        return placar;
    }

    public void setPlacar(int placar) {
        this.placar = placar;
    }

    public ArrayList<Quadrado> getCorpo() {
        return corpo;
    }
}
