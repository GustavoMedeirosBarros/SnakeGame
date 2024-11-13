package entities;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Cobra extends Quadrado {
    private int placar = 0;
    private int incremento = 20;
    private String direcao = "direita";
    private ArrayList<Quadrado> corpo;
    private Maca maca;
    private ImageIcon olhoImagem;
    private ArrayList<Color> cores;
    private Color corBase;

    public Cobra(Maca maca) {
        // Inicializando a cobra
        super(20, 20, Color.MAGENTA);
        this.maca = maca;
        corpo = new ArrayList<>();
        cores = new ArrayList<>();
        corpo.add(this);
        this.x = 400 / 2;
        this.y = 400 / 2;
        corBase = Color.MAGENTA;

        olhoImagem = new ImageIcon(getClass().getResource("/resources/img/olhosCobra.png"));

        gerarCoresCorpo();
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
        Quadrado novoCorpo = new Quadrado(20, 20, Color.MAGENTA);
        novoCorpo.x = cabecaX;
        novoCorpo.y = cabecaY;

        if (novoCorpo.x == maca.getX() && novoCorpo.y == maca.getY()) {
            placar++;
            maca.reposicionar(getCorpo());
            corpo.add(0, novoCorpo);
            gerarCoresCorpo();
        } else {
            corpo.add(0, novoCorpo);
            corpo.remove(corpo.size() - 1);
            gerarCoresCorpo();
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

    private void gerarCoresCorpo() {
        // Metodo para gerar cores no corpo de diferentes tonalidades da cor da cabeça
        // da cobra
        cores.clear();
        for (int i = 0; i < corpo.size(); i++) {
            // A cor da cabeça é a primeira cor a entrar no arraylist
            if (i == 0) {
                cores.add(corBase);
            } else {
                // As seguintes partes do corpo usam cores aleatorias
                cores.add(gerarCorComTom(corBase));
            }
        }
    }

    private Color gerarCorComTom(Color corBase) {
        // Pega a tonalidade da cor da cabeça da cobra em r, g, b
        int r = corBase.getRed();
        int g = corBase.getGreen();
        int b = corBase.getBlue();

        // Define a variação máxima da cor
        int variacaoMaxima = 50;

        // Gera variação aleatória para cada componente de cor
        int novoR = r + (int) (Math.random() * variacaoMaxima * 2) - variacaoMaxima;
        int novoG = g + (int) (Math.random() * variacaoMaxima * 2) - variacaoMaxima;
        int novoB = b + (int) (Math.random() * variacaoMaxima * 2) - variacaoMaxima;

        // Garante que a cor gerada fique entre o limite 0 e 255
        novoR = Math.min(255, Math.max(0, novoR));
        novoG = Math.min(255, Math.max(0, novoG));
        novoB = Math.min(255, Math.max(0, novoB));

        return new Color(novoR, novoG, novoB);
    }

    ////////////////////////// GETTER E SETTERS ////////////////////////////////////
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

    public ImageIcon getOlhoImagem() {
        return olhoImagem;
    }

    public ArrayList<Color> getCores() {
        return cores;
    }

}
